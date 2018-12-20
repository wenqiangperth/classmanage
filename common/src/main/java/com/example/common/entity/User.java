package com.example.common.entity;

import lombok.Data;

/**
 * @author perth
 * @ClassName User
 * @Description 用户类
 * @Date 2018/12/20 18:19
 * @Version 1.0
 **/
@Data
public class User {
    private Long id;
    private String account;
    private String password;
    private String role;
    private String name;
    private int isActived;
    private String email;

    public void setUserByStudent(Student student){
        setId(student.getId());
        setAccount(student.getAccount());
        setPassword(student.getPassword());
        setRole("student");
        setName(student.getStudentName());
        setIsActived(student.getIsActive());
        setEmail(student.getEmail());
    }

    public void setUserByTeacher(Teacher teacher){
        setId(teacher.getId());
        setAccount(teacher.getAccount());
        setPassword(teacher.getPassword());
        setRole("teacher");
        setName(teacher.getTeacherName());
        setIsActived(teacher.getIsActive());
        setEmail(teacher.getEmail());
    }
}
