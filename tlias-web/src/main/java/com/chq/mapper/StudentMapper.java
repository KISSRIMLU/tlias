package com.chq.mapper;

import com.chq.pojo.Student;
import com.chq.pojo.StudentsQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Ricardo
 * @since 2026/3/9 20:59
 */
@Mapper
public interface StudentMapper {
    List<Student> findStudents(StudentsQueryParam studentsQueryParam);


    void deleteStudent(List<Integer> ids);


    void addStudent(Student student);

    @Select("select * from student where id = #{id}")
    Student getInfo(Integer id);

    @Update("update student set name = #{name},no = #{no},phone = #{phone},gender = #{gender},degree = #{degree},id_card = #{idCard},is_college = #{isCollege},address = #{address},graduation_date = #{graduationDate},violation_count = #{violationCount} ,violation_score = #{violationScore},clazz_id = #{clazzId} ,update_time=#{updateTime} where id = #{id}")
    void update(Student student);

    @Update("update student set violation_count = violation_count + 1,violation_score = violation_score + #{score} ,update_time=NOW() where id = #{id}")
    void updateViolation(Integer id, Integer score);

    @Select("select count(*) from student where clazz_id = #{id}")
    Integer countByClazzId(Integer id);
}
