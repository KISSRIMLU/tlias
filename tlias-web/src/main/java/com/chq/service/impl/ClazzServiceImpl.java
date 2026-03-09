package com.chq.service.impl;

import com.chq.exception.BusinessException;
import com.chq.mapper.ClazzMapper;
import com.chq.mapper.StudentMapper;
import com.chq.pojo.Clazz;
import com.chq.pojo.ClazzsQueryParam;
import com.chq.pojo.PageResult;
import com.chq.service.ClazzService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Ricardo
 * @since 2026/3/8 15:15
 */
@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    StudentMapper studentMapper;
    @Autowired
    ClazzMapper clazzMapper;

    @Override
    public PageResult<Clazz> findClazz(ClazzsQueryParam clazzQueryParam) {
        //=================PageHelper分页查询实现
        //1. 设置PageHelper分页参数
        PageHelper.startPage(clazzQueryParam.getPage(), clazzQueryParam.getPageSize());
        //2. 执行查询
        List<Clazz> clazzList = clazzMapper.findClazz(clazzQueryParam);
        //3. 封装分页结果
        Page<Clazz> p = (Page<Clazz>) clazzList;
        return new PageResult<>(p.getTotal(), clazzList);
    }

    @Override
    public void deleteClazz(Integer id) {

        Integer studentCount = studentMapper.countByClazzId(id);
        if (studentCount > 0) {
            // 抛出自定义异常，提示"该班级下有学生，不能直接删除"
            throw new BusinessException("对不起, 该班级下有学生, 不能直接删除");
        }
        clazzMapper.deleteById(id);
    }

    @Override
    public void addClazz(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.addClazz(clazz);
    }

    @Override
    public Clazz getInfo(Integer id) {
        Clazz clazzInfo = clazzMapper.getInfo(id);
        return clazzInfo;
    }

    @Override
    public void updateClass(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.updateClass(clazz);
    }

    @Override
    public List<Clazz> findAll() {
        clazzMapper.findAll();
        return clazzMapper.findAll();
    }
}
