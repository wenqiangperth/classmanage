package com.example.admin.controller;

import com.example.admin.service.AdminService;
import com.example.common.entity.Administrator;
import com.example.common.entity.Student;
import com.example.common.entity.Teacher;
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
//        Administrator administrator=new Administrator();
//        administrator.setAccount("156156");
//        administrator.setPassword("46198");
//        String a=adminService.adminstratorLogin(administrator);
//        System.out.println(a);
    }
//
//    @Test
//    public void getAllStudent() {
//        ArrayList<Student>students=adminService.getAllStudent(2,1);
//        System.out.println(students);
//    }
//
//    @Test
//    public void getStudent(){
//        ArrayList<Student>students=adminService.getStudentByAccountOrName("5577");
//        System.out.println(students);
//    }
//
//    @Test
//    public void updateStudent(){
//        Student student=new Student();
//        student.setId(124L);
//       // student.setAccount("6666");
//        student.setEmail("4654165@qq.com");
//        student.setIsActive(0);
//        student.setPassword("pppqqqq");
//
//        adminService.updateStudentInformation(student);
//    }
//    @Test
//    public void updatePassword(){
//        //adminService.updateStudentPassword(126L,"aaaqqq");
//
//    }
//    @Test
//    public void deleteStudent(){
//        adminService.deleteStudentById(24L);
//    }
//
//    @Test
//    public void addTeacher(){
//        Teacher teacher=new Teacher();
//        teacher.setAccount("151651");
//        teacher.setEmail("156535@163.com");
//        teacher.setPassword("fdadsg");
//        teacher.setTeacherName("龙");
//        System.out.println(adminService.addTeacher(teacher));
//    }
//
//    @Test
//    public void getAllTeacher(){
//        ArrayList<Teacher>teachers=adminService.getAllTeacher(1,2);
//        System.out.println(teachers);
//    }
//
//    @Test
//    public void getTeacher(){
//        ArrayList<Teacher>teachers=adminService.getTeacherByAccountOrName("龙");
//        System.out.println(teachers);
//    }
//    @Test
//    public void updateTeacher(){
//        Teacher teacher=new Teacher();
//        teacher.setId(3L);
//        teacher.setEmail("156655486@qq.com");
//        teacher.setAccount("446699");
//        teacher.setTeacherName("李四");
//        Long i=adminService.updateTeacherInformation(teacher);
//    }
//
//    @Test
//    public void updateTeacherPassword(){
//        Long i=adminService.updateTeacherPassword(5L,"llpppqqq");
//        System.out.println(i);
//    }
//    @Test
//    public void deleteTeacher(){
//        Long i=adminService.deleteTeacherById(3L);
//        System.out.println(i);
//    }
}