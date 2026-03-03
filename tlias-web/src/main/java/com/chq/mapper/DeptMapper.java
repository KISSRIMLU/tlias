package com.chq.mapper;

import com.chq.pojo.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * @author Ricardo
 * @since 2026/3/3 22:50
 */
@Mapper
public interface DeptMapper {

    @Select("select * from dept")
    List<Dept> findAll();
}
