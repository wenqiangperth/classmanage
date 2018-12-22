package com.example.user.controller;

import com.example.common.entity.Team;
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
     *
     * @param team
     * @return小组ID
     */
    @PostMapping(value = "")
    public long addTeam(@RequestBody Team team) {
        System.out.println(team.toString());
        return teamService.addTeam(team);

    }

    /**
     * 根据teamID查询小组
     *
     * @param teamId
     * @return小组
     */
    @GetMapping(value = "/{teamId}")
    public Team getTeamById(@PathVariable("teamId") Long teamId) {
        return teamService.getTeamById(teamId);
    }

    /**
     * 根据ID删除小组
     *
     * @param teamId
     * @return
     */
    @DeleteMapping(value = "/{teamId}")
    public Long deleteTeamById(@PathVariable("teamId") Long teamId) {
        return teamService.deleteTeamById(teamId);
    }

    /**
     * 增加组员
     *
     * @param teamId
     * @param studentId
     * @return
     */
    @PutMapping(value = "/{teamId}/add")
    public Long addTeamMemberById(@PathVariable("teamId") Long teamId, @RequestBody Long studentId) {
        return teamService.addTeamMemberById(teamId, studentId);
    }

    /**
     * 移除组员
     *
     * @param teamId
     * @param studentId
     * @return
     */
    @PutMapping(value = "/{teamId}/remove")
    public Long removeTeamMember(@PathVariable("teamId") Long teamId, @RequestBody Long studentId) {
        return teamService.removeTeamMember(teamId, studentId);
    }


    /**
     * 同意学生特殊组队申请，设置team状态
     * @param teamId
     * @return
     */
    //@PutMapping(value = "/{teamId}/approve")
    //public Long approveTeam(@PathVariable("teamId") Long teamId){
    //   return teamService.approveTeam(teamId);
    //}
}
