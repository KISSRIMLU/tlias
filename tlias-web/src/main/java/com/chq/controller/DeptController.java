package com.chq.controller;

import com.chq.pojo.Dept;
import com.chq.pojo.Result;
import com.chq.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ricardo
 * @since 2026/3/3 22:50
 */
@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

    @Autowired
    DeptService deptService;


    //查询部门
    @GetMapping()
    public Result list() {
        log.info("查询部门列表");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    //删除部门
    @DeleteMapping()
    public Result delete( Integer id) {
        log.info("删除部门id为:{}",id);
        deptService.deleteById(id);
        return Result.success();
    }

    //添加部门
    @PostMapping()
    public Result add(@RequestBody Dept dept){
        log.info("添加部门dept={}" , dept);
        deptService.save(dept);
        return Result.success();
    }


    //查询部门回显
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("查询部门id为:{}",id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    //修改部门
    @PutMapping()
    public Result update(@RequestBody Dept dept){
        log.info("修改部门, dept: {}" , dept);
        deptService.update(dept);
        return Result.success();
    }
}


