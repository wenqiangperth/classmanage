package com.example.common.mapper;

import com.example.common.entity.MemberLimitStrategy;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CourseMemberLimitMapper {

    @Select("select * from course_member_limit_strategy where id=#{id}")
    @Results({
            @Result(property = "courseId",column = "course_id"),
            @Result(property = "minMember",column = "min_member"),
            @Result(property = "maxMember",column = "maxMember")
    })
    public MemberLimitStrategy selectCourseMemberLimitStrategyById(@Param("id")Long id);
}
