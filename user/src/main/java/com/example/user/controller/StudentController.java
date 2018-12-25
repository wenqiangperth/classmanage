package com.example.user.controller;

import com.example.common.entity.Student;
import com.example.user.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
/**
 * @author perth
 * @ClassName StudentController
 * @Description TODO
 * @Date 2018/12/17 19:04
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    /**
     * 更新：学生激活
     * @param request
     * @param student
     * @return
     */
    @PutMapping(value = "/active")
    public Long activeStudent(HttpServletRequest request, @RequestBody Student student){
        student.setId(Long.parseLong(request.getAttribute("id").toString()));
        student.setIsActive(1);
        return studentService.activeStudentActive(student);
    }

}
