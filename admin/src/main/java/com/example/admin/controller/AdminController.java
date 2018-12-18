package com.example.admin.controller;

import com.example.admin.service.AdminService;
import com.example.common.entity.Administrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    @GetMapping(value = "/teacher")
    public String aa(HttpServletRequest request){
        System.out.println("teachers");
        //这是示范获取token中的id,我在过滤器中，将id已经set进入了httpServletRequest中
        System.out.println("拉拉："+request.getAttribute("id"));
        return "perth";
    }

}
