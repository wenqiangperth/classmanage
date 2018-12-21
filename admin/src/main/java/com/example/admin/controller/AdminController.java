package com.example.admin.controller;

import com.example.admin.service.AdminService;
import com.example.common.entity.Administrator;
import com.example.common.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * @ClassName AdminController
 * @Description 处理来自管理员前端的请求
 * @Author perth
 * @Date 2018/12/15 21:50
 * @Version 1.0
 **/
@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;

    /**
     * 分页获取学生
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/student")
    public ArrayList<Student>getAllStudent(@RequestParam(name = "pageNum")int pageNum,@RequestParam(name = "pageSize")int pageSize){
        return adminService.getAllStudent(pageNum,pageSize);
    }

    /**
     * 查询：name or account->student
     * @param accountOrName
     * @return
     */
    @GetMapping(value = "/student/searchstudent")
    public ArrayList<Student>getStudentByAccountOrName(@RequestParam(name = "accountOrName")String accountOrName){
        return adminService.getStudentByAccountOrName(accountOrName);
    }

    /**
     * 更新：学生信息
     * @param studentId
     * @param student
     * @return
     */
    @PutMapping(value = "/student/{studentId}/information")
    public Student updateStudentInformation(@PathVariable("studentId")Long studentId, @RequestBody Student student){
        student.setId(studentId);
        if(adminService.updateStudentInformation(student)>0){
            return student;
        }
        return null;
    }

    /**
     * 更新:学生密码
     * @param studentId
     * @param password
     * @return
     */
    @PutMapping(value = "/student/{studentId}/password")
    public Long updateStudentPassword(@PathVariable("studentId")Long studentId,@RequestParam(name = "password")String password){
        return adminService.updateStudentPassword(studentId,password);
    }

    /**
     * 更新：学生激活
     * @param request
     * @param student
     * @return
     */
    @PutMapping(value = "/student/active")
    public Long updateStudentActive(HttpServletRequest request,@RequestBody Student student){
        student.setId((Long)request.getAttribute("id"));
        student.setIsActive(1);
        return adminService.updateStudentActive(student);
    }

    /**
     * 删除：学生
     * @param studentId
     * @return
     */
    @DeleteMapping(value = "/student/{studentId}")
    public Long deleteStudent(@PathVariable("studentId")Long studentId){
        return adminService.deleteStudentById(studentId);
    }

    /**
     *这是管理员登陆
     * @param administrator
     * @return
     */
    @PostMapping(value = "/admin/login")
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
