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
    private long subCourseId;
    private long subCourseTeacherId;
    private boolean status;
}
