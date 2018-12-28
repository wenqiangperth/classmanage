package com.example.common.dao;

import com.example.common.entity.Course;
import com.example.common.entity.Team;
import com.example.common.entity.TeamShareVO;
import com.example.common.entity.TeamValidVO;
import com.example.common.mapper.CourseMapper;
import com.example.common.mapper.TeamMapper;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.common.mapper.RequestMapper;

import java.util.ArrayList;

/**
 * @ClassName RequestDao
 * @Description
 * @Author perth
 * @Date 2018/12/21 0021 上午 9:21
 * @Version 1.0
 **/
@Repository
public class RequestDao {

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private TeamMapper teamMapper;

    public ArrayList<TeamValidVO> getAllTeamValidByTeacherId(Long teacherId)
    {
        return requestMapper.getAllTeamValidByTeacherId(teacherId);
    }

    public TeamValidVO getTeamValidByTeamValidId(Long teamValidId)
    {
        return requestMapper.getTeamValidByTeamValidId(teamValidId);
    }

    public Long updateTeamShareRequestById(Long teamShareId,Long status)
    {
        return requestMapper.updateTeamShareRequestById(teamShareId,status);
    }

    public Long updateSeminarShareRequestById(Long seminarShareId,int status)
    {
        return requestMapper.updateSeminarShareRequestById(seminarShareId,status);
    }

    public Long updateTeamValidRequestById(Long teamValidId,Long status)
    {
        Long teamId=teamMapper.findTeamIdByTeamValidId(teamValidId);
        teamMapper.updateTeamStatus(teamId,status);
        return requestMapper.updateTeamValidRequestById(teamValidId,status);
    }

}
