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
     * @param team
     * @return小组ID
     */
    @PostMapping(value = "")
    public long addTeam(@RequestBody Team team){
        System.out.println(team.toString());
        return teamService.addTeam(team);

    }

    /**
     * 根据teamID查询小组
     * @param teamId
     * @return小组
     */
    @GetMapping(value = "/{teamId}")
    public Team getTeamById(@PathVariable("teamId")Long teamId){
        return teamService.getTeamById(teamId);
    }

    @DeleteMapping(value = "/{teamId}")
    public Long deleteTeam(@PathVariable("teamId")Long teamId){
        return teamService.deleteTeamById(teamId);
    }
}
