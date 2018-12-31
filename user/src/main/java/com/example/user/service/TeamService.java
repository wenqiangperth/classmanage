package com.example.user.service;

import com.example.common.dao.*;
import com.example.common.entity.*;
import com.example.common.mapper.KlassMapper;
import com.example.common.mapper.TeamMapper;
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

    @Autowired
    private TeamStrategyDao teamStrategyDao;

    @Autowired
    private KlassDao klassDao;




    /**
     * 组队是否合法
     * @param teamId
     * @return
     */
    public boolean isTeamValid(Long teamId){
        return teamStrategyDao.isTeamValid(teamId);
    }

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
        Klass klass=klassDao.getClassByClassId(team.getKlassId());
        team.setKlassSerial(klass.getKlassSerial());
        team.setStatus(0);
        Long teamId=teamDao.addTeam(team);
        if(isTeamValid(team.getId())){
            team.setStatus(1);
        }else{
            team.setStatus(0);
        }
        Long status=0L;
        status=status+team.getStatus();
        teamDao.updateTeamStatus(teamId,status);
        return teamId;
    }

    /**
     * 根据ID获取小组
     * @param teamId
     * @return
     */
    public Team getTeamById(Long teamId,Long courseId){
        return teamDao.getTeamById(teamId,courseId);
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
     * 增加组员，设置分组是否合法
     * @param teamId
     * @param studentId
     * @return
     */
    public Long addTeamMemberById(Long teamId,Long studentId){
        Long i=teamDao.addTeamMemberById(teamId,studentId);
        if(isTeamValid(teamId)){
            teamDao.updateTeamStatus(teamId,1L);
        }else{
            teamDao.updateTeamStatus(teamId,0L);
        }
        return i;
    }

    /**
     * 删除组员,设置分组是否合法
     * @param teamId
     * @param studentId
     * @return
     */
    public Long removeTeamMember(Long teamId,Long studentId){
        Long i=teamDao.removeTeamMember(teamId,studentId);
        if(isTeamValid(teamId)){
            teamDao.updateTeamStatus(teamId,1L);
        }else{
            teamDao.updateTeamStatus(teamId,0L);
        }
        return i;
    }

    /**
     * 创建特殊组队申请
     * @param teamValidApplication
     * @return
     */
    public Long createTeamValisApplication(TeamValidApplication teamValidApplication){
        return teamDao.createTeamValidApplication(teamValidApplication);
    }

    /**
     * 同意特殊组队申请,设置status为1，合法
     * @param teamId
     * @return
     */
    public Long approveTeam(Long teamId){
        return teamDao.updateTeamStatus(teamId,1L);
    }

    /**
     * 修改设置队伍name
     * @param team
     * @return
     */
    public Long updateTeamName(Team team){
        return teamDao.updateTeamName(team);
    }

//    public ArrayList<Team> getAllTeamsByCourseId(Long courseId,Long klassId)
//    {
//        return teamDao.getAllTeamsByCourseId(klassId,courseId);
//    }

    public ArrayList<Long> getAllTeamIdByClassId(Long klassId)
    {
        return teamDao.getAllTeamIdByClassId(klassId);
    }

}
