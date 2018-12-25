package com.example.user.service;

import com.example.common.dao.RoundDao;
import com.example.common.entity.Round;
import com.example.common.entity.Seminar;
import com.example.common.entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author perth
 * @ClassName RoundService
 * @Description TODO
 * @Date 2018/12/17 19:06
 * @Version 1.0
 **/
@Service
public class RoundService {
    @Autowired
    private RoundDao roundDao;

    /**
     * 一轮的所有讨论课
     * @param id
     * @return
     */
    public ArrayList<Seminar>getAllSeminarsByRoundId(Long id){
        return roundDao.selectAllSeminarsByRoundId(id);
    }

    /**
     * 查询：id->round
     * @param id
     * @return
     */
    public Round getRoundById(Long id){
        return roundDao.selectRoundById(id);
    }

    /**
     * 更新：round
     * @param round
     * @return
     */
    public Long updateRound(Round round){
        return roundDao.updateRound(round);
    }

    /**
     * 查询：roundId->轮次所有成绩。在team里
     * @param roundId
     * @return
     */
    public ArrayList<Team>getRoundScore(Long roundId){
        return roundDao.selectRoundScore(roundId);
    }

    /**
     * 查询：轮次下的team成绩
     * @param roundId
     * @param teamId
     * @return
     */
    public Team getRoundTeamScore(Long roundId,Long teamId){
        return roundDao.selectRoundTeamScore(roundId,teamId);
    }

}
