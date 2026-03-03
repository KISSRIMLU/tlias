package com.chq.service.impl;

import com.chq.mapper.DeptMapper;
import com.chq.pojo.Dept;
import com.chq.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ricardo
 * @since 2026/3/3 22:50
 */
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    DeptMapper deptMapper;
    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }
}
