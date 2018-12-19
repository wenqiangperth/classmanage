package com.example.common.mapper;

import com.example.common.entity.Student;
import com.example.common.entity.Team;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

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

    /**
     * 插入一个新的小组
     * @param klassId
     * @param courseId
     * @param leaderId
     * @param teamName
     * @param teamSerial
     * @param status
     * @return
     */
    @Insert("insert into team (klass_id,course_id,leader_id,team_name,team_serial,status) values (#{klassId},#{courseId},#{leaderId},#{teamName},#{teamSerial},#{status})")
    public Long insertTeam(@Param("klassId") Long klassId, @Param("courseId") Long courseId, @Param("leaderId") Long leaderId, @Param("teamName") String teamName,@Param("teamSerial") int teamSerial,@Param("status") int status);

    /**
     * 向klass_student表更新小组的关系，小组成员
     * @param klassId
     * @param studentId
     * @param teamId
     * @return
     */
    @Update("update klass_student set team_id=#{teamId} where klass_id=#{klassId} and student_id=#{studentId}")
    public Long updateKlassStudent(@Param("klassId") Long klassId,@Param("studentId") Long studentId,@Param("teamId") Long teamId);

    /**
     * 从team表中根据course和leader查询team
     * @param klassId
     * @param courseId
     * @param leaderId
     * @return
     */
    @Select("select * from team where klass_id=#{klassId} and course_id=#{courseId} and leader_id=#{leaderId}")
    @Results(id = "teamMap",value = {
            @Result(property = "klassId",column = "klass_id"),
            @Result(property = "courseId",column = "course_id"),
            @Result(property = "leaderId",column = "leader_id"),
            @Result(property = "teamName",column = "team_name"),
            @Result(property = "teamSerial",column = "team_serial")
    })
    public Team selectTeamByCourseIdAndLeaderId(@Param("klassId") Long klassId,@Param("courseId") Long courseId,@Param("leaderId") Long leaderId);

    /**
     * 从team表中查询某课程下所有的team
     * @param klassId
     * @param courseId
     * @return
     */
    @Select("select * from team where klass_id=#{klassId} and course_id=#{courseId}")
    @ResultMap(value = "teamMap")
    public ArrayList<Team> selectTeamsByCourseIdAndClassId(@Param("klassId") Long klassId,@Param("courseId") Long courseId);

    /**
     * 根据ID查询小组
     * @param id
     * @return
     */
    @Select("select * from team where id=#{id}")
    @ResultMap(value = "teamMap")
    public Team selectTeamById(@Param("id") Long id);

    /**
     * 获得某组下所有的组员
     * @param teamId
     * @return
     */
    @Select("select s.id,s.account,s.password,s.is_active,s.student_name,s.email from klass_student ks,student s where ks.team_id=#{teamId} and ks.student_id=s.id")
    @Results(id = "klassStudentMap",value = {
            @Result(property = "isAcctive",column = "is_acctive"),
            @Result(property = "studentName",column = "student_name")
    })
    public ArrayList<Student> selectStudentsByTeamId(Long teamId);

    /**
     * 根据ID删除TEAM
     * @param teamId
     * @return
     */
    @Delete("delete from team where id=#{teamId}")
    public Long deleteTeamById(@Param("teamId") Long teamId);


}
