package com.example.user.controller;

import com.example.common.entity.Student;
import com.example.common.entity.Team;
import com.example.common.entity.TeamValidApplication;
import com.example.user.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author perth
 * @ClassName TeamController
 * @Description 处理team的请求
 * @Date 2018/12/17 19:08
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/team")
public class TeamController {
    @Autowired
    private TeamService teamService;


    /**
     * 创建小组
     * @param team
     * @return小组ID
     */
    @PostMapping(value = "")
    public long addTeam(@RequestBody Team team){
        return teamService.addTeam(team);
    }

    /**
     * 根据teamID查询小组
     * @param teamId
     * @return小组
     */
    @GetMapping(value = "/{teamId}")
    public Team getTeamById(@PathVariable("teamId")Long teamId,@RequestParam("courseId")Long courseId){
        return teamService.getTeamById(teamId,courseId);
    }

    /**
     * 修改team的name
     * @param teamId
     * @param team
     * @return
     */
    @PutMapping(value = "/{teamId}")
    public Long updateTeamName(@PathVariable(name = "teamId")long teamId,@RequestBody Team team){
        team.setId(teamId);
        return teamService.updateTeamName(team);
    }

    /**
     * 根据ID删除小组
     * @param teamId
     * @return
     */
    @DeleteMapping(value = "/{teamId}")
    public Long deleteTeamById(@PathVariable("teamId")Long teamId){
        return teamService.deleteTeamById(teamId);
    }

    /**
     * 增加组员
     * @param teamId
     * @param student
     * @return
     */
    @PutMapping(value = "/{teamId}/add")
    public Long addTeamMemberById(@PathVariable("teamId")Long teamId ,@RequestBody Student student){
        return teamService.addTeamMemberById(teamId,student.getId());
    }

    /**
     * 移除组员
     * @param teamId
     * @param student
     * @return
     */
    @PutMapping(value = "/{teamId}/remove")
    public Long removeTeamMember(@PathVariable("teamId")Long teamId,@RequestBody Student student){
        return teamService.removeTeamMember(teamId,student.getId());
    }

    /**
     * 创建组队申请
     * @param teamId
     * @param teamValidApplication
     * @return
     */
    @PostMapping(value = "/{teamId}/teamvalidrequest")
    public Long createTeamValidRequest(@PathVariable(name = "teamId")Long teamId, @RequestBody TeamValidApplication teamValidApplication){
        teamValidApplication.setTeamId(teamId);
        System.out.println(teamValidApplication.toString());
        return teamService.createTeamValisApplication(teamValidApplication);
    }

    /**
     * 同意学生特殊组队申请，设置team状态
     * @param teamId
     * @return
     */
    @PutMapping(value = "/{teamId}/approve")
    public Long approveTeam(@PathVariable("teamId") Long teamId){
        return teamService.approveTeam(teamId);
    }
}