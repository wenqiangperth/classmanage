package com.example.common.dao;

import com.example.common.entity.Team;
import com.example.common.entity.TeamAndOrStrategy;
import com.example.common.mapper.TeamOrStrategyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author perth
 * @ClassName TeamOrStrategyDao
 * @Description TODO
 * @Date 2018/12/26 0:54
 * @Version 1.0
 **/
@Repository
public class TeamOrStrategyDao {
    @Autowired
    private TeamOrStrategyMapper teamOrStrategyMapper;

    @Autowired
    private TeamStrategyDao teamStrategyDao;

    public boolean isTeamValid(Team team,Long strategyId){
        ArrayList<TeamAndOrStrategy>teamOrStrategies=teamOrStrategyMapper.selectTeamOrStrategyById(strategyId);
        for (TeamAndOrStrategy teamOrStrategy:teamOrStrategies
             ) {
            if(teamStrategyDao.isTeamStrategyValid(team,teamOrStrategy.getStrategyName(),teamOrStrategy.getStrategyId())){
                return true;
            }
        }
        return false;
    }
}
