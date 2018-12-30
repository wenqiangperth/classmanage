package com.example.common.entity;

import lombok.Data;

/**
 * @ClassName TeamValidVO
 * @Description
 * @Author perth
 * @Date 2018/12/22 0022 上午 10:27
 * @Version 1.0
 **/
@Data
public class TeamValidVO {
    private Long id;
    private Long teamId;
    private Team team;
    private Long teacherId;
    private Course course;
    private Klass klass;
    private String reason;
    private Long status;
}
