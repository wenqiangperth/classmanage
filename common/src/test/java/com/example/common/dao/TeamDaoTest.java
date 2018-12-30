package com.example.common.dao;

import com.example.common.entity.Team;
import com.example.common.entity.TeamStrategy;
import com.example.common.mapper.TeamMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeamDaoTest {
    @Autowired
    private TeamDao teamDao;

    @Autowired
    private TeamMapper teamMapper;
    @Test
    public void deleteTeamById() {
        Team team=teamDao.getTeamByTeamId(8L);
        System.out.println(team.toString());
    }

    @Test
    public void aa(){
//        ConflictCourseStrstegy a =teamMapper.getConflictCourseId(1L);
////        System.out.println(a.toString());
//        TeamStrategy teamStrategy=teamMapper.getTeamStrategy(1L);
//        System.out.println(teamStrategy.toString());
    }
}