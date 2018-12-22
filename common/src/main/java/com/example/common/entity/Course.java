package com.example.common.entity;

import lombok.Data;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.util.ArrayList;
import java.util.Date;
/**
 * @ClassName Course
 * @Description
 * @Author perth
 * @Date 2018/12/17 0017 下午 6:35
 * @Version 1.0
 **/

@Data
public class Course {
    private long id;
    private long teacherId;
    private String courseName;
    private String introduction;
    private int presentationPercentage;
    private int questionPercentage;
    private int reportPercentage;
    private Date teamStartTime;
    private Date teamEndTime;
    private long teamMainCourseId;
    private long seminarMainCourseId;
    private ArrayList<Team> teams;

}
