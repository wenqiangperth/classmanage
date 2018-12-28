package com.example.common.mapper;

import com.example.common.entity.TeamAndOrStrategy;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface TeamAndStrategyMapper {

    /**
     * id->TeamAndStrategy
     * @param id
     * @return
     */
    @Select("select * from  team_and_strategy where id=#{id}")
    @Results({
            @Result(property = "strategyName",column = "strategy_name"),
            @Result(property = "strategyId",column = "strategy_id")
    })
    public ArrayList<TeamAndOrStrategy> selectTeamAndStrategyById(@Param("id")Long id);

    /**
     * 查询team_and_strategy中最大id
     * @return
     */
    @Select("select max(id) from team_and_strategy")
    public Long getMaxTeamAndStrategyId();

    /**
     * 在team_and_strategy中插入数据
     * @param teamAndStrategyId
     * @param strategyName
     * @param strategyId
     * @return
     */
    @Insert("insert into team_and_strategy(id,strategy_name,strategy_id) values(#{id},#{strategyName},#{strategyId}")
    @Options(useGeneratedKeys =true,keyColumn ="id" )
    public Long insertTeamAndStrategy(@Param("id")Long teamAndStrategyId,@Param("strategyName")String strategyName,@Param("strategyId")Long strategyId);
}
