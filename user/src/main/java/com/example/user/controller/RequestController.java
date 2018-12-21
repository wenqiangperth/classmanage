package com.example.user.controller;

import com.example.common.entity.SeminarShareVO;
import com.example.common.entity.TeamShareVO;
import com.example.user.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping(value="teamshare")
    public ArrayList<TeamShareVO> getAllTeamShareRequestBycourseId(@RequestParam long courseId)
    {
        return requestService.getAllTeamShareRequestBycourseId(courseId);
    }

    @GetMapping(value="seminarshare")
    public ArrayList<SeminarShareVO> getAllSeminarShareRequestBycourseId(@RequestParam long courseId)
    {
        return requestService.getAllSeminarShareRequestBycourseId(courseId);
    }


}
