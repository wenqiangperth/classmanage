package com.example.user.controller;

import com.example.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author perth
 * @ClassName UserController
 * @Description 处理User事务(老师学生共有的)
 * @Date 2018/12/17 19:04
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;


    /**
     * 修改用户密码
     * @param request
     * @param password
     * @return
     */
    @PutMapping(value = "/password")
    public Long changeUserPassword(HttpServletRequest request,@RequestBody String password){
        String role=(String)request.getAttribute("role");
        Long id=(Long)request.getAttribute("id");
        return userService.changeUserPassword(id,role,password);
    }




}
