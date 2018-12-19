package com.example.user.controller;

import com.example.common.entity.Course;
import com.example.common.entity.Round;
import com.example.user.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * @author perth
 * @ClassName CourseController
 * @Description TODO
 * @Date 2018/12/17 19:05
 * @Version 1.0
 **/

@RestController
@RequestMapping
public class CourseController {

//    @Autowired
//    private CourseService courseService;
//
//    @PostMapping
//    public long addCourse(@RequestBody Course course)
//    {
//        return courseService.addCourse(course.getTeacherId(),course.getCourseName(),course.getIntroduction(),course.getPresentationPercentage(),course.getQuestionPercentage(),course.getReportPercentage(),course.getTeamStartTime(),course.getTeamEndTime(),course.getTeamMainCourseId(),course.getSeminarMainCourseId());
//    }
//
//    @PostMapping(value = "/course/{courseId}")
//    public Course getCourseById(@PathVariable(value="courseId") long courseId){
//        return courseService.getCourseById(courseId);
//    }
//
//    @GetMapping(value="course")
//    public List<Course> getAllCourse()
//    {
//
//        return courseService.getAllCourse();
//    }
//
//    @DeleteMapping(value="course/{courseId}")
//    public long deleteCourseById(@PathVariable(value="courseId")long courseId)
//    {
//        return courseService.deleteCourseById(courseId);
//    }

    @GetMapping(value="course/{courseId}/round")
    public List<Round> getAllRoundByCourseId(@PathVariable(value="courseId")long courseId)
    {
        return courseService.getAllRoundByCourseId(courseId);
    }

}
