package com.example.common.mapper;

import com.example.common.entity.KlassRound;
import com.example.common.entity.Round;
import org.apache.ibatis.annotations.*;
import com.example.common.entity.Seminar;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @ClassName RoundMapper
 * @Description
 * @Author perth
 * @Date 2018/12/17 0017 下午 7:58
 * @Version 1.0
 **/

@Mapper
@Repository
public interface RoundMapper {


    /**
     * 查询：id->round
     * @param id
     * @return
     */
    @Select("select * from round where id=#{id}")
    @Results({
            @Result(property = "courseId",column = "course_id"),
            @Result(property = "roundSerial",column = "round_serial"),
            @Result(property = "presentationScoreMethod",column = "presentation_score_method"),
            @Result(property = "reportScoreMethod",column = "report_score_method"),
            @Result(property = "questionScoreMethod",column = "question_score_method")
    })
    public Round selectRoundById(@Param("id")Long id);

    /**
     * 查询：根据课程id查询所有轮次
     * @param courseId
     * @return
     */
    @Select("select * from round where course_id=#{courseId}")
    @Results(id = "roundMap",value = {
            @Result(property = "courseId",column = "course_id"),
            @Result(property = "roundSerial",column = "round_serial"),
            @Result(property = "presentationScoreMethod",column = "presentation_score_method"),
            @Result(property = "reportScoreMethod",column = "report_score_method"),
            @Result(property = "questionScoreMethod",column = "question_score_method")
    })
    public ArrayList<Round> getAllRoundByCourseId(@Param(value="courseId") long courseId);

    /**
     * 查询：roundId->klassRound
     * @param roundId
     * @return
     */
    @Select("select kr.klass_id,kr.round_id,kr.enroll_number,k.klass_serial from klass_round kr,klass k where kr.klass_id=k.id and round_id=#{roundId}")
    @Results({
            @Result(property = "klassId",column = "klass_id"),
            @Result(property = "roundId",column = "round_id"),
            @Result(property = "enrollNumber",column = "enroll_number"),
            @Result(property = "klassSerial",column = "klass_serial")
    })
    public ArrayList<KlassRound>selectKlassRoundByRoundId(@Param("roundId")Long roundId);

    /**
     * 根据课程id删除轮次
     * @param courseId
     * @return
     */
    @Delete("delete from round where course_id=#{courseId}")
    public long deleteRoundByCourseId(@Param(value="courseId")long courseId);

    /**
     * 更新：round的计分规则
     * @param round
     * @return
     */
    @Update("update round set presentation_score_method=#{presentationScoreMethod},report_score_method=#{reportScoreMethod},question_score_method=#{questionScoreMethod} where id=#{id}")
    public  Long updateRoundById(Round round);

    /**
     * 更新：klass_round的报名次数
     * @param klassRound
     * @return
     */
    @Update("update klass_round set enroll_number=#{enrollNumber} where klass_id=#{klassId} and round_id=#{roundId}")
    public Long updateKlassRound(KlassRound klassRound);

    /**
     * 插入：新建round
     * @param round
     * @return
     */
    @Insert("insert into round (course_id,round_serial,presentation_score_method,report_score_method,question_score_method) values (#{courseId},#{roundSerial},#{presentationScoreMethod},#{reportScoreMethod},#{questionScoreMethod})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    public Long insertRound(Round round);

    /**
     * 根据courseid删除该课程底下所以round
     * @param courseId
     * @return
     */
    @Delete("delete from round where course_id=#{courseId}")
    public Long deleteAllRoundByCourseId(@Param(value="courseId")Long courseId);




}
