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
    private boolean mainCourse;

    public void setStatus(boolean Status)
    {
        this.status=Status;
    }

    public boolean getStatus()
    {
        return this.status;
    }

    public void setMainCourse(boolean MainCourse)
    {
        this.mainCourse=MainCourse;
    }

    public boolean getMainCourse()
    {
        return this.mainCourse;
    }
}
