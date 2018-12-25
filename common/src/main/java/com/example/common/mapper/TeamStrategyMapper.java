package com.example.common.mapper;


import com.example.common.entity.TeamStrategy;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface TeamStrategyMapper {
    @Select("select * from team_strategy where id=#{id}")
    @Results({
            @Result(property = "courseId",column = "course_id"),
            @Result(property = "strategySerial",column = "strategy_serial"),
            @Result(property = "strategyId",column = "strategy_id"),
            @Result(property = "strategyName",column = "strategy_name")
    })
    public ArrayList<TeamStrategy>selectTeamStrategyByCourseId(@Param("courseId")Long courseId);
}
