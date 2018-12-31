package com.example.common.mapper;

import com.example.common.entity.Klass;
import com.example.common.entity.KlassSeminar;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Date;

/**
 * @ClassName KlassMapper
 * @Description
 * @Author perth
 * @Date 2018/12/17 0017 下午 7:57
 * @Version 1.0
 **/
@Mapper
@Repository
public interface KlassMapper {

    /**
     * 插入班级
     * @param klass
     * @return
     */
    @Insert("insert into klass (id,course_id,grade,klass_serial,klass_time,klass_location) values (#{id},#{courseId},#{grade},#{klassSerial},#{klassTime},#{klassLocation})")
    @Options(useGeneratedKeys = true,keyColumn = "id")
    public Long insertKlass(Klass klass);

    /**
     * 根据课程id查看班级
     * @param courseId
     * @return
     */
    @Select("select * from klass where course_id=#{courseId}")
    @Results(id = "klassMap",value = {
            @Result(property = "courseId",column = "course_id"),
            @Result(property = "grade",column = "grade"),
            @Result(property = "klassSerial",column = "klass_serial"),
            @Result(property = "klassTime",column = "klass_time"),
            @Result(property = "klassLocation",column = "klass_location")
    })
    public ArrayList<Klass> getAllClassByCourseId(@Param(value="courseId") long courseId);

    /**
     * 查询：seminarId->klasses
     * @param seminarId
     * @return
     */
    @Select("select * from klass k,klass_seminar ks where k.id=ks.klass_id and ks.seminar_id=#{seminarId}")
    @ResultMap(value = "klassMap")
    public ArrayList<Klass>getAllKlassBySeminarId(@Param("seminarId")Long seminarId);

    /**
     * 查询：seminarId,klassID->KLASS_SEMINAR
     * @param klassId
     * @param seminarId
     * @return
     */
    @Select("select * from klass_seminar where klass_id=#{klassId} and seminar_id=#{seminarId}")
    @Results({
            @Result(property = "klassId",column = "klass_id"),
            @Result(property = "seminarId",column = "seminar_id"),
            @Result(property = "reportDDL",column = "report_ddl")
    })
    public KlassSeminar getKlassSeminarByKlassAndSeminar(@Param("klassId")Long klassId,@Param("seminarId")Long seminarId);


    /**
     * 根据课程id删除班级
     * @param courseId
     * @return
     */
    @Delete("delete from klass where course_id=#{courseId}")
    public long delectClassByCourseId(@Param(value="courseId") long courseId);

    /**
     * 根据班级id删除班级轮次关系
     * @param classId
     * @return
     */
    @Delete("delete from klass_round where klass_id=#{classId}")
    public long deleteClassRoundByClassId(@Param(value="classId") long classId);

    /**
     * 根据班级id删除班级讨论课关系
     * @param classId
     * @return
     */
    @Delete("delete from klass_seminar where klass_id=#{classId}")
    public long deleteClassSeminarByClassId(@Param(value="classId")long classId);

    /**
     * 根据班级id删除班级学生关系
     * @param classId
     * @return
     */
    @Delete("delete from klass_student where klass_id=#{classId}")
    public long deleteClassStudentByClassId(@Param(value="classId")long classId);

    /**
     * 删除：id->klass
     * @param id
     * @return
     */
    @Delete("delete from klass where id=#{klass_id}")
    public Long deleteKlassById(@Param("id")Long id);

    /**
     * 根据classId寻找对应的班级
     * @param classId
     * @return
     */
    @Select("select * from klass where id=#{classId}")
    @ResultMap(value = "klassMap")
    public Klass getKlassByKlassId(@Param(value="classId")long classId);

    /**
     * 在klass_student表中插入数据
     * @param klassId
     * @param studentId
     * @param courseId
     * @return
     */
    @Insert("insert into klass_student(klass_id,student_id,course_id) values(#{klassId},#{studentId},#{courseId})")
public Long insertKlassStudent(@Param("klassId") Long klassId,@Param("studentId") Long studentId,@Param("courseId") Long courseId);

    /**
     * 根据classId删除class信息
     * * @param classId
     * @return
     */
    @Delete("delete from klass where id=#{classId}")
    public Long deleteClassByClassId(@Param(value="classId") Long classId);


    /**
     * 根据classId删除klass_team信息
     * * @param classId
     * @return
     */
    @Delete("delete from klass_team where klass_id=#{classId}")
    public Long deleteClassTeamByClassId(@Param(value="classId") Long classId);

    /**
     * 通过学号和课程Id查找班级id
     * @param courseId
     * @param studentId
     * @return
     */
    @Select("select id from klass_student where course_id=#{courseId} and student_id=#{studentId}")
    public Long getClassIdByCourseIdAndStudentId(@Param(value="courseId") Long courseId,@Param(value="studentId") Long studentId);

    @Select("select k.* from klass_student ks,klass k where ks.klass_id=k.id and ks.course_id=#{courseId} and ks.student_id=#{studentId}")
    @ResultMap(value = "klassMap")
    public Klass getKlassByCourseIdAndStudentId(@Param(value="courseId") Long courseId,@Param(value="studentId") Long studentId);


    /**
     * 根据klassid删除所有klass_round关系
     * @param classId
     * @return
     */
    @Delete("delete from klass_round where klass_id=#{classId}")
    public Long deleteAllKlassRoundByKlassId(@Param(value="classId")Long classId);

    /**
     * 根据klassid删除所有klass_seminar关系
     * @param classId
     * @return
     */
    @Delete("delete from klass_seminar where klass_id=#{classId}")
    public Long deleteAllKlassSeminarByKlassId(@Param(value="classId")Long classId);

    /**
     * 在klass_round表中插入数据
     * @param klassId
     * @param roundId
     * @return
     */
    @Insert("insert into klass_round(klass_id,round_id) values(#{klassId},#{roundId})")
    public Long insertKlassRound(@Param("klassId")Long klassId,@Param("roundId")Long roundId);

    /**
     * 在klass_seminar表中插入数据
     * @param klassId
     * @param seminarId
     * @param reportDdl
     * @param status
     * @return
     */
    @Insert("insert into klass_round(klass_id,seminar_id,report_ddl,status) values(#{klassId},#{seminarId},#{reportDdl},#{status})")
    public Long insertKlassSeminar(@Param("klassId")Long klassId, @Param("seminarId")Long seminarId, @Param("reportDdl") Date reportDdl, @Param("status")int status);

    /**
     * 根据班级id查看其下所有学生
     * @param classId
     * @return
     */
    @Select("select student_id from klass_student where klass_id=#{classId}")
    public ArrayList<Long> findAllStudentByClassId(@Param("classId")Long classId);

    /**
     * 根据klass_id删除klass_team关联
     * @param classId
     * @return
     */
    @Delete("delete from klass_team where klass_id=#{classId}")
    public Long deleteAllKlassTeamByClassId(@Param("classId")Long classId);
}
