package com.example.user.controller;

import com.example.common.entity.User;
import com.example.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
    @Autowired
    private UserService userService;

    @Test
    public void changeUserPassword() {
//        Long i=userService.changeUserPassword(3L,"teacher","perth");
//        System.out.println(i);
    }

    @Test
    public void getUserById(){
        User user=userService.getUserById(24L,"student");
        if(user!=null){
        System.out.println(user.toString());
        }
    }

    /*@Test
    public void getUserPassWord() throws MessagingException
    {
        Long i = userService.getUserPassword(8L,"student");
        System.out.print(i);
    }
*/
    @Test
    public void changUserEmailTest() {
        Long i = userService.changeUserEmail(3L, "teacher", "21516515@163.com");
        System.out.println(i);
    }
}