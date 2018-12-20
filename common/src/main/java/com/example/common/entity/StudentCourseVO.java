package com.example.common.entity;

import lombok.Data;

/**
 * @ClassName StudentCourseVO
 * @Description
 * @Author perth
 * @Date 2018/12/20 0020 下午 8:12
 * @Version 1.0
 **/
@Data
public class StudentCourseVO {
    private Long courseId;
    private String courseName;
    private Long klassId;
    private Klass klass;
    private Long studentId;
    private Long teamId;
}
