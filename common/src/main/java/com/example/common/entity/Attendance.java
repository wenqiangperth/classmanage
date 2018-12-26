package com.example.common.entity;

import lombok.Data;

/**
 * @ClassName Attendance
 * @Description
 * @Author perth
 * @Date 2018/12/17 0017 下午 6:54
 * @Version 1.0
 **/

@Data
public class Attendance {
    private long id;
    private long klassSeminarId;
    private long teamId;
    private int teamOrder;
    private int isPresent;
    private String reportName;
    private String reportUrl;
    private String pptName;
    private String pptUrl;
    private Team team;
    private Score score;
}
