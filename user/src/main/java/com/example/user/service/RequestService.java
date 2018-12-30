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

    @Autowired
    private RoundDao roundDao;

    public ArrayList<TeamShareVO> getAllTeamShareRequestByTeacherId(long teacherId)
    {
        ArrayList<CourseVO> courses=courseDao.getAllCourseByTeacherId(teacherId);
        ArrayList<TeamShareVO> teamShareVOS=new ArrayList<>();
        for(CourseVO course:courses)
        {
            teamShareVOS.addAll(courseDao.getAllTeamShare(course.getCourseId()));
        }
        return teamShareVOS;

    }

    public ArrayList<SeminarShareVO> getAllSeminarShareRequestBycourseId(long teacherId)
    {
        return courseDao.getAllSeminarShare(teacherId);
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
        ArrayList<CourseVO> courses = courseDao.getAllCourseByTeacherId(teacherId);
        for(TeamValidVO teamValidVO:teamValidVOS)
        {
            if(teamValidVO!=null) {
                teamValidVO.setTeam(teamDao.getTeamByTeamId(teamValidVO.getTeamId()));
                System.out.println(teamValidVO);
                teamValidVO.setKlass(klassDao.getClassByClassId(teamValidVO.getTeam().getKlassId()));
                teamValidVO.setCourse(courseDao.getCourseById(teamValidVO.getKlass().getCourseId()));
                }
            }


        return teamValidVOS;
    }

    public TeamValidVO getTeamValidByTeamValidId(Long teamValidId)
    {
        TeamValidVO teamValidVO=requestDao.getTeamValidByTeamValidId(teamValidId);
        ArrayList<CourseVO> courses = courseDao.getAllCourseByTeacherId(teamValidVO.getTeacherId());
        teamValidVO.setTeam(teamDao.getTeamByTeamId(teamValidVO.getTeamId()));
        teamValidVO.setKlass(klassDao.getClassByClassId(teamValidVO.getTeam().getKlassId()));
        for(CourseVO course:courses)
        {
            if(teamValidVO.getTeamId().equals(course.getTeamId()))
            {
                teamValidVO.setCourse(courseDao.getCourseById(course.getCourseId()));

            }

        }
        return teamValidVO;
    }

    public Long updateTeamShareRequestById(Long teamShareId,Long status)
    {
        if(status==1) {
            Long subCourseId = courseDao.getTeamShareByTeamShareId(teamShareId).getSubCourseId();
            ArrayList<Klass> klasses = courseDao.getAllClassByCourseId(subCourseId);
            ArrayList<Team> teams = new ArrayList<Team>();
            for (Klass klass : klasses) {
                teams.addAll(teamDao.getAllTeamsByCourseId(klass.getId(), subCourseId));
                klassDao.deleteAllKlassTeamByClassId(klass.getId());
            }
            for (Team team : teams) {
                team=teamDao.getTeamByTeamId(team.getId());
                teamDao.deleteTeamById(team.getId());
            }
            Long mainCourseId = courseDao.getTeamShareByTeamShareId(teamShareId).getMainCourseId();
            ArrayList<Klass> mainKlasses=courseDao.getAllClassByCourseId(mainCourseId);
            ArrayList<Team> mainTeams = new ArrayList<Team>();
            for(Klass mainKlass:mainKlasses)
            {
                mainTeams.addAll(teamDao.getAllTeamsByCourseId(mainKlass.getId(),mainCourseId));
            }
            for(Team mainTeam:mainTeams)
            {
                int max=0;
                Long maxKlassId=0L;
                mainTeam=teamDao.getTeamByTeamId(mainTeam.getId());
                ArrayList<Long> classIds=new ArrayList<Long>();
                for(Student student:(mainTeam.getStudents()))
                {
                    Long temp=klassDao.getClassIdByCourseIdAndStudentId(subCourseId,student.getId());
                    if(temp!=null) {
                        classIds.add(temp);
                    }
                }
                for(Klass klass:klasses) {
                    int count = 0;
                    for (Long classId : classIds) {

                        if (klass.getId() == classId) {
                            count++;
                        }
                    }
                    if (count >= max) {
                        max = count;
                        maxKlassId = klass.getId();
                    }
                }
                System.out.println(maxKlassId+" "+mainTeam.getId());
                teamDao.insertKlassTeam(maxKlassId,mainTeam.getId());
//                for(Student student:(mainTeam.getStudents()))
//                {
//                    if(courseDao.isSelectCourse(subCourseId,student.getId())!=0)
//                    {
//                        teamDao.insertTeamStudent(mainTeam.getId(),student.getId());
//                    }
//                }
            }
            courseDao.updateTeamMainCourseIdByCourseId(mainCourseId,subCourseId);
         }
        return requestDao.updateTeamShareRequestById(teamShareId,status);
    }

    public Long updateSeminarShareRequestById(Long seminarShareId,int status)
    {
        if(status==1) {
            Long subCourseId = courseDao.getSeminarShareBySeminarShareId(seminarShareId).getSubCourseId();
            Long mainCourseId = courseDao.getSeminarShareBySeminarShareId(seminarShareId).getMainCourseId();
            ArrayList<Klass> klasses = courseDao.getAllClassByCourseId(subCourseId);
            ArrayList<Round> subRounds=roundDao.selectAllRoundByCourseId(subCourseId);
            ArrayList<Seminar> subSeminars=seminarDao.findAllSeminarByCourseId(subCourseId);
            seminarDao.deleteAllSeminarByCourseId(subCourseId);
            roundDao.deleteAllRoundByCourseId(subCourseId);
            for(Klass subKlass:klasses)
            {
                klassDao.deleteAllKlassRoundByKlassId(subKlass.getId());
                klassDao.deleteAllKlassSeminarByKlassId(subKlass.getId());
            }
            ArrayList<Round> mainRounds=roundDao.selectAllRoundByCourseId(mainCourseId);
            ArrayList<Seminar> mainSeminars=seminarDao.findAllSeminarByCourseId(mainCourseId);
            for(Klass subKlass:klasses)
            {
                for(Round mainRound:mainRounds)
                {
                    klassDao.insertKlassRound(subKlass.getId(),mainRound.getId());
                }
                for(Seminar mainSeminar:mainSeminars)
                {
                    klassDao.insertKlassSeminar(subKlass.getId(),mainSeminar.getId(),null,0);
                }
            }
        }
        return requestDao.updateSeminarShareRequestById(seminarShareId,status);
    }

    public Long updateTeamValidRequestById(Long teamValidId,Long status)
    {
        return requestDao.updateTeamValidRequestById(teamValidId,status);
    }
}
