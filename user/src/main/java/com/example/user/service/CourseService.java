package com.example.user.service;

import com.example.common.dao.TeamDao;
import com.example.common.entity.*;
import com.example.common.dao.CourseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    private TeamDao teamDao;
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
     * @param course
     * @return
     */
    public long addCourse(Course course)
    {
        return courseDao.addCourse(course);
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
    public ArrayList<CourseVO> getAllCourseById(String role, Long id)
    {
        if(role.equals("ROLE_STUDENT")){
            return courseDao.getAllCourseByStudentId(id);
        }
        else if(role.equals("ROLE_TEACHER")){
            return courseDao.getAllCourseByTeacherId(id);
        }
        return null;
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

    public ArrayList<Team> getAllTeamByCourseId(long courseId)
    {
        return courseDao.getAllTeamByCourseId(courseId);
    }

    public ArrayList<Klass> getAllClassByCourseId(long courseId){
        return courseDao.getAllClassByCourseId(courseId);
    }

    public Team getTeamByCourseIdAndStudentId(long studentId,long courseId)
    {
        return courseDao.getTeamByCourseIdAndStudentId(studentId,courseId);
    }

    @Transactional(rollbackFor = Exception.class)
    public Long deleteTeamShareByTeamShareId(long teamShareId)
    {
        Long temp=(long)1;
        ArrayList<Team> teams=courseDao.deleteTeamShareByTeamShareId(teamShareId);
        for(Team team:teams)
        {
            temp=teamDao.deleteTeamById(team.getId());
        }
        return temp;
    }

    public Long deleteSeminarShareBySeminarShareId(long seminarShareId)
    {
        return courseDao.deleteSeminarShareBySeminarShareId(seminarShareId);
    }

    public Long createTeamShareRequest(Long courseId,Long subCourseId)
    {
        return courseDao.createTeamShareRequest(courseId,subCourseId);
    }

    public Long createSeminarShareRequest(Long courseId,Long subCourseId)
    {
        return courseDao.createSeminarShareRequest(courseId,subCourseId);
    }
}
