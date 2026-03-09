package com.chq.controller;

import com.chq.pojo.Emp;
import com.chq.pojo.EmpQueryParam;
import com.chq.pojo.PageResult;
import com.chq.pojo.Result;
import com.chq.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author Ricardo
 * @since 2026/3/5 21:18
 */

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;


    @GetMapping
    public Result page(EmpQueryParam empQueryParam) {
        log.info("分页查询:{}", empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    /*工作经历*/
    @PostMapping
    public Result save(@RequestBody Emp emp) {
        log.info("保存员工:{}", emp);
        empService.save(emp);
        return Result.success();
    }

    @DeleteMapping
    public Result deleteEmp(@RequestParam List<Integer> ids) {
        log.info("删除员工#{}", ids);
        empService.deleteEmp(ids);
        return Result.success();
    }

    /**
     * 根据 ID 查询员工信息
     *
     * @param id 员工 ID
     * @return 包含员工信息的 Result 对象，如果未找到则返回 null
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("查询员工#{}", id);
        Emp emp = empService.getInfo(id);
        return Result.success(emp);
    }


    //修改员工
    @PutMapping
    public Result update(@RequestBody Emp emp) {
        log.info("修改员工#{}", emp);
        empService.update(emp);
        return Result.success();
    }

    @GetMapping("/list")
    public Result getEmpList() {
        log.info("查询所有员工列表");
        List<Emp> empList = empService.getEmpList();
        return Result.success(empList);
    }
}

