package com.example.common.mapper;

import com.example.common.entity.TeamValidVO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @ClassName RequestMapper
 * @Description
 * @Author perth
 * @Date 2018/12/21 0021 上午 9:25
 * @Version 1.0
 **/
@Mapper
@Repository
public interface RequestMapper {

    /**
     * 根据教师id查找其所有组队请求
     * @param teacherId
     * @return
     */
    @Select("select * from team_valid_application where teacher_id=#{teacherId}")
    @Results(id = "TeamValidMap",value = {
            @Result(property = "teamId",column = "team_id"),
            @Result(property = "teacherId",column = "teacher_id"),
            @Result(property = "reason",column = "reason"),
            @Result(property = "status",column = "status")
    })
    public ArrayList<TeamValidVO> getAllTeamValidByTeacherId(@Param(value="teacherId")Long teacherId);

    /**
     * 根据Id查看组队申请消息
     * @param teamValidId
     * @return
     */
    @Select("select * from team_valid_application where id=#{teamValidId}")
    @ResultMap(value="TeamValidMap")
    public TeamValidVO getTeamValidByTeamValidId(@Param(value="teamValidId")Long teamValidId);
}
