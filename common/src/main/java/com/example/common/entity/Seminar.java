package com.example.common.entity;

import lombok.Data;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.sql.Date;

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
    private String seminarName;
    private String introduction;
    private int maxTeam;
    private int isVisible;
    private int seminarSerial;
    private DateTimeLiteralExpression.DateTime enrollStartTime;
    private DateTimeLiteralExpression.DateTime enrollEndTime;

    public DateTimeLiteralExpression.DateTime getEnrollStartTime(){
        return enrollStartTime;
    }
    public DateTimeLiteralExpression.DateTime getEnrollEndTime(){
        return enrollEndTime;
    }

}
