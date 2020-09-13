package com.example.user.controller;

import com.example.common.entity.Klass;
import com.example.common.entity.Team;
import com.example.common.mapper.KlassMapper;
import com.example.user.service.CourseService;
import com.example.user.service.TeamService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseControllerTest {
    @Autowired
    private CourseService courseService;

    @Autowired
    private TeamService teamService;
    @Autowired
    private KlassMapper klassMapper;

    @Test
    public void aa(){
        ArrayList<Team>teams=courseService.getAllTeamByCourseId(16L);
        System.out.println(teams);
    }

    @Test
    public void bb(){
        Team team=teamService.getTeamById(2L,16L);
        System.out.println(team);
    }

    @Test
    public void cc(){
      Klass klass=klassMapper.getKlassByCourseIdAndStudentId(16L,211L);
        System.out.println(klass);
    }

}