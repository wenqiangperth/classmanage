package com.example.common.mapper;

import com.example.common.entity.Score;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @author perth
 * @ClassName ScoreMapper
 * @Description TODO
 * @Date 2018/12/22 11:19
 * @Version 1.0
 **/
@Mapper
@Repository
public interface ScoreMapper {

    /**
     * 查询：roundID,teamId->ROUNDscore
     * @param roundId
     * @param teamId
     * @return
     */
    @Select("select * from round_score where round_id=#{roundId} and team_id=#{teamId}")
    @Results(id = "scoreMap",value = {
            @Result(property = "teamId",column = "team_id"),
            @Result(property = "totalScore",column = "total_score"),
            @Result(property = "presentationScore",column = "presentation_score"),
            @Result(property = "questionScore",column = "question_score"),
            @Result(property = "reportScore",column = "report_score")
    })
    public Score selectRoundScore(@Param("roundId")Long roundId,@Param("teamId")Long teamId);
}
