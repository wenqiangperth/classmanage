package com.example.common.mapper;

import org.apache.ibatis.annotations.Delete;
import com.example.common.entity.Seminar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
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
     * 查询：一轮下所有讨论课
     * @param roundId
     * @return
     */
    @Select("select * from seminar where round_id=#{roundId}")
    @Results({
            @Result(property = "courseId",column = "course_id"),
            @Result(property = "roundId",column = "round_id"),
            @Result(property = "seminarName",column = "seminar_name"),
            @Result(property = "maxTeam",column = "max_team"),
            @Result(property = "isVisible",column = "is_visible"),
            @Result(property = "seminarSerial",column = "seminar_serial"),
            @Result(property = "enrollStartTime",column = "enroll_start_time"),
            @Result(property = "enrollEndTime",column = "enroll_end_time")
    })
    public ArrayList<Seminar> selectAllSeminarsByRoundId(Long roundId);
    /**
     * 根据课程id删除轮次
     * @param courseId
     * @return
     */
    @Delete("delete from round where course_id=#{courseId}")
    public long deleteRoundByCourseId(@Param(value="courseId")long courseId);
}
