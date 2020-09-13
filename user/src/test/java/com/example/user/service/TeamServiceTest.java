package com.example.user.service;

import com.example.user.UserApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApplication.class)
public class TeamServiceTest {

    @Autowired
    private TeamService teamService;



    @Test
    public void aa(){
        teamService.addTeamMemberById(22L,150L);
    }


}