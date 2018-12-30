package com.example.common.mapper;


import com.example.common.entity.TeamAndOrStrategy;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface TeamOrStrategyMapper {

    /**
     * 通过id查组队策略
     * @param id
     * @return
     */
    @Select("select * from team_or_strategy where id=#{id}")
    @Results({
            @Result(property = "strategyName",column = "strategy_name"),
            @Result(property = "strategyId",column = "strategy_id")
    })
    public ArrayList<TeamAndOrStrategy>selectTeamOrStrategyById(@Param("id")Long id);

    /**
     * 查询team_or_strategy中最大id
     * @return
     */
    @Select("select max(id) from team_or_strategy")
    public Long getMaxTeamOrStrategyId();

    /**
     * 在team_or_strategy中插入数据
     * @param teamOrStrategyId
     * @param strategyName
     * @param strategyId
     * @return
     */
    @Insert("insert into team_or_strategy(id,strategy_name,strategy_id) values(#{id},#{strategyName},#{strategyId}")
    @Options(useGeneratedKeys =true,keyColumn ="id" )
    public Long insertTeamOrStrategy(@Param("id")Long teamOrStrategyId,@Param("strategyName")String strategyName,@Param("strategyId")Long strategyId);
}
