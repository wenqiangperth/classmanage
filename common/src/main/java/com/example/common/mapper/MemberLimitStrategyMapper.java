package com.example.common.mapper;


import com.example.common.entity.MemberLimitStrategy;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MemberLimitStrategyMapper {

    /**
     * id->MemberLimitStrategy
     * @param id
     * @return
     */
    @Select("select * from member_limit_strategy where id=#{id}")
    @Results({
            @Result(property = "minMember",column = "min_member"),
            @Result(property = "maxMember",column = "maxMember")
    })
    public MemberLimitStrategy selectMemberLimitStrategyById(@Param("id")Long id);

    /**
     * 查询member_limit_strategy中最大id
     * @return
     */
    @Select("select max(id) from member_limit_strategy")
    public Long getMaxMemberLimitStrategyId();

    /**
     * 在member_limit_strategy表中插入数据
     * @param memberLimitStrategyId
     * @param mimMember
     * @param maxMember
     * @return
     */
    @Insert("insert into member_limit_strategy(id,min_member,max_member) values(#{id},#{minMember},#{maxMember})")
    @Options(useGeneratedKeys =true,keyColumn ="id" )
    public Long insertMemberLimitStrategy(@Param("id")Long memberLimitStrategyId,@Param("minMember")Long mimMember,@Param("maxMember")Long maxMember);
}
