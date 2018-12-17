package com.example.common.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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

    @Insert("insert into seminar (course_id,round_id,seminar_name,introduction,max_team,is_visible,seminar_serial,enroll_start_time,enroll_end_time) values (#{courseId},#{roundId},#{seminarName},#{introduction},#{maxTeam},#{isVisible},#{seminarSerial},#{enrollStartTime},#{enrollEndTime})")
    public long insertSeminar(@Param("courseId") Long courseId, @Param("roundId") Long roundId, @Param("seminarName") String seminarName, @Param("introduction") String introduction, @Param("maxTeam")int maxTeam, @Param("isVisible") boolean isVisible, @Param("seminarSerial")int seminarSerial, @Param("enrollStartTime")Date enrollStartTime,@Param("enrollEndTime")Date enrollEndTime);
}
