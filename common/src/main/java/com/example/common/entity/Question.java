package com.example.common.entity;

import lombok.Data;

/**
 * @ClassName Question
 * @Description
 * @Author perth
 * @Date 2018/12/17 0017 下午 7:39
 * @Version 1.0
 **/

@Data
public class Question {
    private long id;
    private long klassSeminarId;
    private long attendanceId;
    private long teamId;
    private long studentId;
    private int isSelected;
    private double score;
    private Team team;
}
