package com.example.common.mapper;

import com.example.common.entity.TeamAndOrStrategy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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
    public ArrayList<TeamAndOrStrategy> selectTeamAndStrategyById(@Param("id")Long id);
}
