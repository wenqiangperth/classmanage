package com.example.user.controller;

import com.example.common.entity.Teacher;
import com.example.user.service.TeacherService;
import org.apache.ibatis.annotations.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author perth
 * @ClassName TeacherController
 * @Description TODO
 * @Date 2018/12/17 19:03
 * @Version 1.0
 **/
@RestController
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    /**
     * 更新:teacher激活
     * @param request
     * @param teacher
     * @return
     */
    @PutMapping(value = "/teacher/active")
    public Long activeTeacher(HttpServletRequest request, @RequestBody Teacher teacher){
        teacher.setId(Long.parseLong(request.getAttribute("id").toString()));
        teacher.setIsActive(1);
        return teacherService.activeTeacher(teacher);
    }

//    /**
//     * 删除班级以及关系
//     * @param klassId
//     * @return
//     */
//    @DeleteMapping(value = "/class/{classId}")
//    public Long deleteKlass(@PathVariable(name = "classId")Long klassId){
//        return teacherService.deleteKlassById(klassId);
//    }

}
