package com.example.common.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName AdministratorDao
 * @Description 这是一个管理员实体类
 * @Author perth
 * @Date 2018/12/15 17:21
 * @Version 1.0
 **/
@Data
public class Administrator {
    private Long id;
    private String account;
    private String password;
    private List<String> roles;

    public List<String> getRoles() {
        this.roles=new ArrayList<>(Arrays.asList("ROLE_ADMIN"));
        return roles;
    }
}
