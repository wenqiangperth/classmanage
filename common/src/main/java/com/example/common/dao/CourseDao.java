package com.example.common.dao;

import com.example.common.entity.*;
import com.example.common.mapper.CourseMapper;
import com.example.common.mapper.StudentMapper;
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

        return courseMapper.getAllTeamShare(courseId);
    }

    public ArrayList<SeminarShareVO> getAllSeminarShare(long courseId)
    {
        return courseMapper.getAllSeminarShare(courseId);
    }
}
