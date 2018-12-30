package com.example.user.service;

import com.example.common.dao.TeamDao;
import com.example.common.entity.*;
import com.example.common.dao.CourseDao;
import com.example.common.mapper.TeamStrategyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private final static String ROLE_STUDENT="ROLE_STUDENT";
    private final static String ROLE_TEACHER="ROLE_TEACHER";
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
     * @param courseDTO
     * @return
     */
    public long addCourse(CourseDTO courseDTO)
    {
        return courseDao.addCourse(courseDTO);
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

    public ArrayList<RoundTeamScoreVO>getRoundTeamScoreByCourseIdAndRoundId(Long courseId,Long roundId){
        return courseDao.getRoundTeamScoreByCourseIdAndRoundId(courseId,roundId);
    }

    /**
     * 查询所有课程
     * @return
     */
    public ArrayList<CourseVO> getAllCourseById(String role, Long id)
    {
        if((ROLE_STUDENT).equals(role)){
            return courseDao.getAllCourseByStudentId(id);
        }
        else if((ROLE_TEACHER.equals(role))){
            return courseDao.getAllCourseByTeacherId(id);
        }
        return null;
    }

    public ArrayList<CourseVO> getAllCourses()
    {
        return courseDao.getAllCourses();
    }

    /**
     * 查询课程轮次
     * @param courseId
     * @return
     */
    public ArrayList<Round> getAllRoundByCourseId(Long courseId)
    {
        Course course=courseDao.getCourseById(courseId);
        Long id=courseId;
        if(course.getSeminarMainCourseId()!=null&&course.getSeminarMainCourseId()!=0){
            id=course.getSeminarMainCourseId();
        }
        return courseDao.getAllRoundByCourseId(id);
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

//    public ArrayList<CourseVO> getAllCourses()
//    {
//        return courseDao.getAllCourses();
//    }


    public ArrayList<SeminarShareVO> getAllSeminarShare(long courseId)
    {
        return courseDao.getAllSeminarShare(courseId);
    }

    public ArrayList<Team> getAllTeamByCourseId(Long courseId)
    {
        ArrayList<Klass> klasses=courseDao.getAllClassByCourseId(courseId);
        ArrayList<Long> teamIds=new ArrayList<>();
        ArrayList<Team> teams=new ArrayList<>();
        for(Klass klass:klasses)
        {
            teamIds.addAll(teamDao.getAllTeamIdByClassId(klass.getId()));
        }
        for(Long teamId:teamIds)
        {
            Team temp=teamDao.getTeamInfoById(teamId);
//            ArrayList<Student> selectStudents=new ArrayList<>();
//            for (Student tempStudent:(temp.getStudents()))
//            {
//                if(courseDao.isSelectCourse(courseId,tempStudent.getId())==1)
//                {
//                    selectStudents.add(tempStudent);
//                }
//            }
//            temp.setStudents(selectStudents);
            teams.add(temp);
        }
        return teams;
    }

    public ArrayList<Klass> getAllClassByCourseId(long courseId){
        return courseDao.getAllClassByCourseId(courseId);
    }

    public Team getTeamByCourseIdAndStudentId(long studentId,long courseId)
    {
        return courseDao.getTeamByCourseIdAndStudentId(studentId,courseId);
    }

    @Transactional(rollbackFor = Exception.class)
    public Long deleteTeamShareByTeamShareId(long teamShareId) {

        return courseDao.deleteTeamShareByTeamShareId(teamShareId);
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
