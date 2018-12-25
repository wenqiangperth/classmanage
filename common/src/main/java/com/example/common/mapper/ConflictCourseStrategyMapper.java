package com.example.common.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

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
}
