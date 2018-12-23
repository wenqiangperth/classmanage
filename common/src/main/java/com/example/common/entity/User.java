package com.example.common.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    private List<String> roles;
    private String name;
    private int isActived;
    private String email;
    private String oldPassword;

    public void setUserByAdministrator(Administrator administrator){
        setId(administrator.getId());
        setAccount(administrator.getAccount());
        setPassword(administrator.getPassword());
        this.roles=new ArrayList<>(Arrays.asList("ROLE_ADMIN"));
    }

    public void setUserByStudent(Student student){
        setId(student.getId());
        setAccount(student.getAccount());
        setPassword(student.getPassword());
        this.roles=new ArrayList<>(Arrays.asList("ROLE_STUDENT"));
        setName(student.getStudentName());
        setIsActived(student.getIsActive());
        setEmail(student.getEmail());
    }

    public void setUserByTeacher(Teacher teacher){
        setId(teacher.getId());
        setAccount(teacher.getAccount());
        setPassword(teacher.getPassword());
        this.roles=new ArrayList<>(Arrays.asList("ROLE_TEACHER"));
        setName(teacher.getTeacherName());
        setIsActived(teacher.getIsActive());
        setEmail(teacher.getEmail());
    }
}
