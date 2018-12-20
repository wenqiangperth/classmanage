package com.example.common.mapper;

import com.example.common.entity.Teacher;
import com.example.common.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @ClassName TeacherMapper
 * @Description
 * @Author perth
 * @Date 2018/12/17 0017 下午 7:59
 * @Version 1.0
 **/

@Mapper
@Repository
public interface TeacherMapper {


    /**
     * 查询：根据账号获取teacher->User
     * @param account
     * @return
     */
    @Select("select * from teacher where account=#{account}")
    @ResultMap(value = "teacherMap")
    public User selectTeahcerByAccount(String account);

    /**
     * 查询：ID->teacher
     * @param teacherId
     * @return
     */
    @Select("select * from teacher where id=#{teacherId}")
    @Results(id = "teacherMap",value = {
            @Result(property = "account",column = "account"),
            @Result(property = "password",column = "password"),
            @Result(property = "teacherName",column = "teacher_name"),
            @Result(property = "isActive",column = "is_active"),
            @Result(property = "email",column = "email")
    })
    public Teacher selectTeacherById(@Param(value="teacherId") long teacherId);

    /**
     * 更新：password
     * @param password
     * @param id
     * @return
     */
    @Update("update teacher set password=#{password} where id=#{id}")
    public Long updateTeahcerPassword(@Param("password")String password,@Param("id") Long id);
}
