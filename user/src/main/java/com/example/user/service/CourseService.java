package com.example.user.service;

import com.example.common.entity.Course;
import com.example.common.dao.CourseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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

    public Course getCourseById(long courseId)
    {
        return courseDao.getCourseById(courseId);
    }

    public long addCourse(long teacherId, String courseName, String introduction, int presentationPercentage, int questionPercentage, int reportPercentage, Date teamStartTime, Date teamEndTime, long teamMainCourseId, long seminarMainCourseId)
    {
        return courseDao.addCourse(teacherId,courseName,introduction,presentationPercentage,questionPercentage,reportPercentage,teamStartTime,teamEndTime,teamMainCourseId,seminarMainCourseId);
    }

    public long deleteCourseById(long courseId)
    {
        return courseDao.deleteCourseById(courseId);
    }

    public List<Course> getAllCourse()
    {
        return courseDao.getAllCourse();
    }


}
