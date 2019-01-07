package com.example.common.entity;

import lombok.Data;

import java.util.ArrayList;

/**
 * @author perth
 * @ClassName ScoreDTO
 * @Description 接收数据
 * @Date 2019/1/1 18:00
 * @Version 1.0
 **/
@Data
public class ScoreDTO {
    ArrayList<Attendance> attendances;
}
