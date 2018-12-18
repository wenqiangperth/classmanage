package com.example.common.mapper;

import com.example.common.entity.Course;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;


/**
 * @ClassName CourseMapper
 * @Description
 * @Author perth
 * @Date 2018/12/17 0017 下午 7:57
 * @Version 1.0
 **/

@Mapper
@Repository
public interface CourseMapper {

    @Insert("insert into course(teacher_id,course_name,introduction,presentation_percentage,question_percentage,report_percentage,team_start_time,team_end_time,team_main_course_id,seminar_main_course_id)values (#{teacherId},#{courseName},#{introduction},#{presentationPercentage},#{questionPercentage},#{reportPercentage},#{teamStartTime},#{teamEndTime},#{teamMainCourseId},#{seminarMainCourseId})")
    public long addCourse(long teacherId,String courseName,String introduction,int presentationPercentage,int questionPercentage,int reportPercentage,Date teamStartTime,Date teamEndTime,long teamMainCourseId,long seminarMainCourseId);

    @Select("select * from administrator where id=#{id}")
    public Course getCourseById(@Param(value="id")long courseId);

    @Delete("delete from course where id=#{courseId}")
    public long deleteCourseById(@Param(value="id") long courseId);

    @Select("select * from course where id=#{courseId}")
    public List<Course> getAllCourse();
}

