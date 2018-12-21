package com.example.user.service;

import com.example.common.dao.StudentDao;
import com.example.common.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author perth
 * @ClassName StudentService
 * @Description TODO
 * @Date 2018/12/17 19:05
 * @Version 1.0
 **/
@Service
public class StudentService {
    @Autowired
    private StudentDao studentDao;

    /**
     * 更新：学生激活
     * @param student
     * @return
     */
    public Long activeStudentActive(Student student){
        return studentDao.updateStudentActive(student);
    }

}
