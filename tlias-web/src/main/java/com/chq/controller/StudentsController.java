package com.chq.controller;

import com.chq.pojo.*;
import com.chq.service.impl.StudentsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Ricardo
 * @since 2026/3/9 20:37
 */
@Slf4j
@RestController
@RequestMapping("/students")
public class StudentsController {

    @Autowired
    StudentsServiceImpl studentsServiceImpl;

    @GetMapping
    public Result findClazz(StudentsQueryParam studentsQueryParam) {
        log.info("查询学生{}", studentsQueryParam);
        PageResult<Student> studentList = studentsServiceImpl.findStudents(studentsQueryParam);
        return Result.success(studentList);
    }

    @DeleteMapping("/{ids}")
    public Result deleteStudent(@PathVariable List<Integer> ids) {
        log.info("删除学生列表#{}", ids);
        studentsServiceImpl.deleteStudent(ids);
        return Result.success();
    }

    @PostMapping
    public Result addStudent(@RequestBody Student student) {
        log.info("添加学生列表#{}", student);
        studentsServiceImpl.addStudent(student);
        return Result.success();
    }
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("查询学生列表#{}", id);
        Student studentInfo = studentsServiceImpl.getInfo(id);
        return Result.success(studentInfo);
    }
    @PutMapping
    public Result update(@RequestBody Student student) {
        log.info("修改学生列表#{}", student);
        studentsServiceImpl.update(student);
        return Result.success();
    }

    @PutMapping("/violation/{id}/{score}")
    public Result updateViolation(@PathVariable Integer id, @PathVariable Integer score) {
        log.info("扣除学生id#{}#{}分", id, score);
        studentsServiceImpl.updateViolation(id, score);
        return Result.success();
    }

}
