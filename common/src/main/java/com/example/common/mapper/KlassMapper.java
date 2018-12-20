package com.example.common.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

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
}
