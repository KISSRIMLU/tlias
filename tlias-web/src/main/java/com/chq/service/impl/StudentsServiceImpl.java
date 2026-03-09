package com.chq.service.impl;

import com.chq.mapper.StudentMapper;
import com.chq.pojo.PageResult;
import com.chq.pojo.Student;
import com.chq.pojo.StudentsQueryParam;
import com.chq.service.StudentsService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Ricardo
 * @since 2026/3/9 20:53
 */
@Service
public class StudentsServiceImpl implements StudentsService {

    @Autowired
    StudentMapper studentMapper;

    @Override
    public PageResult<Student> findStudents(StudentsQueryParam studentsQueryParam) {
        PageHelper.startPage(studentsQueryParam.getPage(), studentsQueryParam.getPageSize());
        List<Student> studentList = studentMapper.findStudents(studentsQueryParam);
        Page<Student> p = (Page<Student>) studentList;
        return new PageResult<Student>(p.getTotal(), studentList);
    }

    @Override
    public void deleteStudent(List<Integer> ids) {
        studentMapper.deleteStudent(ids);
    }

    @Override
    public void addStudent(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.addStudent(student);
    }

    @Override
    public Student getInfo(Integer id) {
        Student student = studentMapper.getInfo(id);
        return student;
    }

    @Override
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.update(student);
    }

    @Override
    public void updateViolation(Integer id, Integer score) {
        studentMapper.updateViolation(id, score);
    }
}
