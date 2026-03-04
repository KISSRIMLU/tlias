package com.chq.mapper;

import com.chq.pojo.Dept;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * @author Ricardo
 * @since 2026/3/3 22:50
 */
@Mapper
public interface DeptMapper {

    @Select("select id, name, create_time, update_time from dept")
    List<Dept> findAll();

    @Delete("delete from dept where id=#{id}")
    void deleteById(Integer id);

    @Insert("insert into dept(name, create_time, update_time) values(#{name}, #{createTime}, #{updateTime})")
    void save(Dept dept);

    @Select("select id, name, create_time, update_time from dept where id=#{id}")
    Dept getById(Integer id);

    @Insert("update dept set name=#{name}, update_time=#{updateTime} where id=#{id}")
    void update(Dept dept);
}
