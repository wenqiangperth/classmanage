package com.example.common.dao;

import com.example.common.entity.Course;
import com.example.common.entity.TeamShareVO;
import com.example.common.entity.TeamValidVO;
import com.example.common.mapper.CourseMapper;
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

    public ArrayList<TeamValidVO> getAllTeamValidByTeacherId(Long teacherId)
    {
        return requestMapper.getAllTeamValidByTeacherId(teacherId);
    }

    public TeamValidVO getTeamValidByTeamValidId(Long teamValidId)
    {
        return requestMapper.getTeamValidByTeamValidId(teamValidId);
    }


}
