package com.example.user.controller;

import com.example.common.entity.*;
import com.example.user.service.CourseService;
import com.example.user.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.util.ArrayList;

/**
 * @author perth
 * @ClassName CourseController
 * @Description TODO
 * @Date 2018/12/17 19:05
 * @Version 1.0
 **/

@RestController
@RequestMapping(value="/course")
public class CourseController {

   @Autowired
    private CourseService courseService;
    /**
     * 添加课程
     * @param course
     * @return
     */
    @PostMapping
    public long addCourse(@RequestBody Course course)
    {
        return courseService.addCourse(course.getTeacherId(),course.getCourseName(),course.getIntroduction(),course.getPresentationPercentage(),course.getQuestionPercentage(),course.getReportPercentage(),course.getTeamStartTime(),course.getTeamEndTime(),course.getTeamMainCourseId(),course.getSeminarMainCourseId());
    }

    /**
     * 根据课程id获取课程信息
     * @param courseId
     * @return
     */
    @GetMapping(value = "/{courseId}")
    public Course getCourseById(@PathVariable(value="courseId") long courseId){
        return courseService.getCourseById(courseId);
    }

    /**
     * 查看所有课程
     * @return
     */
    @GetMapping(value="")
    public ArrayList<StudentCourseVO> getAllCourse(HttpServletRequest request)
    {
        return courseService.getAllCourseById(request);
    }

    /**
     * 根据课程id删除课程信息
     * @param courseId
     * @return
     */
    @DeleteMapping(value="/{courseId}")
    public long deleteCourseById(@PathVariable(value="courseId")long courseId)
    {
        return courseService.deleteCourseById(courseId);
    }

    /**
     * 根据课程Id查询所有轮次
     * @param courseId
     * @return
     */
    @GetMapping(value="/{courseId}/round")
    public ArrayList<Round> getAllRoundByCourseId(@PathVariable(value="courseId")long courseId) {
     return courseService.getAllRoundByCourseId(courseId);
    }

  /**
   * 根据课程id查看所有共享组队信息
   * @param courseId
   * @return
   */
  @GetMapping(value="/{courseId}/teamshare")
  public ArrayList<TeamShareVO> getAllTeamShare(@PathVariable(value="courseId") long courseId){
       return courseService.getAllTeamShare(courseId);
  }

 /**
  * 根据课程id查看所有共享讨论课信息
  * @param courseId
  * @return
  */
 @GetMapping(value="/{courseId}/seminarshare")
    public ArrayList<SeminarShareVO> getAllSeminarShare(@PathVariable(value="courseId") long courseId){
         return courseService.getAllSeminarShare(courseId);
     }

    /**
     * 根据课程id获得所有队伍
     * @param courseId
     * @return
     */
  @GetMapping(value="/{courseId}/team")
  public ArrayList<Team> getAllTeamByCourseId(@PathVariable(value="courseId")long courseId)
  {
      return courseService.getAllTeamByCourseId(courseId);
  }

    /**
     * 根据课程id获取所有未组队学生信息
     * @param courseId
     * @return
     */
  @GetMapping(value="/{courseId}/noTeam")
  public ArrayList<Student> getAllNoTeamByCourseId(@PathVariable(value="courseId")long courseId)
  {
      return courseService.getAllNoTeamByCourseId(courseId);
  }

    /**
     * 根据课程id获得所有班级
     * @param courseId
     * @return
     */
  @GetMapping(value="/{courseId}/class")
  public ArrayList<Klass> getAllClassByCourseId(@PathVariable(value="courseId")long courseId){
      return courseService.getAllClassByCourseId(courseId);
  }
}
