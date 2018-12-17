package com.example.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName UserApplication
 * @Description main
 * @author perth
 * @Date 2018/12/15 21:50
 * @Version 1.0
 **/
@SpringBootApplication(scanBasePackages = {"com.example.user","com.example.common"})
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}

