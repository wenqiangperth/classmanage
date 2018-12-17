package com.example.common.entity;

import lombok.Data;

/**
 * @ClassName Round
 * @Description
 * @Author perth
 * @Date 2018/12/17 0017 下午 7:36
 * @Version 1.0
 **/

@Data
public class Round {
    private long id;
    private long courseId;
    private int roundSerial;
    private int presentationScoreMethod;
    private int reportScoreMethod;
    private int questionScoreMethod;
}
