package com.example.common.mapper;

import com.example.common.entity.*;

import java.util.Date;
import java.util.ArrayList;

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

    /**
     * 添加课程
     * @param teacherId
     * @param courseName
     * @param introduction
     * @param presentationPercentage
     * @param questionPercentage
     * @param reportPercentage
     * @param teamStartTime
     * @param teamEndTime
     * @param teamMainCourseId
     * @param seminarMainCourseId
     * @return
     */
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
     * 根据学生ID返回我的课程
     * @param studentId
     * @return
     */
    @Select("select * from klass_student where student_id=#{studentId}")
    @Results(id = "studentCourseMap",value = {
            @Result(property = "klassId",column = "klass_id"),
            @Result(property = "studentId",column = "student_id"),
            @Result(property = "courseId",column = "course_id"),
            @Result(property = "teamId",column = "team_id")
    })
    public ArrayList<StudentCourseVO> getAllCourseByStudentId(@Param(value="studentId") long studentId);

    /**
     * 老师查看课程
     * @param teacherId
     * @return
     */
    @Select("select * from course where teacher_id=#{teacherId}")
    @ResultMap(value="courseMap")
    public ArrayList<Course> getAllCourseByTeacherId(@Param(value="teacherId") long teacherId);




    /**
     * 根据课程id找所有没有组队的学生id
     * @param courseId
     * @return
     */
    @Select("select student_id from klass_student where course_id=#{courseId} and team_id is null")
    public ArrayList<Long> getAllNoTeamStudentByCourseId(@Param(value="courseId") long courseId);

    /**
     * 获得所有共享分组请求
     * @param courseId
     * @return
     */
    @Select("select * from share_team_application where main_course_id=#{courseId} or sub_course_id=#{courseId}")
    @Results(id = "TeamShareMap",value = {
            @Result(property = "mainCourseId",column = "main_course_id"),
            @Result(property = "subCourseId",column = "sub_course_id"),
            @Result(property = "subCourseTeacherId",column = "sub_course_teacher_id"),
            @Result(property = "status",column = "status")
    })
    public ArrayList<TeamShareVO> getAllTeamShare(@Param(value="courseId") long courseId);

    /**
     * 获得所有共享讨论课请求
     * @param courseId
     * @return
     */
    @Select("select * from share_seminar_application where main_course_id=#{courseId} or sub_course_id=#{courseId}")
    @Results(id = "SeminarShareMap",value = {
            @Result(property = "mainCourseId",column = "main_course_id"),
            @Result(property = "subCourseId",column = "sub_course_id"),
            @Result(property = "subCourseTeacherId",column = "sub_course_teacher_id"),
            @Result(property = "status",column = "status")
    })
    public ArrayList<SeminarShareVO> getAllSeminarShare(@Param(value="courseId") long courseId);

    /**
     * 根据学生id和课程id获得队伍Id
     * @param studentId
     * @param courseId
     * @return
     */
    @Select("select team_id from klass_student where student_id=#{studentId} and course_id=#{courseId}")
    public long getTeamIdByCourseIdAndStudentId(@Param(value="studentId") long studentId,@Param(value="courseId") long courseId);

    /**
     * 根据teamShareId查看subCourseId
     * @param teamShareId
     * @return
     */
    @Select("select sub_course_id from share_team_application where id=#{teamShareId}")
    public Long getSubCourseIdByTeamShareId(@Param(value="teamShareId")Long teamShareId);

    /**
     * 根据teamShareId删除该条共享分组的记录
     * @param teamShareId
     * @return
     */
    @Delete("delete from share_team_application where id=#{teamShareId}")
    public Long deleteTeamShareByteamShareId(@Param(value="teamShareId") Long teamShareId);

    /**
     * 根据seminarShareId查看subCourseId
     * @param seminarShareId
     * @return
     */
    @Select("select sub_course_id from share_seminar_application where id=#{seminarShareId}")
    public Long getSubCourseIdBySeminarShareId(@Param(value="seminarShareId")Long seminarShareId);

    /**
     * 根据seminarShareId删除该条共享讨论课的记录
     * @param seminarShareId
     * @return
     */
    @Delete("delete from share_seminar_application where id=#{seminarShareId}")
    public Long deleteSeminarShareBySeminarShareId(@Param(value="seminarShareId") Long seminarShareId);

    /**
     * 根据班级Id和讨论课id删除对应的klass_seminar表中的记录
     * @param classId
     * @param seminarId
     * @return
     */
    @Delete("delete from klass_seminar where klass_id=#{classId} and seminar_id=#{seminar_id}")
    public Long deleteKlassSeminarByCourseId(@Param(value="classId")Long classId,@Param(value="seminarId")Long seminarId);

    /**
     * 在share_team_application表中插入数据
     * @param courseId
     * @param subCourseId
     * @param subCourseTeacherId
     * @return
     */
    @Insert("insert into share_team_application(main_course_id,sub_course_id,subcourse_teacher_id) values (#{courseId},#{subCourseId},#{subCourseTeacherId}")
    public Long createTeamShareRequest(@Param(value="courseId") Long courseId,@Param(value="subCourseId")Long subCourseId,@Param(value="subCourseTeacherId")Long subCourseTeacherId);

    /**
     * 在share_seminar_application表中插入数据
     * @param courseId
     * @param subCourseId
     * @param subCourseTeacherId
     * @return
     */
    @Insert("insert into share_seminar_application(main_course_id,sub_course_id,subcourse_teacher_id) values (#{courseId},#{subCourseId},#{subCourseTeacherId}")
    public Long createSeminarShareRequest(@Param(value="courseId") Long courseId,@Param(value="subCourseId")Long subCourseId,@Param(value="subCourseTeacherId")Long subCourseTeacherId);
}

