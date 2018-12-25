package com.example.common.dao;

import com.example.common.entity.*;
import com.example.common.mapper.RoundMapper;
import com.example.common.mapper.ScoreMapper;
import com.example.common.mapper.SeminarMapper;
import com.example.common.mapper.TeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * @author perth
 * @ClassName RoundDao
 * @Description TODO
 * @Date 2018/12/20 13:59
 * @Version 1.0
 **/
@Repository
public class RoundDao {
    @Autowired
    private RoundMapper roundMapper;
    @Autowired
    private SeminarMapper seminarMapper;
    @Autowired
    private TeamMapper teamMapper;
    @Autowired
    private ScoreMapper scoreMapper;

    /**
     * 一轮的所有讨论课
     * @param roundId
     * @return
     */
    public ArrayList<Seminar>selectAllSeminarsByRoundId(Long roundId){
        return seminarMapper.selectAllSeminarsByRoundId(roundId);
    }

    /**
     * 查询：id->round
     * @param id
     * @return
     */
    public Round selectRoundById(Long id){
        Round round=roundMapper.selectRoundById(id);
        ArrayList<KlassRound>klassRounds=roundMapper.selectKlassRoundByRoundId(id);
        round.setKlassRounds(klassRounds);
        return round;
    }

    /**
     * 更新：round计分规则，班级报名次数
     * @param round
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Long updateRound(Round round){
        Long i=roundMapper.updateRoundById(round);
        if(i<0){
            return i;
        }
        ArrayList<KlassRound>klassRounds=round.getKlassRounds();
        for (KlassRound klassRound:klassRounds
             ) {
            roundMapper.updateKlassRound(klassRound);
        }
        return i;
    }

    /**
     * 查询：轮次一轮所有成绩
     * @param roundId
     * @return
     */
    public ArrayList<Team> selectRoundScore(Long roundId){
        ArrayList<Team> teams=teamMapper.selectTeamByRoundId(roundId);
        if(teams!=null){
            for (Team team:teams
                 ) {
                Score score=scoreMapper.selectRoundScore(roundId,team.getId());
                score.setSeminarOrRoundId(roundId);
                team.setScore(score);
            }
            return teams;
        }else {
            return null;
        }
    }

    /**
     * 查询：team在某轮次的score
     * @param roundId
     * @param teamId
     * @return
     */
    public Team selectRoundTeamScore(Long roundId,Long teamId){
        Team team=teamMapper.selectTeamById(teamId);
        Score score=scoreMapper.selectRoundScore(roundId,teamId);
        team.setScore(score);
        return team;
    }

    public Long deleteAllRoundByCourseId(Long courseId)
    {
        return roundMapper.deleteAllRoundByCourseId(courseId);
    }

    public ArrayList<Round> selectAllRoundByCourseId(Long courseId)
    {
        return roundMapper.getAllRoundByCourseId(courseId);
    }
}
