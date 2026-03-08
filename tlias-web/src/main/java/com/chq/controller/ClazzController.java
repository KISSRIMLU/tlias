package com.chq.controller;

import com.chq.pojo.*;
import com.chq.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        log.info("查询班级列表#{}",clazzQueryParam);
        PageResult<Clazz> ClazzList =clazzServiceImpl.findClazz(clazzQueryParam) ;
        return Result.success(ClazzList);
    }
}
