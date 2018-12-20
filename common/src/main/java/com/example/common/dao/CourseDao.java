package com.example.common.dao;

import com.example.common.entity.*;
import com.example.common.mapper.CourseMapper;
import com.example.common.mapper.StudentMapper;
import com.example.common.mapper.TeacherMapper;
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
        return courseMapper.getAllClassByCourseId(courseId);
    }

    public ArrayList<Student> getAllNoTeamByCourseId(long courseId)
    {
        ArrayList<Student> students = new ArrayList<Student>();
        ArrayList<Long> studentIds = courseMapper.getAllNoTeamStudentByCourseId(courseId);
        for(Long studentId:studentIds)
        {
            Student student = studentMapper.getStudentById(studentId);
            students.add(student);
        }
        return students;
    }

    public ArrayList<TeamShareVO> getAllTeamShare(long courseId)
    {
        ArrayList<TeamShareVO> teamShareVOS=courseMapper.getAllTeamShare(courseId);
        for(TeamShareVO teamShareVO:teamShareVOS)
        {
            Course mainCourse = courseMapper.getCourseById(teamShareVO.getMainCourseId());
            teamShareVO.setMainCourseName(mainCourse.getCourseName());
            teamShareVO.setMainCourseTeacherId(mainCourse.getTeacherId());
            teamShareVO.setMainCourseTeacherName(teacherMapper.getTeacherById(mainCourse.getTeacherId()).getTeacherName());
            if(courseId==teamShareVO.getMainCourseId()) {
                teamShareVO.setMainCourse(true);
            }
            Course subCourse = courseMapper.getCourseById(teamShareVO.getSubCourseId());
            teamShareVO.setSubCourseName(subCourse.getCourseName());
            teamShareVO.setMainCourseTeacherId(subCourse.getTeacherId());
            teamShareVO.setSubCourseTeacherName(teacherMapper.getTeacherById(subCourse.getTeacherId()).getTeacherName());

        }
        return teamShareVOS;
    }

    public ArrayList<SeminarShareVO> getAllSeminarShare(long courseId)
    {
        ArrayList<SeminarShareVO> seminarShareVOS=courseMapper.getAllSeminarShare(courseId);
        for(SeminarShareVO seminarShareVO:seminarShareVOS)
        {
            Course mainCourse = courseMapper.getCourseById(seminarShareVO.getMainCourseId());
            seminarShareVO.setMainCourseName(mainCourse.getCourseName());
            seminarShareVO.setMainCourseTeacherId(mainCourse.getTeacherId());
            seminarShareVO.setMainCourseTeacherName(teacherMapper.getTeacherById(mainCourse.getTeacherId()).getTeacherName());
            if(courseId==seminarShareVO.getMainCourseId()) {
                seminarShareVO.setMainCourse(true);
            }
            Course subCourse = courseMapper.getCourseById(seminarShareVO.getSubCourseId());
            seminarShareVO.setSubCourseName(subCourse.getCourseName());
            seminarShareVO.setMainCourseTeacherId(subCourse.getTeacherId());
            seminarShareVO.setSubCourseTeacherName(teacherMapper.getTeacherById(subCourse.getTeacherId()).getTeacherName());

        }
        return seminarShareVOS;
    }
    
}
