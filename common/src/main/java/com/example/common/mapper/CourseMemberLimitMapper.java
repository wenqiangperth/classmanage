package com.example.common.mapper;

import com.example.common.entity.MemberLimitStrategy;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Mapper
@Repository
public interface CourseMemberLimitMapper {
    /**
     * 选择班级组队人数限制
     * @param id
     * @return
     */
    @Select("select * from course_member_limit_strategy where id=#{id}")
    @Results({
            @Result(property = "courseId",column = "course_id"),
            @Result(property = "minMember",column = "min_member"),
            @Result(property = "maxMember",column = "maxMember")
    })
    public MemberLimitStrategy selectCourseMemberLimitStrategyById(@Param("id")Long id);

    /**
     * 查询course_member_limit_strategy中最大id
     * @return
     */
    @Select("select max(id) from course_member_limit_strategy")
    public Long getMaxCourseMemberLimitStrategyId();

    /**
     * 在course_member_limit_strategy中插入数据
     * @param courseMemberLimitStrategyId
     * @param courseId
     * @param mimMember
     * @param maxMember
     * @return
     */
    @Insert("insert into course_member_limit_strategy(id,course_id,min_member,max_member) values(#{id},#{courseId},#{minMember},#{maxMember})")
    @Options(useGeneratedKeys =true,keyColumn ="id" )
    public Long insertCourseMemberLimitStrategy(@Param("id")Long courseMemberLimitStrategyId,@Param("courseId")Long courseId,@Param("minMember")Long mimMember,@Param("maxMember")Long maxMember);
}
