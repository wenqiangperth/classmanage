package com.example.common.entity;
import lombok.Data;

/**
 * @ClassName Student
 * @Description 这是一个学生实体类
 * @Author perth
 * @Date 2018/12/15 17:21
 * @Version 1.0
 **/


@Data
public class Student {
    private Long id;
    private String account;
    private String password;
    private int isActive;
    private String studentName;
    private String email;


}
