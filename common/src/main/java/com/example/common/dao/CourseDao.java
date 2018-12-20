package com.example.common.dao;

import com.example.common.entity.*;
import com.example.common.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * @ClassName CourseDao
 * @Description
 * @Author perth
 * @Date 2018/12/18 0018 下午 4:41
 * @Version 1.0
 **/
@Repository
public class CourseDao {

    @Autowired
    private  CourseMapper courseMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private KlassMapper klassMapper;

    @Autowired
    private TeamMapper teamMapper;

    public Course getCourseById(long courseId) {
        return courseMapper.getCourseById(courseId);
    }

    public long addCourse(long teacherId, String courseName, String introduction, int presentationPercentage, int questionPercentage, int reportPercentage, Date teamStartTime, Date teamEndTime, long teamMainCourseId, long seminarMainCourseId)
    {
        return courseMapper.addCourse(teacherId,courseName,introduction,presentationPercentage,questionPercentage,reportPercentage,teamStartTime,teamEndTime,teamMainCourseId,seminarMainCourseId);
    }

    public long deleteCourseById(long courseId)
    {
        return courseMapper.deleteCourseById(courseId);
    }

    public ArrayList<Course> getAllCourse()
    {

        return courseMapper.getAllCourse();
    }

    public ArrayList<Round> getAllRoundByCourseId(long courseId)
    {
        return courseMapper.getAllRoundByCourseId(courseId);
    }

    public ArrayList<Klass> getAllClassByCourseId(long courseId)
    {
        return klassMapper.getAllClassByCourseId(courseId);
    }

    public ArrayList<Student> getAllNoTeamByCourseId(long courseId)
    {
        ArrayList<Student> students = new ArrayList<Student>();
        ArrayList<Long> studentIds = courseMapper.getAllNoTeamStudentByCourseId(courseId);
        for(Long studentId:studentIds)
        {
            Student student = studentMapper.selectStudentById(studentId);
            students.add(student);
        }
        return students;
    }

    public ArrayList<TeamShareVO> getAllTeamShare(long courseId)
    {
        ArrayList<TeamShareVO> teamShareVOS=courseMapper.getAllTeamShare(courseId);
        for(TeamShareVO teamShareVO:teamShareVOS){
            Course mainCourse = courseMapper.getCourseById(teamShareVO.getMainCourseId());
            teamShareVO.setMainCourseName(mainCourse.getCourseName());
            teamShareVO.setMainCourseTeacherId(mainCourse.getTeacherId());
            teamShareVO.setMainCourseTeacherName(teacherMapper.selectTeacherById(mainCourse.getTeacherId()).getTeacherName());
            if(courseId==teamShareVO.getMainCourseId()) {
                teamShareVO.setMainCourse(true);
            }
            Course subCourse = courseMapper.getCourseById(teamShareVO.getSubCourseId());
            teamShareVO.setSubCourseName(subCourse.getCourseName());
            teamShareVO.setMainCourseTeacherId(subCourse.getTeacherId());
            teamShareVO.setSubCourseTeacherName(teacherMapper.selectTeacherById(subCourse.getTeacherId()).getTeacherName());
        }
        return teamShareVOS;
    }

    public ArrayList<Team> getAllTeamByCourseId(long courseId)
    {
           ArrayList<Team> teams = new ArrayList<Team>();
           ArrayList<Klass> klasses=klassMapper.getAllClassByCourseId(courseId);
           for(Klass klass:klasses)
           {
               ArrayList<Team> temps = teamMapper.selectTeamsByCourseIdAndClassId(klass.getCourseId(),courseId);
               for(Team temp:temps)
               {
                   temp.setStudents(teamMapper.selectStudentsByTeamId(temp.getId()));
               }
               teams.addAll(temps);
           }
           return teams;
    }
}
