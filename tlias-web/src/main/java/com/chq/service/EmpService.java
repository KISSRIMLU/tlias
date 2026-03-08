package com.chq.service;

import com.chq.pojo.Emp;
import com.chq.pojo.EmpQueryParam;
import com.chq.pojo.PageResult;

import java.util.List;


/**
 * @author Ricardo
 * @since 2026/3/5 21:21
 */
public interface EmpService {


    PageResult<Emp> page(EmpQueryParam empQueryParam);

    void save(Emp emp);

    void deleteEmp(List<Integer> ids);

    Emp getInfo(Integer id);

    void update(Emp emp);
}
