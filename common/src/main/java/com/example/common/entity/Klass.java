package com.example.common.entity;

import lombok.Data;

/**
 * @ClassName klass
 * @Description
 * @Author perth
 * @Date 2018/12/17 0017 下午 6:58
 * @Version 1.0
 **/

@Data
public class Klass {
    private long id;
    private long courseId;
    private int grade;
    private int klassSerial;
    private String klassTime;
    private String klassLocation;
    private KlassSeminar klassSeminar;
}