package com.chq.service.impl;

import com.chq.mapper.EmpMapper;
import com.chq.pojo.Emp;
import com.chq.pojo.EmpQueryParam;
import com.chq.pojo.PageResult;
import com.chq.service.EmpService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ricardo
 * @since 2026/3/5 21:21
 */

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;


    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        //==========原始分页查询实现
//        Long total = empMapper.count();
//        Integer start = (page - 1) * pageSize;
//        List<Emp> rows = empMapper.list();
//        return new PageResult<Emp>(total, rows);

        //=================PageHelper分页查询实现
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());

        List<Emp> empList=empMapper.list(empQueryParam);
        Page< Emp> p = (Page< Emp>) empList;
        return new PageResult<Emp>(p.getTotal(), p.getResult());

    }
}
