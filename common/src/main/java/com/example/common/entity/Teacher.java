package com.example.common.entity;

import lombok.Data;

/**
 * @ClassName Teacher
 * @Description
 * @Author perth
 * @Date 2018/12/17 0017 下午 6:30
 * @Version 1.0
 **/

@Data
public class Teacher {
    private long id;
    private String account;
    private String password;
    private String teacherName;
    private int isActive;
    private String email;
}
