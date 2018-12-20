package com.example.common.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @ClassName RoundMapper
 * @Description
 * @Author perth
 * @Date 2018/12/17 0017 下午 7:58
 * @Version 1.0
 **/

@Mapper
@Repository
public interface RoundMapper {
    /**
     * 根据课程id删除轮次
     * @param courseId
     * @return
     */
    @Delete("delete from round where course_id=#{courseId}")
    public long deleteRoundByCourseId(@Param(value="courseId")long courseId);
}
