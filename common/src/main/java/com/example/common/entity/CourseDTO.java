package com.example.common.entity;

import lombok.Data;

import java.util.ArrayList;

/**
 * @ClassName CourseDTO
 * @Description
 * @Author perth
 * @Date 2018/12/27 0027 上午 10:35
 * @Version 1.0
 **/
@Data
public class CourseDTO {
    private Course course;
    private Long minCount;
    private Long maxCount;
    private ArrayList<CourseLimit> courseLimit;
    private int flag;
    private ArrayList<ArrayList<Long>> conflictCourseId;
}
