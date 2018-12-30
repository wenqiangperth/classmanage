package com.example.user.controller;

import com.example.common.entity.*;
import com.example.user.service.CourseService;
import com.example.user.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * @author perth
 * @ClassName RequestController
 * @Description TODO
 * @Date 2018/12/17 19:08
 * @Version 1.0
 **/
@RestController
@RequestMapping(value="/request")
public class RequestController {
    @Autowired
    private RequestService requestService;

    @Autowired
    private CourseService courseService;

    /**
     * 根据课程id获得所有请求
     * @param teacherId
     * @return
     */
    @GetMapping(value="/teamshare")
    public ArrayList<TeamShareVO> getAllTeamShareRequestByTeacherId(@RequestParam Long teacherId)
    {
        return requestService.getAllTeamShareRequestByTeacherId(teacherId);
    }

    /**
     * 根据课程id获得所有讨论课共享请求
     * @param teacherId
     * @return
     */
    @GetMapping(value="/seminarshare")
    public ArrayList<SeminarShareVO> getAllSeminarShareRequestBycourseId(@RequestParam Long teacherId)
    {
        return requestService.getAllSeminarShareRequestBycourseId(teacherId);
    }

    /**
     * 根据id获得对应的组队共享请求
     * @param teamShareId
     * @return
     */
    @GetMapping("/teamshare/{teamshareId}")
    public TeamShareVO getTeamShareRequestById(@PathVariable(value="teamshareId") Long teamShareId)
    {
        return requestService.getTeamShareRequestById(teamShareId);
    }

    /**
     * 根据id获得对应的讨论课共享请求
     * @param seminarShareId
     * @return
     */
    @GetMapping("/seminarshare/{seminarshareId}")
    public SeminarShareVO getSeminarShareRequestById(@PathVariable(value="seminarshareId") Long seminarShareId)
    {
        return requestService.getSeminarShareRequestById(seminarShareId);
    }

    /**
     * 根据教师id查看所有组队申请
     * @param teacherId
     * @return
     */
    @GetMapping("/teamvalid")
    public ArrayList<TeamValidVO> getAllTeamValidByTeacherId(@RequestParam(value="teacherId") Long teacherId)
    {
        return requestService.getAllTeamValidByTeacherId(teacherId);
    }

    /**
     * 根据teamValidId查看组队合法请求
     * @param teamValidId
     * @return
     */
    @GetMapping("/teamvalid/{teamvalidId}")
    public TeamValidVO getTeamValidByTeamValidId(@PathVariable(value="teamvalidId")Long teamValidId)
    {
        return requestService.getTeamValidByTeamValidId(teamValidId);
    }

    /**
     * 处理组队共享请求
     * @param teamShareId
     * @param teamShareVO
     * @return
     */
    @PutMapping("/teamshare/{teamshareId}")
    public Long updateTeamShareRequestById(@PathVariable(value="teamshareId")Long teamShareId,@RequestBody TeamShareVO teamShareVO)
    {
        return requestService.updateTeamShareRequestById(teamShareId,teamShareVO.getStatus());
    }

    /**
     * 处理讨论课共享请求
     * @param seminarShareId
     * @param seminarShareVO
     * @return
     */
    @PutMapping("/seminarshare/{seminarshareId}")
    public Long updateSeminarShareRequestById(@PathVariable(value="seminarShareId")Long seminarShareId,@RequestBody SeminarShareVO seminarShareVO)
    {
        return requestService.updateSeminarShareRequestById(seminarShareId,seminarShareVO.getStatus());
    }

    @PutMapping("/teamvalid/{teamvalidId}")
    public Long updateTeamValidRequestById(@PathVariable(value="teamvalidId")Long teamValidId,@RequestBody TeamValidVO teamValidVO)
    {
        return requestService.updateTeamValidRequestById(teamValidId,teamValidVO.getStatus());
    }

}
