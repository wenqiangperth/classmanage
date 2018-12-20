package com.example.user.controller;

import com.example.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author perth
 * @ClassName UserController
 * @Description TODO
 * @Date 2018/12/17 19:04
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;






}
