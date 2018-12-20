package com.example.admin.controller;

import com.example.admin.service.AdminService;
import com.example.common.entity.Administrator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminControllerTest {
    @Autowired
    private AdminService adminService;

    @Test
    public void administratorLogin() {
        Administrator administrator=new Administrator();
        administrator.setAccount("156156");
        administrator.setPassword("46198");
        String a=adminService.adminstratorLogin(administrator);
        System.out.println(a);
    }

    @Test
    public void aa() {

    }
}