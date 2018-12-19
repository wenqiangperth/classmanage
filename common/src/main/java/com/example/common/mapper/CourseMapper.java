package com.example.common.mapper;

import com.example.common.entity.Course;
import java.util.Date;
import java.util.ArrayList;
import com.example.common.entity.Klass;
import com.example.common.entity.Round;
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
    public long addCourse(@Param(value = "teacherId") long teacherId,@Param(value = "courseName")String courseName,@Param(value = "introduction")String introduction,@Param(value = "presentationPercentage")int presentationPercentage,@Param(value = "questionPercentage")int questionPercentage,@Param(value = "reportPercentage")int reportPercentage,@Param(value = "teamStartTime")Date teamStartTime,@Param(value = "teamEndTime")Date teamEndTime,@Param(value = "teamMainCourseId")long teamMainCourseId,@Param(value = "seminarMainCourseId")long seminarMainCourseId);

    /**
     * 查看课程信息
     * @param courseId
     * @return
     */
    @Select("select * from course where id=#{id}")
    @Results(id = "courseMap",value = {
            @Result(property = "teacherId",column = "teacher_id"),
            @Result(property = "courseName",column = "course_name"),
            @Result(property = "introduction",column = "introduction"),
            @Result(property = "presentationPercentage",column = "presentation_percentage"),
            @Result(property = "questionPercentage",column = "question_percentage"),
            @Result(property = "reportPercentage",column = "report_percentage"),
            @Result(property = "teamStartTime",column = "team_start_time"),
            @Result(property = "teamEndTime",column = "team_end_time"),
            @Result(property = "teamMainCourseId",column = "team_main_course_id"),
            @Result(property = "seminarMainCourseId",column = "seminar_main_course_id")
    })
    public Course getCourseById(@Param(value="id")long courseId);

    /**
     * 根据课程id删除课程
     * @param courseId
     * @return
     */
    @Delete("delete from course where id=#{id}")
    public long deleteCourseById(@Param(value="id") long courseId);

    /**
     * 查看所有课程
     * @return
     */
    @Select("select * from course")
    @ResultMap(value = "courseMap")
    public ArrayList<Course> getAllCourse();

    /**
     * 根据课程id查询所有轮次
     * @param courseId
     * @return
     */
    @Select("select * from round where course_id=#{courseId}")
    @Results(id = "roundMap",value = {
            @Result(property = "courseId",column = "course_id"),
            @Result(property = "roundSerial",column = "round_serial"),
            @Result(property = "presentationScoreMethod",column = "presentation_score_method"),
            @Result(property = "reportScoreMethod",column = "report_score_method"),
            @Result(property = "questionScoreMethod",column = "question_score_method")
    })
    public ArrayList<Round> getAllRoundByCourseId(@Param(value="courseId") long courseId);

    @Select("select * from klass where course_id=#{courseId}")
        public ArrayList<Klass> getAllClassByCourseId(@Param(value="courseId") long courseId);

    @Select("select student_id from klass_student where course_id=#{courseId} and team_id is null")
    public ArrayList<Long> getAllNoTeamStudentByCourseId(@Param(value="courseId") long courseId);
}

