package com.chq.service.impl;

import com.chq.mapper.EmpLogMapper;
import com.chq.pojo.EmpLog;
import com.chq.service.EmpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Ricardo
 * @since 2026/3/6 22:47
 */
@Service
public class EmpLogServiceImpl implements EmpLogService {

    @Autowired
    EmpLogMapper empLogMapper;


    //插入日志事物的传播行为：新建事物
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void insertLog(EmpLog empLog) {
        empLogMapper.insert(empLog);
    }
}

