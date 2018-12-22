package com.example.common.mapper;

import com.example.common.entity.Klass;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

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
     * 根据课程id查看班级
     * @param courseId
     * @return
     */
    @Select("select * from klass where course_id=#{courseId}")
    @Results(id = "KlassMap",value = {
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
    @ResultMap(value = "KlassMap")
    public ArrayList<Klass>getAllKlassBySeminarId(@Param("seminarId")Long seminarId);


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
    @Delete("delete from klass_student where klass_id=#{class_id}")
    public long deleteClassStudentByClassId(@Param(value="classId")long classId);

    /**
     * 根据classId寻找对应的班级
     * @param classId
     * @return
     */
    @Select("select * from klass where id=#{classId}")
    @ResultMap(value = "KlassMap")
    public Klass getKlassByKlassId(@Param(value="classId")long classId);
}
