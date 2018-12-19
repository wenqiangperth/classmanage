package com.example.user.controller;

import com.example.common.entity.Team;
import com.example.user.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    @PostMapping(value = "")
    public long addTeam(@RequestBody Team team){
        System.out.println(team.toString());
        return teamService.addTeam(team);

    }
}
