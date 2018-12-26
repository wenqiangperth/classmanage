package com.example.user.controller;

import com.example.common.entity.Round;
import com.example.common.entity.Seminar;
import com.example.common.entity.Team;
import com.example.user.service.RoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * @author perth
 * @ClassName RoundController
 * @Description TODO
 * @Date 2018/12/17 19:06
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/round")
public class RoundController {
    @Autowired
    private RoundService roundService;


    /**
     * 获得一个轮次下所有讨论课
     * @param roundId
     * @return
     */
    @GetMapping(value = "/{roundId}/seminar")
    public ArrayList<Seminar>getAllSeminarByRoundId(@PathVariable("roundId")Long roundId){
        return roundService.getAllSeminarsByRoundId(roundId);
    }

    /**
     * 查询：roundID->Round(包括与班级的关系)
     * @param id
     * @return
     */
    @GetMapping(value = "/{roundId}")
    public Round getRoundById(@PathVariable(name = "roundId")Long id){
        return roundService.getRoundById(id);
    }

    /**
     * 更新：round和班级报名次数
     * @param roundId
     * @param round
     * @return
     */
    @PutMapping(value = "/{roundId}")
    public Long updateRound(@PathVariable(name = "roundId")Long roundId,@RequestBody Round round){
        round.setId(roundId);
        return roundService.updateRound(round);
    }

    /**
     * 查询:roundID->轮次成绩
     * @param roundId
     * @return
     */
    @GetMapping(value = "/{roundId}/roundscore")
    public ArrayList<Team>getRoundScore(@PathVariable(name = "roundId")Long roundId){
        return roundService.getRoundScore(roundId);
    }

    /**
     * 查询：roundId,teamID->score
     * @param roundId
     * @param teamId
     * @return
     */
    @GetMapping(value = "/{roundId}/team/{teamId}/roundScore")
    public Team getRoundTeamScore(@PathVariable(name = "roundId")Long roundId,@PathVariable(name = "teamId")Long teamId){
        return roundService.getRoundTeamScore(roundId,teamId);
    }



}
