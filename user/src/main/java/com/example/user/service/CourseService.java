package com.example.user.service;

import com.example.common.entity.*;
import com.example.common.dao.CourseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.ArrayList;


/**
 * @author perth
 * @ClassName CourseService
 * @Description TODO
 * @Date 2018/12/17 19:05
 * @Version 1.0
 **/

@Service
public class CourseService {
    @Autowired
    private CourseDao courseDao;

    /**
     * 获取课程信息
     * @param courseId
     * @return
     */
    public Course getCourseById(long courseId)
    {
        return courseDao.getCourseById(courseId);
    }

    /**
     * 添加课程
     * @param teacherId
     * @param courseName
     * @param introduction
     * @param presentationPercentage
     * @param questionPercentage
     * @param reportPercentage
     * @param teamStartTime
     * @param teamEndTime
     * @param teamMainCourseId
     * @param seminarMainCourseId
     * @return
     */
    public long addCourse(long teacherId, String courseName, String introduction, int presentationPercentage, int questionPercentage, int reportPercentage, Date teamStartTime, Date teamEndTime, long teamMainCourseId, long seminarMainCourseId)
    {
        return courseDao.addCourse(teacherId,courseName,introduction,presentationPercentage,questionPercentage,reportPercentage,teamStartTime,teamEndTime,teamMainCourseId,seminarMainCourseId);
    }

    /**
     * 删除课程
     * @param courseId
     * @return
     */
    public long deleteCourseById(long courseId)
    {
        return courseDao.deleteCourseById(courseId);
    }

    /**
     * 查询所有课程
     * @return
     */
    public ArrayList<Course> getAllCourse()
    {
        return courseDao.getAllCourse();
    }

    /**
     * 查询课程轮次
     * @param courseId
     * @return
     */
    public ArrayList<Round> getAllRoundByCourseId(long courseId)
    {
        return courseDao.getAllRoundByCourseId(courseId);
    }

    /**
     * 查询未组队学生
     * @param courseId
     * @return
     */
    public ArrayList<Student> getAllNoTeamByCourseId(long courseId)
    {
        return courseDao.getAllNoTeamByCourseId(courseId);
    }

    public ArrayList<TeamShareVO> getAllTeamShare(long courseId)
    {
        return courseDao.getAllTeamShare(courseId);
    }

    public ArrayList<SeminarShareVO> getAllSeminarShare(long courseId)
    {
        return courseDao.getAllSeminarShare(courseId);
    }


}
