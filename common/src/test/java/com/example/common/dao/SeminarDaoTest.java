package com.example.common.dao;

import com.example.common.entity.Attendance;
import com.example.common.entity.Score;
import com.example.common.entity.Seminar;
import com.example.common.entity.Team;
import com.example.common.mapper.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SeminarDaoTest {
    @Autowired
    private SeminarDao seminarDao;
    @Autowired
    private SeminarMapper seminarMapper;
    @Autowired
    private AttendanceMapper attendanceMapper;

    @Autowired
    private TeamMapper teamMapper;
    @Autowired
    private KlassMapper klassMapper;
    @Autowired
    private ScoreMapper scoreMapper;

    @Test
    public void aa(){
        ArrayList<Attendance>attendances=seminarDao.getAllAttendance(22L,23L);
        System.out.println(attendances);

//        Long classSeminarId=seminarMapper.getClassSeminarIdBySeminarIdAndClassId(23l,22l);
//        System.out.println(classSeminarId+"allala");
//        ArrayList<Attendance> attendances= attendanceMapper.getAllAttendanceByKlassSeminarId(classSeminarId);
//        System.out.println(attendances);
//        Seminar seminar=seminarMapper.getSeminarByKlassSeminarId(classSeminarId);
//        for(Attendance attendance:attendances)
//        {
//            Team team=teamMapper.selectTeamById(attendance.getTeamId());
//            team.setKlassSerial(klassMapper.getKlassByKlassId(team.getKlassId()).getKlassSerial());
//            attendance.setTeam(team);
//            Score score=scoreMapper.selectSeminarScoreByClassSeminarIdAndTeamId(classSeminarId,attendance.getTeamId());
//
//            System.out.println(score);
//            score.setSeminarName(seminar.getSeminarName());
//            attendance.setScore(score);
////            Score score1=new Score();
//            System.out.println("之前"+seminar.getSeminarName());
//            double a=score.getPresentationScore();
////           score1.setPresentationScore(a);
//           // score.setSeminarName("dadas");
////            score1.setSeminarName(seminar.getSeminarName());
//        }
//        //ArrayList<Attendance>attendances=seminarDao.getAllAttendance(5L,22L);
//        System.out.println(attendances);
    }

    @Test
    public void addSeminar() {
        Seminar seminar=new Seminar();
        seminar.setCourseId(5L);
        seminar.setSeminarName("加油");
        seminar.setIntroduction("best");
        seminar.setMaxTeam(5);
        seminar.setIsVisible(1);
        Long i=seminarDao.addSeminar(seminar);
        System.out.println(i);
    }
}