package com.example.user.controller;

import com.example.common.entity.User;
import com.example.user.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
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
     * @param user
     * @return
     */
    @PutMapping(value = "/password")
    public Long changeUserPassword(HttpServletRequest request,@RequestBody User user){
        System.out.println("lllaaa");
        String role=(String)request.getAttribute("role");
        Long id=Long.parseLong(request.getAttribute("id").toString());
        user.setId(id);
        System.out.println(user);
        System.out.println(role);
        return userService.changeUserPassword(user,role);
    }

    /**
     * 获得用户密码
     * @param account
     * @return
     * @throws MessagingException
     */
    @GetMapping(value = "/password")
    public Long getUserPassword(@RequestParam(value = "account") String account) throws MessagingException {
        //String role=(String)request.getAttribute("role");
        //Long id=(Long)request.getAttribute("id");
        return userService.getUserPassword(account);
    }

    /**
     * 获得用户个人信息
     * @param request
     * @return
     */
    @GetMapping(value = "/information")
    public User getUserInformation(HttpServletRequest request){
        String role=(String)request.getAttribute("role");
        Long id=Long.parseLong(request.getAttribute("id").toString());
        return userService.getUserById(id,role);
    }

    /**
     * 修改用户邮箱
     * @param request
     * @param user
     * @return
     */
    @PutMapping(value = "/email")
    public Long changUserEmail(HttpServletRequest request,@RequestBody User user){
        String role=(String)request.getAttribute("role");
        Long id=Long.parseLong(request.getAttribute("id").toString());
        System.out.println(user);
        System.out.println(id+"   "+role);
        return userService.changeUserEmail(id,role,user.getEmail());
    }



}
