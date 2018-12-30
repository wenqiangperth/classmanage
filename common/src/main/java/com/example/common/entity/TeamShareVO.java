package com.example.common.entity;

import lombok.Data;

/**
 * @ClassName TeamShareVO
 * @Description
 * @Author perth
 * @Date 2018/12/19 0019 下午 9:03
 * @Version 1.0
 **/

@Data
public class TeamShareVO {
    private Long id;
    private Long mainCourseId;
    private Long mainCourseTeacherId;
    private String mainCourseName;
    private String mainCourseTeacherName;
    private Long subCourseId;
    private Long subCourseTeacherId;
    private String subCourseName;
    private String subCourseTeacherName;
    private Long status;
    private Integer mainCourse;

    public void setStatus(Long tempStatus)
    {
        this.status=tempStatus;
    }

    public Long getStatus()
    {
        return this.status;
    }

    public void setMainCourse(int tempMainCourse)
    {
        this.mainCourse=tempMainCourse;
    }

    public int getMainCourse()
    {
        return this.mainCourse;
    }
}
