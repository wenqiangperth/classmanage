package com.example.user.controller;

import com.example.common.entity.Seminar;
import com.example.user.service.SeminarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SeminarControllerTest {
    @Autowired
    private SeminarService seminarService;

    @Test
    public void createSeminar() {
        Seminar seminar=new Seminar();
        seminar.setSeminarName("加油");
        seminar.setIntroduction("the best");
        seminar.setMaxTeam(6);
        seminar.setIsVisible(1);
        seminar.setRoundId(4L);
        seminar.setSeminarSerial(2);
        seminar.setId(10L);

        Long i= seminarService.updateSeminar(seminar);
        System.out.println(i);
    }
    @Test
    public void getSeminar(){
        Seminar seminar=seminarService.getSeminarById(5L);
        System.out.println(seminar);
    }
}