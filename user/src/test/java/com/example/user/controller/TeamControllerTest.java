package com.example.user.controller;

import com.example.common.entity.Student;
import com.example.common.entity.Team;
import com.example.common.entity.TeamValidApplication;
import com.example.common.mapper.TeamMapper;
import com.example.user.service.TeamService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeamControllerTest {
    @Autowired
    private TeamService teamService;
    @Autowired
    private TeamMapper teamMapper;
    @Test
    public void getTeamById() {
        //Team team=teamService.getTeamById(8L);
       // System.out.println(team.toString());
    }
    @Test
    public void deleteTeamById(){
       // teamService.deleteTeamById(7L);
    }
    @Test
    public void addTeamMemer(){
//        Team team=new Team();
//        team.setCourseId(2);
//        team.setKlassId(3);
//        team.setLeaderId(20);
//        team.setTeamName("测bu试");
//        team.setTeamSerial(1);
//        team.setStatus(0);
//
//        teamService.addTeam(team);
//        System.out.println(team.getId());
    }

    @Test
    public void createTeamValid(){
        TeamValidApplication t=new TeamValidApplication();
        t.setReason("hahahha");
        t.setTeamId(3L);
        Long i=teamService.createTeamValisApplication(t);
        System.out.println(i);
    }
}