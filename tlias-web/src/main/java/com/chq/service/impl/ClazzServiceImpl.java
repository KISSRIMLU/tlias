package com.chq.service.impl;

import com.chq.mapper.ClazzMapper;
import com.chq.pojo.Clazz;
import com.chq.pojo.ClazzsQueryParam;
import com.chq.pojo.PageResult;
import com.chq.service.ClazzService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ricardo
 * @since 2026/3/8 15:15
 */
@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    ClazzMapper clazzMapper;

    @Override
    public PageResult<Clazz> findClazz(ClazzsQueryParam clazzQueryParam) {
        //=================PageHelper分页查询实现
        //1. 设置PageHelper分页参数
        PageHelper.startPage(clazzQueryParam.getPage(), clazzQueryParam.getPageSize());
        //2. 执行查询
        List<Clazz> clazzList = clazzMapper.findAllClazz(clazzQueryParam);
        //3. 封装分页结果
        Page<Clazz> p = (Page<Clazz>) clazzList;
        PageResult<Clazz> pageResult = new PageResult<>(p.getTotal(), clazzList);
        return pageResult;
    }
}
