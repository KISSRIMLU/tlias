package com.chq.mapper;

import com.chq.pojo.Clazz;
import com.chq.pojo.ClazzsQueryParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Ricardo
 * @since 2026/3/8 15:18
 */
@Mapper
public interface ClazzMapper {
    //查找班级信息
    List<Clazz> findAllClazz(ClazzsQueryParam clazzQueryParam);
}
