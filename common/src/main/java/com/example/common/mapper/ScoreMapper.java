package com.example.common.mapper;

import com.example.common.entity.Score;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

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

    /**
     * 查询讨论课成绩
     * @param seminarId
     * @param teamId
     * @param courseId
     * @return
     */
    @Select("select ss.* from seminar_score ss,klass_seminar ks,klass k " +
            "where k.id=ks.klass_id and ks.id=ss.klass_seminar_id and k.course_id=#{courseId}" +
            " and ks.seminar_id=#{seminarId} and ss.team_id=#{teamId}")
    @ResultMap(value = "scoreMap")
    public Score selectSeminarScoreByTeamIdAndSeminarId(@Param("seminarId")Long seminarId,@Param("teamId")Long teamId,@Param("courseId")Long courseId);


    /**
     * 查询：一个team的一次讨论课成绩
     * @param teamId
     * @param klassId
     * @param seminarId
     * @return
     */
    @Select("select ss.* from seminar_score ss,klass_seminar ks where ks.id=ss.klass_seminar_id and ss.team_id=#{teamId} and ks.klass_id=#{klassId} and ks.seminar_id=#{seminarId}")
    @ResultMap(value = "scoreMap")
    public Score selectTeamSeminarScore(@Param("teamId")Long teamId,@Param("klassId")Long klassId,@Param("seminarId")Long seminarId);

    /**
     * 更新：团队讨论课的成绩
     * @param score
     * @return
     */
    @Update("update seminar_score set total_score=#{totalScore},presentation_score=#{presentationScore}," +
            "question_score=#{questionScore},report_score=#{reportScore} where team_id=#{teamId} and klass_seminar_id=#{seminarOrRoundId}")
    public Long updateTeamSeminarScore(Score score);

    /**
     * 更新：展示成绩
     * @param score
     * @return
     */
    @Update("update seminar_score set presentation_score=#{presentationScore} where klass_seminar_id=#{seminarOrRoundId} and team_id=#{teamId}")
    public Long updateTeamSeminarPresentationScore(Score score);

    /**
     * 查询：一个班的一次讨论课成绩
     * @param klassId
     * @param seminarId
     * @return
     */
    @Select("select ss.* from klass_seminar ks,seminar_score ss where ks.id=ss.klass_seminar_id and ks.klass_id=#{klassId} and ks.seminar_id=#{seminarId}")
    @ResultMap(value = "scoreMap")
    public ArrayList<Score> selectSeminarScore(@Param("klassId")Long klassId,@Param("seminarId")Long seminarId);

    /**
     * 通过ClassSeminarIdAndTeamId查讨论课成绩
     * @param classSeminarId
     * @param teamId
     * @return
     */
    @Select("select * from seminar_score where klass_seminar_id=#{classSeminarId} and team_id=#{teamId}")
    @ResultMap(value = "scoreMap")
    public Score selectSeminarScoreByClassSeminarIdAndTeamId(@Param("classSeminarId")Long classSeminarId,@Param("teamId")Long teamId);


}
