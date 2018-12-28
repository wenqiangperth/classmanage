package com.example.common.entity;

import lombok.Data;

/**
 * @ClassName CourseLimit
 * @Description
 * @Author perth
 * @Date 2018/12/27 0027 上午 10:40
 * @Version 1.0
 **/
@Data
public class CourseLimit {
    private Long id;
    private Long courseId;
    private Long teamMinCount;
    private Long teamMaxCount;

}
