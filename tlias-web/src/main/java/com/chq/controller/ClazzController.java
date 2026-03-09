package com.chq.controller;

import com.chq.pojo.*;
import com.chq.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ricardo
 * @since 2026/3/8 15:03
 */

@Slf4j
@RequestMapping("/clazzs")
@RestController
public class ClazzController {

    @Autowired
    ClazzService clazzServiceImpl;

    @GetMapping
    public Result findClazz(ClazzsQueryParam clazzQueryParam) {
        log.info("条件查询班级#{}", clazzQueryParam);
        PageResult<Clazz> ClazzList = clazzServiceImpl.findClazz(clazzQueryParam);
        return Result.success(ClazzList);
    }

    @DeleteMapping("/{id}")
    public Result deleteClazz(@PathVariable Integer id) {
        log.info("删除班级列表#{}", id);
        clazzServiceImpl.deleteClazz(id);
        return Result.success();
    }

    @PostMapping
    public Result addClazz(@RequestBody Clazz clazz) {
        log.info("添加班级列表#{}", clazz);
        clazzServiceImpl.addClazz(clazz);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("查询班级列表#{}", id);
        Clazz clazzInfo = clazzServiceImpl.getInfo(id);
        return Result.success(clazzInfo);
    }

    @PutMapping
    public Result updateClass(@RequestBody Clazz clazz) {
        log.info("修改班级列表#{}", clazz);
        clazzServiceImpl.updateClass(clazz);
        return Result.success();
    }
    @GetMapping("/list")
    public Result list() {
        log.info("查询所有班级列表");
        List<Clazz> clazzList = clazzServiceImpl.findAll();
        return Result.success(clazzList);
    }
}
