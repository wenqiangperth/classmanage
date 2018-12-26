package com.example.user.controller;

import com.example.common.entity.*;
import com.example.user.service.SeminarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;


//@RunWith(SpringRunner.class)
@SpringBootTest
public class SeminarControllerTest {
    @Autowired
    private SeminarService seminarService;

    @Test
    public void b(){
//        Attendance attendance=seminarService.getAtteandanceByTeamOrderAndKlassSeminarId(9L,2);
//        System.out.println(attendance);
    }

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

    @Test
    public void updateSeminar(){
        KlassSeminar klassSeminar=new KlassSeminar();
        klassSeminar.setSeminarId(5L);
        klassSeminar.setKlassId(2L);
        klassSeminar.setStatus(1);
        Long j=seminarService.updateSeminarStatus(klassSeminar);
        System.out.println(j);
//        Long i=seminarService.updateSeminarRoundId(2L,8L);
//        System.out.println(i);
    }

    @Test
    public void score(){
        //Team team =seminarService.getTeamSeminarSocre(3L,5L);
        Score score=new Score();
        score.setPresentationScore(4.5);
       score.setQuestionScore(5);
        score.setReportScore(3);
        score.setTeamId(3L);
        Long i=seminarService.updateTeamSeminarScore(score,5L);
       // System.out.println(team);
        System.out.println(i);

    }
    @Test
    public void getSeminarScore(){
        ArrayList<Team>teams=seminarService.getSeminarScore(2l,5L);
        System.out.println(teams);
    }
}