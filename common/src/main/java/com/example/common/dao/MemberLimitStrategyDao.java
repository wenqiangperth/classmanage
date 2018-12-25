package com.example.common.dao;

import com.example.common.entity.MemberLimitStrategy;
import com.example.common.entity.Team;
import com.example.common.mapper.MemberLimitStrategyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author perth
 * @ClassName MemberLimitStrategyDao
 * @Description TODO
 * @Date 2018/12/25 20:42
 * @Version 1.0
 **/
@Repository
public class MemberLimitStrategyDao {
    @Autowired
    private MemberLimitStrategyMapper memberLimitStrategyMapper;

    public boolean isTeamValid(Team team, Long strategyId){
        MemberLimitStrategy memberLimitStrategy=memberLimitStrategyMapper.selectMemberLimitStrategyById(strategyId);
        if(memberLimitStrategy==null){
            return true;
        }
        int num=team.getStudents().size();
        if(memberLimitStrategy.getMinMember()<=num){
            if(memberLimitStrategy.getMaxMember()<=0){
                return true;
            }else if(memberLimitStrategy.getMaxMember()>=num){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }
}
