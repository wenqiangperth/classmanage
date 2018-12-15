package com.example.common.entity;

import lombok.Data;

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
}
