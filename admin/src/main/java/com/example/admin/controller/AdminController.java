package com.example.admin.controller;

import com.example.admin.service.AdminService;
import com.example.common.entity.Administrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName AdminController
 * @Description 处理来自管理员前端的请求
 * @Author perth
 * @Date 2018/12/15 21:50
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    /**
     *
     * @param administrator
     * @return
     */
    @PostMapping(value = "/login")
    public String administratorLogin(@RequestBody Administrator administrator){
        System.out.println("jinru");
        return adminService.adminstratorLogin(administrator);
    }

}
