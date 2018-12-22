package com.example.user.controller;

import com.example.common.entity.SeminarShareVO;
import com.example.common.entity.TeamShareVO;
import com.example.common.entity.TeamValidVO;
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

    /**
     * 根据课程id获得所有请求
     * @param courseId
     * @return
     */
    @GetMapping(value="/teamshare")
    public ArrayList<TeamShareVO> getAllTeamShareRequestBycourseId(@RequestParam long courseId)
    {
        return requestService.getAllTeamShareRequestBycourseId(courseId);
    }

    /**
     * 根据课程id获得所有讨论课共享请求
     * @param courseId
     * @return
     */
    @GetMapping(value="/seminarshare")
    public ArrayList<SeminarShareVO> getAllSeminarShareRequestBycourseId(@RequestParam long courseId)
    {
        return requestService.getAllSeminarShareRequestBycourseId(courseId);
    }

    /**
     * 根据id获得对应的组队共享请求
     * @param teamShareId
     * @return
     */
    @GetMapping("/teamshare/{teamshareId}")
    public TeamShareVO getTeamShareRequestById(@PathVariable(value="teamshareId") long teamShareId)
    {
        return requestService.getTeamShareRequestById(teamShareId);
    }

    /**
     * 根据id获得对应的讨论课共享请求
     * @param seminarShareId
     * @return
     */
    @GetMapping("/seminarshare/{seminarshareId}")
    public SeminarShareVO getSeminarShareRequestById(@PathVariable(value="seminarshareId") long seminarShareId)
    {
        return requestService.getSeminarShareRequestById(seminarShareId);
    }

    /**
     * 根据教师id查看所有组队申请
     * @param teacherId
     * @return
     */
    @GetMapping("/request/teamvalid")
    public ArrayList<TeamValidVO> getAllTeamValidByTeacherId(@RequestParam(value="teacherId") Long teacherId)
    {
        return requestService.getAllTeamValidByTeacherId(teacherId);
    }

    @GetMapping("/request/teamvalid/{teamvalidId}")
    public TeamValidVO getTeamValidByTeamValidId(@PathVariable(value="teamvalidId")Long teamValidId)
    {
        return requestService.getTeamValidByTeamValidId(teamValidId);
    }

}
