package com.example.common.entity;

import lombok.Data;

/**
 * @ClassName Team
 * @Description
 * @Author perth
 * @Date 2018/12/17 0017 下午 6:32
 * @Version 1.0
 **/

@Data
public class Team {
    private long id;
    private long klassId;
    private long courseId;
    private long leaderId;
    private String teamName;
    private int teamSerial;
    private int status;
}
