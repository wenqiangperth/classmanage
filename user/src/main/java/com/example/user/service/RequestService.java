package com.example.user.service;

import com.example.common.dao.CourseDao;
import com.example.common.entity.SeminarShareVO;
import com.example.common.entity.TeamShareVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.common.dao.RequestDao;
import java.util.ArrayList;

/**
 * @author perth
 * @ClassName RequestService
 * @Description teacher处理各类请求
 * @Date 2018/12/17 19:08
 * @Version 1.0
 **/
@Service
public class RequestService {

    @Autowired
    private RequestDao requestDao;

    @Autowired
    private CourseDao courseDao;

    public ArrayList<TeamShareVO> getAllTeamShareRequestBycourseId(long courseId)
    {
        return courseDao.getAllTeamShare(courseId);
    }

    public ArrayList<SeminarShareVO> getAllSeminarShareRequestBycourseId(long courseId)
    {
        return courseDao.getAllSeminarShare(courseId);
    }

}
