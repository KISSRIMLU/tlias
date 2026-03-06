package com.chq.controller;

import com.chq.pojo.Emp;
import com.chq.pojo.EmpQueryParam;
import com.chq.pojo.PageResult;
import com.chq.pojo.Result;
import com.chq.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



/**
 * @author Ricardo
 * @since 2026/3/5 21:18
 */

@Slf4j
@RestController
public class EmpController {
    @Autowired
    private EmpService empService;


    @GetMapping("/emps")
    public Result page(EmpQueryParam empQueryParam) {
        log.info("分页查询:{}", empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    /*工作经路*/
    @PostMapping("/emps")
    public Result seave(@RequestBody Emp emp){
        log.info("保存员工:{}", emp);
        empService.save(emp);
        return Result.success();
    }

}
