package com.example.common.mapper;

import com.example.common.entity.Seminar;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.sql.Date;

/**
 * @ClassName SeminarMapper
 * @Description
 * @Author perth
 * @Date 2018/12/17 0017 下午 7:58
 * @Version 1.0
 **/

@Mapper
@Repository
public interface SeminarMapper {

    /**
     * 插入seminar数据
     * @param courseId,roundId,seminarName,introduction,maxTeam,isVisible,seminarSerial,enrollStartTime,enrollEndTime
     * @return
     */
    @Insert("insert into seminar (course_id,round_id,seminar_name,introduction,max_team,is_visible,seminar_serial,enroll_start_time,enroll_end_time) values (#{courseId},#{roundId},#{seminarName},#{introduction},#{maxTeam},#{isVisible},#{seminarSerial},#{enrollStartTime},#{enrollEndTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public long insertSeminar(@Param("courseId") Long courseId, @Param("roundId") Long roundId, @Param("seminarName") String seminarName, @Param("introduction") String introduction, @Param("maxTeam")int maxTeam, @Param("isVisible") boolean isVisible, @Param("seminarSerial")int seminarSerial, @Param("enrollStartTime")Date enrollStartTime,@Param("enrollEndTime")Date enrollEndTime);

    @Select("select * from seminar where course_id=#{courseId} and round_id=#{roundId} and seminar_name=#{seminarName}")
    public Seminar selectSeminarByCoureseIdAndRoundIdAndSeminarName(@Param("courseId") Long courseId,@Param("roundId")Long roundId,@Param("seminarName")String seminarName);

    /**
     * 根据课程id删除讨论课
     * @param courseId
     * @return
     */
    @Delete("delete from seminar where course_id=#{courseId}")
    public long deleteSeminarByCourseId(@Param(value="courseId")long courseId);
}
