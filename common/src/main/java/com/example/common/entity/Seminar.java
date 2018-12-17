package com.example.common.entity;

import lombok.Data;
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
    private long id;
    private long courseId;
    private long roundId;
    private String seminarName;
    private String introduction;
    private int maxTeam;
    private boolean isVisible;
    private int seminarSerial;
    private Date enrollStartTime;
    private Date enrollEndTime;

}
