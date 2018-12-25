package com.example.common.dao;

import com.example.common.entity.Team;
import com.example.common.entity.TeamStrategy;
import com.example.common.mapper.TeamMapper;
import com.example.common.mapper.TeamStrategyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author perth
 * @ClassName TeamStrategyDao
 * @Description TODO
 * @Date 2018/12/25 23:53
 * @Version 1.0
 **/
@Repository
public class TeamStrategyDao {
    @Autowired
    private TeamStrategyMapper teamStrategyMapper;

    @Autowired
    private TeamAndStrategyDao teamAndStrategyDao;

    @Autowired
    private TeamOrStrategyDao teamOrStrategyDao;

    @Autowired
    private ConflictCourseStrategyDao conflictCourseStrategyDao;

    @Autowired
    private CourseMemberLimitStrategyDao courseMemberLimitStrategyDao;

    @Autowired
    private MemberLimitStrategyDao memberLimitStrategyDao;

    @Autowired
    private TeamMapper teamMapper;

    public boolean isTeamValid(Long teamId){
        Team team=teamMapper.selectTeamById(teamId);
        ArrayList<TeamStrategy>teamStrategies=teamStrategyMapper.selectTeamStrategyByCourseId(team.getCourseId());
        for (TeamStrategy teamStrategy:teamStrategies
             ) {
            if(!isTeamStrategyValid(team,teamStrategy.getStrategyName(),teamStrategy.getStrategyId())){
                return false;
            }
        }
        return true;
    }

    public boolean isTeamStrategyValid(Team team,String strategyName,Long strategyId){
        boolean isok=false;
        switch (strategyName){
            case "TeamAndStrategy":isok=teamAndStrategyDao.isTeamValid(team,strategyId);break;
            case "TeamOrStrategy":isok=teamOrStrategyDao.isTeamValid(team,strategyId);break;
            case "CourseMemberLimitStrategy":isok=courseMemberLimitStrategyDao.isTeamValid(team,strategyId);break;
            case "ConflictCourseStrategy":isok=conflictCourseStrategyDao.isTeamValid(team,strategyId);break;
            case "MemberLimitStrategy":isok=memberLimitStrategyDao.isTeamValid(team,strategyId);break;
        }
        return isok;
    }
}
