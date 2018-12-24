package com.example.user.controller;

import com.example.common.entity.*;
import com.example.user.service.CourseService;
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
    @PostMapping(value = "")
    public long addCourse(HttpServletRequest request, @RequestBody Course course)
    {
        Long id=Long.parseLong(request.getAttribute("id").toString());
        course.setTeacherId(id);
        return courseService.addCourse(course);
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
    public ArrayList<CourseVO> getAllCourse(HttpServletRequest request)
    {
        String role=request.getAttribute("role").toString();
        Long id=Long.parseLong(request.getAttribute("id").toString());
        return courseService.getAllCourseById(role,id);
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

    /**
     * 查看我的组队
     * @param request
     * @param courseId
     * @return
     */
  @GetMapping(value="/{courseId}/myteam")
  public Team getTeamByCourseIdAndStudentId(HttpServletRequest request,@PathVariable(value="courseId")long courseId)
  {
      long studentId=(long)request.getAttribute("id");
      return courseService.getTeamByCourseIdAndStudentId(studentId,courseId);
  }

    /**
     * 根据teamShareId取消共享
     * @param teamShareId
     * @return
     */
  @DeleteMapping(value="teamshare/{teamshareId}")
   public Long deleteTeamShareByTeamShareId(@PathVariable(value = "teamshareId") long teamShareId)
  {
      return courseService.deleteTeamShareByTeamShareId(teamShareId);
  }
    /**
     * 根据seminarShareId取消共享
     * @param seminarShareId
     * @return
     */
    @DeleteMapping(value="seminarshare/{seminarshareId}")
    public Long deleteSeminarShareBySeminarShareId(@PathVariable(value = "seminarshareId") long seminarShareId)
    {
        return courseService.deleteSeminarShareBySeminarShareId(seminarShareId);
    }

    /**
     * 发起一个组队共享请求
     * @param courseId
     * @param subCourseId
     * @return
     */
    @PostMapping(value="/{courseId}/teamsharerequest")
    public Long createTeamShareRequest(@PathVariable(value="courseId") Long courseId,@RequestParam Long subCourseId)
    {
        return courseService.createSeminarShareRequest(courseId,subCourseId);
    }

    /**
     * 发起一个讨论课共享请求
     * @param courseId
     * @param subCourseId
     * @return
     */
    @PostMapping(value="/{courseId}/seminarsharerequest")
    public Long createSeminarShareRequest(@PathVariable(value="courseId") Long courseId,@RequestParam Long subCourseId)
    {
        return courseService.createSeminarShareRequest(courseId,subCourseId);
    }
}
