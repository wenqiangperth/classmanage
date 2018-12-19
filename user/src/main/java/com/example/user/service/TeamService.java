package com.example.user.service;

import com.example.common.dao.TeamDao;
import com.example.common.entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author perth
 * @ClassName TeamService
 * @Description 处理Team的事务
 * @Date 2018/12/17 19:08
 * @Version 1.0
 **/
@Service
public class TeamService {
    @Autowired
    private TeamDao teamDao;

    /**
     * 创建小组，设置小组序号
     * @param team
     * @return小组ID
     */
    public Long addTeam(Team team){
        ArrayList<Team> teams=teamDao.getAllTeamsByCourseId(team.getKlassId(),team.getCourseId());
        int teamSerial=1;
        for (Team team1:teams
             ) {
            if(teamSerial<team1.getTeamSerial()){teamSerial=team1.getTeamSerial();}
        }
        team.setTeamSerial(teamSerial);
        return teamDao.addTeam(team);
    }

    /**
     * 根据ID获取小组
     * @param teamId
     * @return
     */
    public Team getTeamById(Long teamId){
        return teamDao.getTeamById(teamId);
    }

    /**
     * 根据ID删除小组
     * @param id
     * @return
     */
    public Long deleteTeamById(Long id){
        return teamDao.deleteTeamById(id);
    }

    /**
     * 增加组员
     * @param teamId
     * @param studentId
     * @return
     */
    public Long addTeamMemberById(Long teamId,Long studentId){
        return teamDao.addTeamMemberById(teamId,studentId);
    }

    public Long removeTeamMember(Long teamId,Long studentId){
        return teamDao.removeTeamMember(teamId,studentId);
    }
}
