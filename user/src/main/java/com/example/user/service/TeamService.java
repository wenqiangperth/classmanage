package com.example.user.service;

import com.example.common.dao.TeamDao;
import com.example.common.entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author perth
 * @ClassName TeamService
 * @Description TODO
 * @Date 2018/12/17 19:08
 * @Version 1.0
 **/
@Service
public class TeamService {
    @Autowired
    private TeamDao teamDao;

    public Long addTeam(Team team){
        team.setTeamSerial(0);
        return teamDao.addTeam(team);
    }
}
