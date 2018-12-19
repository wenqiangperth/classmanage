package com.example.common.mapper;

import com.example.common.entity.Team;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @ClassName TeamMapper
 * @Description
 * @Author perth
 * @Date 2018/12/17 0017 下午 7:59
 * @Version 1.0
 **/

@Mapper
@Repository
public interface TeamMapper {


    @Insert("insert into team (klass_id,course_id,leader_id,team_name,team_serial,status) values (#{klassId},#{courseId},#{leaderId},#{teamName},#{teamSerial},#{status})")
    public Long insertTeam(@Param("klassId") Long klassId, @Param("courseId") Long courseId, @Param("leaderId") Long leaderId, @Param("teamName") String teamName,@Param("teamSerial") int teamSerial,@Param("status") int status);

    @Insert("insert into klass_student (klass_id,student_id,course_id,team_id) values (#{klassId},#{studentId},#{courseId},#{teamId})")
    public Long insertKlassStudent(@Param("klassId") Long klassId,@Param("studentId") Long studentId,@Param("courseId") Long courseId,@Param("teamId") Long teamId);

    @Select("select * from team where klass_id=#{klassId} and course_id=#{courseId} and leader_id=#{leaderId}")
    public Team selectTeamByCourseIdAndLeaderId(@Param("klassId") Long klassId,@Param("courseId") Long courseId,@Param("leaderId") Long leaderId);

}
