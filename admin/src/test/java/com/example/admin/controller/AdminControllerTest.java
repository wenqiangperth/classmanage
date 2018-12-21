package com.example.admin.controller;

import com.example.admin.service.AdminService;
import com.example.common.entity.Administrator;
import com.example.common.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

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
    public void getAllStudent() {
        ArrayList<Student>students=adminService.getAllStudent(2,1);
        System.out.println(students);
    }

    @Test
    public void getStudent(){
        ArrayList<Student>students=adminService.getStudentByAccountOrName("5577");
        System.out.println(students);
    }

    @Test
    public void updateStudent(){
        Student student=new Student();
        student.setId(124L);
       // student.setAccount("6666");
        student.setEmail("4654165@qq.com");
        student.setIsActive(0);
        student.setPassword("pppqqqq");
        Long i=adminService.updateStudentActive(student);
        System.out.println(i);
        //adminService.updateStudentInformation(student);
    }
    @Test
    public void updatePassword(){
        //adminService.updateStudentPassword(126L,"aaaqqq");

    }
    @Test
    public void deleteStudent(){
        adminService.deleteStudentById(24L);
    }
}