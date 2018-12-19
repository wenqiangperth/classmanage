package com.example.common.mapper;

import com.example.common.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @ClassName StudentMapper
 * @Description
 * @Author perth
 * @Date 2018/12/17 0017 下午 7:58
 * @Version 1.0
 **/

@Mapper
@Repository
public interface StudentMapper {

    @Select("select * from student where id=#{studentId}")
    public Student getStudentById(@Param(value="id") long studentId);

}
