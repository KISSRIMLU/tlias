package com.chq.mapper;

import com.chq.pojo.Emp;
import com.chq.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;


import java.util.List;
import java.util.Map;

/**
 * @author Ricardo
 * @since 2026/3/5 21:20
 */

@Mapper
public interface EmpMapper {
//------------------原始分页查询实现
//    @Select("select count(*) from  emp e left join dept d on e.dept_id=d.id")
//    public Long count();

//    @Select("select e.* ,d.name as deptName from emp e left join dept d on e.dept_id=d.id " +
//            "order by e.update_time desc limit #{start},#{pageSize}")
//    public List<Emp> list(Integer start, Integer pageSize);

    //---------------------------PageHelper 分页查询实现
    List<Emp> list(EmpQueryParam empQueryParam);


    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " +
            "values (#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void inster(Emp emp);

    void deleteEmp(List<Integer> ids);

    Emp getById(Integer id);

    //根据 ID 修改员工信息
    void updateById(Emp emp);

    @Select("select id,name,username from emp")
    List<Emp> getEmpList();

    @MapKey("job")
    List<Map<String, Object>> getEmpJobData();

    @Select("select if (gender=1,'男', '女') as name,count(gender) as value from emp group by gender")
    @MapKey("name")
    List<Map> countEmpGenderData();


    @Select("select * from emp where username=#{username} and password=#{password}")
    Emp getByUsernameAndPassword(Emp emp);
}
