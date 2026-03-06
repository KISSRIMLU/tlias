package com.chq.mapper;

import com.chq.pojo.EmpLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Ricardo
 * @since 2026/3/6 22:43
 */

@Mapper
public interface EmpLogMapper {
    @Insert("insert into emp_log(operate_time,info)values(#{operateTime},#{info}) ")
    void insert(EmpLog empLog);
}
