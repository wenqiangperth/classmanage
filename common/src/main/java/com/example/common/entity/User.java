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
}
