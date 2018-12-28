package com.example.common.entity;

import lombok.Data;

/**
 * @ClassName CourseVO
 * @Description
 * @Author perth
 * @Date 2018/12/20 0020 下午 8:12
 * @Version 1.0
 **/
@Data
public class CourseVO {
    private Long courseId;
    private String courseName;
    private Long klassId;
    private Klass klass;
    private Long studentOrTeacherId;
    private Long teamMainCourseId;
    private Long seminarMainCourseId;
    private Long teamId;
    private String studentOrTeacherName;


    public void setCourseVOByCourse(Course course){
        courseId=course.getId();
        courseName=course.getCourseName();
        studentOrTeacherId=course.getTeacherId();
        teamMainCourseId=course.getTeamMainCourseId();
        seminarMainCourseId=course.getSeminarMainCourseId();
    }
}
