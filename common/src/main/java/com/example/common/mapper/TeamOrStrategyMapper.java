package com.example.common.mapper;


import com.example.common.entity.TeamAndOrStrategy;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface TeamOrStrategyMapper {

    @Select("select * from team_or_strategy where id=#{id}")
    @Results({
            @Result(property = "strategyName",column = "strategy_name"),
            @Result(property = "strategyId",column = "strategy_id")
    })
    public ArrayList<TeamAndOrStrategy>selectTeamOrStrategyById(@Param("id")Long id);
}
