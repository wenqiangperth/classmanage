package com.example.common.mapper;

import com.example.common.entity.Teacher;
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
     * 根据ID查询教师信息
     * @param teacherId
     * @return
     */
    @Select("select * from teacher where id=#{teacherId}")
    @Results(id = "TeacherMap",value = {
            @Result(property = "account",column = "account"),
            @Result(property = "password",column = "password"),
            @Result(property = "teacherName",column = "teacher_name"),
            @Result(property = "isActive",column = "is_active"),
            @Result(property = "email",column = "email")
    })
    public Teacher getTeacherById(@Param(value="teacherId") long teacherId);
}
