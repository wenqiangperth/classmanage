package com.example.user.controller;

import com.example.common.entity.Teacher;
import com.example.user.service.TeacherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeacherControllerTest {
    @Autowired
    private TeacherService teacherService;


    @Test
    public void activeTeacher() {
        Teacher teacher=new Teacher();
        teacher.setId(3L);
        teacher.setIsActive(1);
        teacher.setEmail("18356165@163.com");
        teacher.setPassword("llkkkkqqqqaaa");
        Long i=teacherService.activeTeacher(teacher);
        System.out.println(i);
    }
}