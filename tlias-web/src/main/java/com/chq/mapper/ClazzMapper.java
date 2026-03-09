package com.chq.mapper;

import com.chq.pojo.Clazz;
import com.chq.pojo.ClazzsQueryParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author Ricardo
 * @since 2026/3/8 15:18
 */
@Mapper
public interface ClazzMapper {
    //查找班级信息
    List<Clazz> findClazz(ClazzsQueryParam clazzQueryParam);

    //删除班级信息
    @Select("delete from clazz where id=#{id}")
    void deleteById(Integer id);

    //添加班级信息
    @Insert("insert into clazz( name, room, begin_date, end_date, master_id, subject,create_time,update_time)  values(#{name},#{room},#{beginDate},#{endDate},#{masterId},#{subject},#{createTime},#{updateTime})")
    void addClazz(Clazz clazz);
    //编辑班级信息回显
    @Select("select * from clazz where id=#{id}")
    Clazz getInfo(Integer id);
    //修改班级信息
    @Update("update clazz set name=#{name},room=#{room},begin_date=#{beginDate},end_date=#{endDate},master_id=#{masterId},subject=#{subject},update_time=#{updateTime} where id=#{id}")
    void updateClass(Clazz clazz);

    //查询班级列表
    @Select("select * from clazz")
    List<Clazz> findAll();
}
