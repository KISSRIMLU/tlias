package com.chq.controller;

import com.chq.pojo.Dept;
import com.chq.pojo.Result;
import com.chq.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Ricardo
 * @since 2026/3/3 22:50
 */
@RestController
public class DeptController {

    @Autowired
    DeptService deptService;

    @RequestMapping("/dept")
    public Result list() {
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }
}