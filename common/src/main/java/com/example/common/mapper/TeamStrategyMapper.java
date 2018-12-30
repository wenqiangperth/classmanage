package com.example.common.mapper;


import com.example.common.entity.TeamStrategy;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface TeamStrategyMapper {

    /**
     * 通过课程id查组队策略
     * @param courseId
     * @return
     */
    @Select("select * from team_strategy where course_id=#{courseId}")
    @Results({
            @Result(property = "courseId",column = "course_id"),
            @Result(property = "strategySerial",column = "strategy_serial"),
            @Result(property = "strategyId",column = "strategy_id"),
            @Result(property = "strategyName",column = "strategy_name")
    })
    public ArrayList<TeamStrategy>selectTeamStrategyByCourseId(@Param("courseId")Long courseId);

    /**
     * 在team_strategy中插入数据
     * @param courseId
     * @param strategySerial
     * @param strategyName
     * @param strategyId
     * @return
     */
    @Insert("insert into team_strategy(course_id,strategy_serial,strategy_name,strategy_id) values(#{courseId},#{strategySerial},#{strategyName},#{strategyId})")
    public Long insertTeamStrategy(@Param("courseId")Long courseId,@Param("strategySerial")int strategySerial,@Param("strategyName")String strategyName,@Param("strategyId")Long strategyId);

//    /**
//     * 查询team_strategy中最大id
//     * @return
//     */
//    @Select("select max(id) from team_strategy")
//    public Long getMaxTeamStrategyId();
}
