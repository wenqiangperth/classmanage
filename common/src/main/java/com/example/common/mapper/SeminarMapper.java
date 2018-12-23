package com.example.common.mapper;

import com.example.common.entity.KlassSeminar;
import com.example.common.entity.Seminar;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.sql.Date;
import java.util.ArrayList;

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
     * 插入:seminar
     * @param seminar
     * @return
     */
    @Insert("insert into seminar (course_id,round_id,seminar_name,introduction,max_team,is_visible,seminar_serial,enroll_start_time,enroll_end_time) values (#{courseId},#{roundId},#{seminarName},#{introduction},#{maxTeam},#{isVisible},#{seminarSerial},#{enrollStartTime},#{enrollEndTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public long insertSeminar( Seminar seminar);

    /**
     * 查询：courseId,seminarName->seminar
     * @param courseId
     * @param roundId
     * @param seminarName
     * @return
     */
    @Select("select * from seminar where course_id=#{courseId} and round_id=#{roundId} and seminar_name=#{seminarName}")
    public Seminar selectSeminarByCoureseIdAndRoundIdAndSeminarName(@Param("courseId") Long courseId,@Param("roundId")Long roundId,@Param("seminarName")String seminarName);


    /**
     * 插入:klass_seminar关系
     * @param klassId
     * @param seminarId
     * @param status
     * @return
     */
    @Insert("insert into klass_seminar (klass_id,seminar_id,status) values (#{klassId},#{seminarId},#{status})")
    public Long insertKlassSeminar(@Param("klassId")Long klassId,@Param("seminarId")Long seminarId,@Param("status")int status);


    /**
     * 删除：根据课程id删除讨论课
     * @param courseId
     * @return
     */
    @Delete("delete from seminar where course_id=#{courseId}")
    public long deleteSeminarByCourseId(@Param(value="courseId")long courseId);

    /**
     * 删除：id->seminar
     * @param id
     * @return
     */
    @Delete("delete from seminar where id=#{id}")
    public Long deleteSeminarById(@Param("id")Long id);

    /**
     * 删除：klass_seminar关系
     * @param seminarId
     * @return
     */
    @Delete("delete from klass_seminar where seminar_id=#{seminarId}")
    public Long deleteKlassSeminarBySeimarId(@Param("seminarId")Long seminarId);

    /**
     * 查找：根据courseId查找所有讨论课
     * @param courseId
     * @return
     */
    @Select("select * from seminar where course_id=#{courseId}")
    @Results(id = "seminarMap",value = {
            @Result(property = "courseId",column = "course_id"),
            @Result(property = "roundId",column = "round_id"),
            @Result(property = "seminarName",column = "seminar_name"),
            @Result(property = "introduction",column = "introduction"),
            @Result(property = "maxTeam",column = "max_team"),
            @Result(property = "isVisible",column = "is_visible"),
            @Result(property = "seminarSerial",column = "seminar_serial"),
            @Result(property = "enrollStartTime",column = "enroll_start_time"),
            @Result(property = "enrollEndTime",column = "enroll_end_time")
    })
    public ArrayList<Seminar> findAllSeminarByCourseId(@Param(value="courseId") long courseId);

    /**
     * 查询：roundID->seminars
     * @param roundId
     * @return
     */
    @Select("select * from seminar where round_id=#{roundId}")
    @ResultMap(value = "seminarMap")
    public ArrayList<Seminar> selectAllSeminarsByRoundId(Long roundId);

    /**
     * 查询：id->seminar
     * @param id
     * @return
     */
    @Select("select * from seminar where id=#{id}")
    @ResultMap(value = "seminarMap")
    public Seminar selectSeminarById(@Param("id")Long id);

    /**
     * 更新：修改讨论课
     * @param seminar
     * @return
     */
    @Update("update seminar set round_id=#{roundId},seminar_name=#{seminarName}," +
            "introduction=#{introduction},max_team=#{maxTeam},is_visible=#{isVisible}," +
            "seminar_serial=#{seminarSerial},enroll_start_time=#{enrollStartTime}," +
            "enroll_end_time=#{enrollEndTime} where id=#{id}")
    public Long updateSeminarById(Seminar seminar);

    /**
     * 更新：
     * @param klassSeminar
     * @return
     */
    @Update("update klass_seminar set report_ddl=#{reportDDL} where seminar_id={seminarId} and klass_id=#{klassId}")
    public Long updateKlassSeminarByKlassIdAndSeminarId(KlassSeminar klassSeminar);

    /**
     * 更新：设置讨论课轮次
     * @param roundId
     * @param id
     * @return
     */
    @Update("update seminar set round_id=#{roundId} where id=#{id}")
    public Long updateSeminarRoundId(@Param("roundId")Long roundId,@Param("id")Long id);

    /**
     * 更新：设置班级讨论课状态
     * @param klassSeminar
     * @return
     */
    @Update("update klass_seminar set status=#{status} where seminar_id=#{seminarId} and klass_id=#{klassId}")
    public Long updateSeminarStatus(KlassSeminar klassSeminar);

    @Select("select id from klass_seminar where klass_id=#{classId} and seminarId=#{seminarId}")
    public Long getClassSeminarIdBySeminarIdAndClassId(@Param(value="classId") Long classId,@Param(value="seminarId") Long seminarId);

}
