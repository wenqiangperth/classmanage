package com.example.common.entity;

import lombok.Data;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.util.Date;

/**
 * @ClassName Seminar
 * @Description
 * @Author perth
 * @Date 2018/12/17 0017 下午 6:45
 * @Version 1.0
 **/
@Data
public class Seminar {
    private Long id;
    private Long courseId;
    private Long roundId;
    private int roundSerial;
    private String seminarName;
    private String introduction;
    private int maxTeam;
    private int isVisible;
    private int seminarSerial;
    private Date enrollStartTime;
    private Date enrollEndTime;



}
