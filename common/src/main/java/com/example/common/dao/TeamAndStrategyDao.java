package com.example.common.dao;

import com.example.common.entity.Team;
import com.example.common.entity.TeamAndOrStrategy;
import com.example.common.mapper.TeamAndStrategyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author perth
 * @ClassName TeamAndStrategyDao
 * @Description TODO
 * @Date 2018/12/25 23:51
 * @Version 1.0
 **/
@Repository
public class TeamAndStrategyDao {
    @Autowired
    private TeamAndStrategyMapper teamAndStrategyMapper;

    @Autowired
    private TeamStrategyDao teamStrategyDao;

    public boolean isTeamValid(Team team, Long strategyId){
        ArrayList<TeamAndOrStrategy> teamAndStrategies=teamAndStrategyMapper.selectTeamAndStrategyById(strategyId);
        if(teamAndStrategies==null){
            return true;
        }
        for (TeamAndOrStrategy teamAndStrategy:teamAndStrategies
        ) {
            if(!teamStrategyDao.isTeamStrategyValid(team,teamAndStrategy.getStrategyName(),teamAndStrategy.getStrategyId())){
                return false;
            }
        }
        return true;
    }
}
