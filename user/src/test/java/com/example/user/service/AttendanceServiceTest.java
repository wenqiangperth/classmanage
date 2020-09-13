package com.example.user.service;

import com.example.common.dao.AttendanceDao;
import com.example.common.entity.Question;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AttendanceServiceTest {

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private AttendanceDao attendanceDao;

    @Test
    public void a(){
        ArrayList<Question>questions=attendanceDao.getAllQuestionByAttendanceId(33L);
        Question q=attendanceService.getQuestionByAttendanceId(33L);
        System.out.println(questions);
        System.out.println("pp");
        System.out.println(q);
    }

}