package com.example.common.mapper;

import com.example.common.entity.TeamValidVO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PutMapping;

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

    /**
     * 根据Id修改组队共享申请请求
     * @param teamShareId
     * @param status
     * @return
     */
    @Update("update share_team_application set status=#{status} where id=#{teamShareId}")
    public Long updateTeamShareRequestById(@Param(value="teamShareId") Long teamShareId,@Param(value="status") Long status);

    /**
     * 根据Id修改讨论课共享申请请求
     * @param seminarShareId
     * @param status
     * @return
     */
    @Update("update share_seminar_application set status=#{status} where id=#{seminarShareId}")
    public Long updateSeminarShareRequestById(@Param(value="seminarShareId") Long seminarShareId,@Param(value="status") int status);

    /**
     * 根据Id修改组队合理性申请请求
     * @param teamValidId
     * @param status
     * @return
     */
    @Update("update team_valid_application set status=#{status} where id=#{teamValidId}")
    public Long updateTeamValidRequestById(@Param(value="teamValidId")Long teamValidId,@Param(value="status")Long status);



}
