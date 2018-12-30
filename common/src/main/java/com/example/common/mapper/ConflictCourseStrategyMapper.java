package com.example.common.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 *
 */
@Mapper
@Repository
public interface ConflictCourseStrategyMapper {

    /**
     * 查询：id->strategy
     * @param id
     * @return
     */
    @Select("select course_id from conflict_course_strategy where id=#{id}")
    public ArrayList<Long>selectConflictCourseStrategyById(@Param("id")Long id);

    /**
     * 查询conflict_course_strategy中最大id
     * @return
     */
    @Select("select max(id) from conflict_course_strategy")
    public Long getMaxConflictCourseStrategyId();

    /**
     * 在ConflictCourseStrategy表中插入记录
     * @param conflictCourseStrategyId
     * @param courseId
     * @return
     */
    @Insert("insert into conflict_course_strategy(id,course_id) values(#{id},#{courseId}")
    @Options(useGeneratedKeys =true,keyColumn ="id" )
    public Long insertConflictCourseStrategy(@Param("id")Long conflictCourseStrategyId,@Param("courseId")Long courseId);
}
