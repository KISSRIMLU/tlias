package com.chq.service.impl;

import com.chq.mapper.EmpExprMapper;
import com.chq.mapper.EmpMapper;
import com.chq.pojo.*;
import com.chq.service.EmpLogService;
import com.chq.service.EmpService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Ricardo
 * @since 2026/3/5 21:21
 */

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

    @Autowired
    private EmpLogService empLogService;


    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        //==========原始分页查询实现
//        Long total = empMapper.count();
//        Integer start = (page - 1) * pageSize;
//        List<Emp> rows = empMapper.list();
//        return new PageResult<Emp>(total, rows);

        //=================PageHelper分页查询实现
        //1. 设置PageHelper分页参数
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
        //2. 执行查询
        List<Emp> empList = empMapper.list(empQueryParam);
        //3. 封装分页结果
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult<Emp>(p.getTotal(), p.getResult());

    }

    //Transactional注解实现事务管理==================
    //捕获所有异常
    @Transactional(rollbackFor = Exception.class)

    @Override
    public void save(Emp emp) {
        try {
            //1.补全基础属性
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.inster(emp);

            //循环便利 保存员工经历
            Integer empId = emp.getId();
            List<EmpExpr> exprList = emp.getExprList();
            if (!CollectionUtils.isEmpty(exprList)) {
                exprList.forEach(empExpr -> empExpr.setEmpId(empId));
                empExprMapper.insertBatch(exprList);
            }
        } finally {
            //插入操作日志，设置传播性：propagation = Propagation.REQUIRES_NEW
            EmpLog emplog = new EmpLog(null, LocalDateTime.now(), emp.toString());
            empLogService.insertLog(emplog);
        }
    }

    @Override
    public void deleteEmp(List<Integer> ids) {

        empExprMapper.deleteEmpExpr(ids);

        empMapper.deleteEmp(ids);
    }


    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Emp emp) {
        //1,根据ID修改员工信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);

        //2,1删除员工工作经历
        // Collections.singletonList(元素)//将单个元素转换成List
        // Arrays.asList(数组)//将数组转换成List
        empExprMapper.deleteEmpExpr(Arrays.asList(emp.getId()));

        //2,2添加员工工作经历
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)) {
            exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
            empExprMapper.insertBatch(exprList);
        }
    }
}
