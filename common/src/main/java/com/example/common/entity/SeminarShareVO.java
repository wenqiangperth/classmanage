package com.example.common.entity;

import lombok.Data;

/**
 * @ClassName SeminarShareVO
 * @Description
 * @Author perth
 * @Date 2018/12/19 0019 下午 9:27
 * @Version 1.0
 **/
@Data
public class SeminarShareVO {
    private long id;
    private long mainCourseId;
    private long mainCourseTeacherId;
    private String mainCourseName;
    private String mainCourseTeacherName;
    private long subCourseId;
    private long subCourseTeacherId;
    private String subCourseName;
    private String subCourseTeacherName;
    private boolean status;
}