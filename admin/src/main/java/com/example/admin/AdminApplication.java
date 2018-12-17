package com.example.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName AdminApplication
 * @Description main
 * @author perth
 * @Date 2018/12/15 21:50
 * @Version 1.0
 **/
@SpringBootApplication(scanBasePackages = {"com.example.admin","com.example.common"})
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}
