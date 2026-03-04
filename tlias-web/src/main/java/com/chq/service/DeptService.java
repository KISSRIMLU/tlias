package com.chq.service;

import com.chq.pojo.Dept;

import java.util.List;

/**
 * @author Ricardo
 * @since 2026/3/3 22:50
 */
public interface DeptService {
     List<Dept> findAll();

    void deleteById(Integer id);

    void save(Dept dept);

    Dept getById(Integer id);

    void update(Dept dept);

}
