package com.example.common.mapper;

import com.example.common.entity.Course;
import com.example.common.entity.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

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
    @Results(id="studentMap",value = {
            @Result(property = "account",column = "account"),
            @Result(property = "password",column = "password"),
            @Result(property = "isActive",column = "is_active"),
            @Result(property = "studentName",column ="student_name" ),
            @Result(property = "email",column = "email")
    })
    public Student getStudentById(@Param(value="studentId") long studentId);


    /**
     * 根据学生id获取他的所有课程
     * @param stundetId
     * @return
     */
    @Select("select c.id,c.teacher_id,c.course_name,c.introduction,c.presentation_percentage," +
            "c.question_percentage,c.report_percentage,c.team_start_time,c.team_end_time,c.team_main_course_id,c.seminar_main_course_id from course c,klass_student ks where c.id=ks.course_id and ks.student_id=#{studentId}")
    @Results(id="courseMap",value = {
            @Result(property = "teacherId",column = "teacher_id"),
            @Result(property = "courseName",column = "course_name"),
            @Result(property = "presentationPercentage",column = "presentation_percentage"),
            @Result(property = "questionPercentage",column ="question_percentage" ),
            @Result(property = "reportPercentage",column = "report_percentage"),
            @Result(property = "teamStartTime",column = "team_start_time"),
            @Result(property = "teamEndTime",column = "team_end_time"),
            @Result(property = "teamMainCourseId",column = "team_main_course_id"),
            @Result(property = "seminarMainCourseId",column = "seminar_main_course_id")
    })
    public ArrayList<Course> getAllCoursesByStundetId(@Param("studentId")Long stundetId);


}
