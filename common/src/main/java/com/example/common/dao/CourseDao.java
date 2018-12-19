package com.example.common.dao;

import com.example.common.entity.Course;
import com.example.common.entity.Klass;
import com.example.common.entity.Round;
import com.example.common.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

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

    public List<Course> getAllCourse()
    {

        return courseMapper.getAllCourse();
    }

    public List<Round> getAllRoundByCourseId(long courseId)
    {
        return courseMapper.getAllRoundByCourseId(courseId);
    }

    public List<Klass> getAllClassByCourseId(long courseId)
    {
        return courseMapper.getAllClassByCourseId(courseId);
    }
}
