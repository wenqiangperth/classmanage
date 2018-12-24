package com.example.common.mapper;

import com.example.common.entity.Team;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeamMapperTest {
    @Autowired
    private TeamMapper teamMapper;

    @Test
    public void insertTeam() {
        Team team=new Team();
        team.setCourseId(2);
        team.setKlassId(1);
        team.setLeaderId(23);
        team.setTeamName("aaa测试");
        team.setTeamSerial(3);
        team.setStatus(0);
        //Long i=teamMapper.aa(team);
        System.out.println(team.getId());
        //System.out.println(i);
    }
    @Test
    public void selectTeam(){
        //teamMapper.selectTeamsByCourseIdAndClassId();
    }
}