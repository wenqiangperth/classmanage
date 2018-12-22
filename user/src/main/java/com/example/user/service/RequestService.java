package com.example.user.service;

import com.example.common.dao.*;
import com.example.common.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private TeamDao teamDao;

    @Autowired
    private KlassDao klassDao;

    @Autowired
    private SeminarDao seminarDao;

    public ArrayList<TeamShareVO> getAllTeamShareRequestBycourseId(long courseId)
    {
        return courseDao.getAllTeamShare(courseId);
    }

    public ArrayList<SeminarShareVO> getAllSeminarShareRequestBycourseId(long courseId)
    {
        return courseDao.getAllSeminarShare(courseId);
    }

    public TeamShareVO getTeamShareRequestById(long teamShareId)
    {
        return courseDao.getTeamShareByTeamShareId(teamShareId);
    }

    public SeminarShareVO getSeminarShareRequestById(long seminarShareId)
    {
        return courseDao.getSeminarShareBySeminarShareId(seminarShareId);
    }

    public ArrayList<TeamValidVO> getAllTeamValidByTeacherId(Long teacherId)
    {
        ArrayList<TeamValidVO> teamValidVOS=requestDao.getAllTeamValidByTeacherId(teacherId);
        ArrayList<StudentCourseVO> courses = courseDao.getAllCourseByTeacherId(teacherId);
        for(TeamValidVO teamValidVO:teamValidVOS)
        {
            teamValidVO.setTeam(teamDao.getTeamById(teamValidVO.getTeamId()));
            teamValidVO.setKlass(klassDao.getClassByClassId(teamValidVO.getTeam().getKlassId()));
            for(StudentCourseVO course:courses)
            {
                if(teamValidVO.getTeamId()==course.getTeamId())
                {
                    teamValidVO.setCourse(courseDao.getCourseById(course.getCourseId()));

                }
            }

        }
        return teamValidVOS;
    }

    public TeamValidVO getTeamValidByTeamValidId(Long teamValidId)
    {
        TeamValidVO teamValidVO=requestDao.getTeamValidByTeamValidId(teamValidId);
        ArrayList<StudentCourseVO> courses = courseDao.getAllCourseByTeacherId(teamValidVO.getTeacherId());
        teamValidVO.setTeam(teamDao.getTeamById(teamValidVO.getTeamId()));
        teamValidVO.setKlass(klassDao.getClassByClassId(teamValidVO.getTeam().getKlassId()));
        for(StudentCourseVO course:courses)
        {
            if(teamValidVO.getTeamId()==course.getTeamId())
            {
                teamValidVO.setCourse(courseDao.getCourseById(course.getCourseId()));

            }

        }
        return teamValidVO;
    }

    public Long updateTeamShareRequestById(Long teamShareId,int status)
    {
        if(status==1) {
            Long subCourseId = courseDao.getTeamShareByTeamShareId(teamShareId).getSubCourseId();
            ArrayList<Klass> klasses = courseDao.getAllClassByCourseId(subCourseId);
            ArrayList<Team> teams = new ArrayList<Team>();
            for (Klass klass : klasses) {
                teams.addAll(teamDao.getAllTeamsByCourseId(klass.getId(), subCourseId));
            }
            for (Team team : teams) {
                teamDao.deleteTeamById(team.getId());
            }
        }
        return requestDao.updateTeamShareRequestById(teamShareId,status);
    }

    public Long updateSeminarShareRequestById(Long seminarShareId,int status)
    {
        if(status==1) {
            Long subCourseId = courseDao.getSeminarShareBySeminarShareId(seminarShareId).getSubCourseId();
            Long mainCourseId = courseDao.getSeminarShareBySeminarShareId(seminarShareId).getMainCourseId();
            ArrayList<Klass> klasses = courseDao.getAllClassByCourseId(subCourseId);
            seminarDao.deleteAllSeminarByCourseId(subCourseId);
            for (Klass klass : klasses) {
                seminarDao.deleteAllClassSeminarByClassId(klass.getId());
            }
            ArrayList<Seminar> seminars = seminarDao.findAllSeminarByCourseId(mainCourseId);
            for (Seminar seminar : seminars) {
                seminarDao.addSeminar(seminar);
            }
        }
        return requestDao.updateSeminarShareRequestById(seminarShareId,status);
    }

    public Long updateTeamValidRequestById(Long teamValidId,int status)
    {
        return requestDao.updateTeamValidRequestById(teamValidId,status);
    }
}
