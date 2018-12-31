package com.example.common.mapper;

import com.example.common.entity.*;
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
     * 插入:一个team
     * @param team
     * @return
     */
    @Insert("insert into team (klass_id,course_id,leader_id,team_name,team_serial,klass_serial,status) values (#{klassId},#{courseId},#{leaderId},#{teamName},#{teamSerial},#{klassSerial},#{status})")
    @Options(useGeneratedKeys =true,keyColumn ="id" )
    public Long insertTeam(Team team);

    /**
     * 更新：klass_student表小组的关系，小组成员
     * @param klassId
     * @param studentId
     * @param teamId
     * @return
     */
//    @Update("update klass_student set team_id=#{teamId} where klass_id=#{klassId} and student_id=#{studentOrTeacherId}")
//    public Long updateKlassStudent(@Param("klassId") Long klassId,@Param("studentOrTeacherId") Long studentId,@Param("teamId") Long teamId);

    /**
     * 更新：team_student表小组的关系，小组成员
     * @param teamId
     * @param studentId
     * @return
     */
    @Insert("insert into team_student(team_id,student_id) values(#{teamId},#{studentOrTeacherId})")
    public Long insertTeamStudent(@Param("teamId") Long teamId,@Param("studentOrTeacherId") Long studentId);

    /**
     * 删除队伍和学生关系
     * @param teamId
     * @param studentId
     * @return
     */
    @Delete("delete from team_student where team_id=#{teamId}")
    public Long deleteTeamStudentByTeamId(@Param("teamId")Long teamId,@Param("studentId")Long studentId);

    /**
     * 删除队伍和单个学生关系
     * @param teamId
     * @param studentId
     * @return
     */
    @Delete("delete from team_student where team_id=#{teamId} and student_id=#{studentId}")
    public Long deleteTeamStudentByTeamIdAndStudentId(@Param("teamId")Long teamId,@Param("studentId")Long studentId);

    /**
     * 更新klass_student表关系
     * @param klassId
     * @param teamId
     * @return
     */
    @Insert("insert into klass_team(klass_id,team_id) values(#{klassId},#{teamId})")
    public Long insertKlassTeam(@Param("klassId")Long klassId,@Param("teamId")Long teamId);

    /**
     * 删除klass_team关系
     * @param klassId
     * @param teamId
     * @return
     */
    @Delete("delete from klass_team where klass_id=#{klassId} and team_id=#{teamId}")
    public Long deleteKlassTeam(@Param("klassId")Long klassId,@Param("teamId")Long teamId);

    /**
     * 查询：从team表中根据course和leader查询team
     * @param team
     * @return
     */
    @Select("select * from team where klass_id=#{klassId} and course_id=#{courseId} and leader_id=#{leaderId}")
    @Results(id = "teamMap",value = {
            @Result(property = "klassId",column = "klass_id"),
            @Result(property = "courseId",column = "course_id"),
            @Result(property = "leaderId",column = "leader_id"),
            @Result(property = "teamName",column = "team_name"),
            @Result(property = "teamSerial",column = "team_serial"),
            @Result(property = "klassSerial",column = "klass_serial")
    })
    public Team selectTeamByCourseIdAndLeaderId(Team team);

    /**
     * 查询：从team表中查询某课程某班级下所有的team
     * @param klassId
     * @param courseId
     * @return
     */
    @Select("select * from team where klass_id=#{klassId} and course_id=#{courseId}")
    @ResultMap(value = "teamMap")
    public ArrayList<Team> selectTeamsByCourseIdAndClassId(@Param("klassId") Long klassId,@Param("courseId") Long courseId);

    /**
     * 查询：从klass_team表中查询某课程某班级下所有的teamid
     * @param klassId
     * @return
     */
    @Select("select team_id from team where klass_id=#{klassId}")
    public ArrayList<Long> selectTeamsIdByClassId(@Param("klassId") Long klassId);

    /**
     * 查询：roundId->teams
     * @param roundId
     * @return
     */
    @Select("select t.* from team t,round r where t.course_id=r.course_id and r.id=#{roundId}")
    @ResultMap(value = "teamMap")
    public ArrayList<Team>selectTeamByRoundId(@Param("roundId")Long roundId);

    /**
     * 查询:courseid->TEAM
     * @param courseId
     * @return
     */
    @Select("select * from team t,klass k,klass_team ks where ks.klass_id=k.id and ks.team_id=t.id and k.course_id=#{courseId}")
    @ResultMap(value = "teamMap")
    public ArrayList<Team>selectTeamsByCourseId(@Param("courseId")Long courseId);

//    /**
//     * 查询：从team表中查询某课程下所有的team
//     * @param courseId
//     * @return
//     */
//    @Select("select * from team where course_id=#{courseId}")
//    @ResultMap(value = "teamMap")
//    public ArrayList<Team> selectTeamsByCourseId(@Param("courseId") Long courseId);



    /**
     * 查询：根据ID查询小组
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
    @Select("select s.id,s.account,s.password,s.is_active,s.student_name,s.email from team_student ks,student s where ks.team_id=#{teamId} and ks.student_id=s.id")
    @Results(id = "klassStudentMap",value = {
            @Result(property = "isAcctive",column = "is_active"),
            @Result(property = "studentName",column = "student_name")
    })
    public ArrayList<Student> selectStudentsByTeamId(Long teamId);

    /**
     * 删除：根据ID删除TEAM
     * @param teamId
     * @return
     */
    @Delete("delete from team where id=#{teamId}")
    public Long deleteTeamById(@Param("teamId") Long teamId);

    /**
     * 删除：klassID->teams
     * @param klassId
     * @return
     */
    @Delete("delete from team where klass_id=#{klassId}")
    public Long deleteTeamByKlassId(@Param("klassId")Long klassId);

    /**
     * 根据klass_id删除klass_team关系
     * @param klassId
     * @return
     */
    @Delete("delete from klass_team where klass_id=#{klassId}")
    public Long deleteAllKlassTeam(@Param("klassId")Long klassId);



    /**
     * 更新：team的status
     * @param teamId
     * @param status
     * @return
     */
    @Update("update team set status=#{status} where id=#{teamId}")
    public Long updateTeamStatus(@Param("teamId") Long teamId,@Param("status")Long status);

    /**
     * 更新：team的name
     * @param team
     * @return
     */
    @Update("update team set team_name=#{teamName} where id=#{id}")
    public Long updateTeamName(Team team);

    /**
     * 插入:创建特殊组队请求
     * @param teamValidApplication
     * @return
     */
    @Insert("insert into team_valid_application (team_id,teacher_id,reason) values (#{teamId},#{teacherId},#{reason})")
    public Long insertTeamValidApplication(TeamValidApplication teamValidApplication);

    /**
     * 通过teamValidId查找到team_id
     * @param teamValidId
     * @return
     */
    @Select("select team_id from team_valid_application where id=#{teamValidId}")
    public Long findTeamIdByTeamValidId(@Param(value="teamValidId")Long teamValidId);

    /**
     * 根据班级查看所有队伍
     * @param classId
     * @return
     */
    @Select("select team_id from klass_team where klass_id=#{classId}")
    public ArrayList<Long> findAllTeamByClassId(@Param(value="classId")Long classId);

    /**
     * 根据studentId和courseId和classId查找teamId
     * @param studentId
     * @param courseId
     * @param classId
     * @return
     */
    @Select("select team_id from team_student s,team st where s.team_id=st.id and student_id=#{studentId} and course_id=#{courseId} and klass_id=#{classId}")
    public Long selectTeamIdByStudentIdAndCourseIdAndClassId(@Param("studentId")Long studentId,@Param("courseId")Long courseId,@Param("classId")Long classId);

//    @Insert("insert into team (klass_id,course_id,leader_id,team_name,team_serial,status) values (#{klassId},#{courseId},#{leaderId},#{teamName},#{teamSerial},#{status})")
//    @Options(useGeneratedKeys = true,keyColumn = "id")
//    public Long aa(Team team);

//    @Select("select student_id from team_student a,klass_team b,student c where a.team_id=b.team_id and a.student_id=c.id and klass_id=#{klassId} and team_id is null")
//    public ArrayList<Long> getAllNoTeamStudentByClassId(@Param("klassId")Long klassId);

    /**
     * 根据teamid查找其下所有学生id
     * @param teamId
     * @return
     */
    @Select("select student_id from team_student where team_id=#{teamId}")
    public ArrayList<Long> findAllStudentIdByTeamId(@Param("teamId")Long teamId);

    /**
     * 根据班级id查找其下所有组队信息
     * @param klassId
     * @return
     */
    @Select("select team_id from klass_team where klass_id=#{klassId}")
    public ArrayList<Long> getAllTeamIdByClassId(@Param("klassId")Long klassId);
}
