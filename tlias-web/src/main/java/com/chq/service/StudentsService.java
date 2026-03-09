package com.chq.service;

import com.chq.pojo.PageResult;
import com.chq.pojo.Student;
import com.chq.pojo.StudentsQueryParam;

import java.util.List;

/**
 * @author Ricardo
 * @since 2026/3/9 20:47
 */
public interface StudentsService {
    PageResult<Student> findStudents(StudentsQueryParam studentsQueryParam);

    void deleteStudent(List<Integer> ids);

    void addStudent(Student student);

    Student getInfo(Integer id);

    void update(Student student);

    void updateViolation(Integer id, Integer score);
}
