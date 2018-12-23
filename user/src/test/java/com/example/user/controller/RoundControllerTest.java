package com.example.user.controller;

import com.example.common.entity.KlassRound;
import com.example.common.entity.Round;
import com.example.common.entity.Team;
import com.example.user.service.RoundService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RoundControllerTest {
    @Autowired
    private RoundService roundService;

    @Test
    public void getAllSeminarByRoundId() {
    }

//    @Test
//    public void getRoundScore() {
//        ArrayList<Team>teams=roundService.getRoundScore(1L);
//        System.out.println(teams);
//    }

    @Test
    public void getTeamScore(){
        Team team=roundService.getRoundTeamScore(1L,2L);
        System.out.println(team);
    }

    @Test
    public void updateRound() {
        Round round=new Round();
        round.setId(1L);
        round.setPresentationScoreMethod(2);
        round.setQuestionScoreMethod(2);
        round.setReportScoreMethod(2);
        ArrayList<KlassRound>klassRounds=new ArrayList<>();
        KlassRound klassRound=new KlassRound();
        klassRound.setKlassId(1L);
        klassRound.setRoundId(1L);
        klassRound.setEnrollNumber(1);
        klassRounds.add(klassRound);
        round.setKlassRounds(klassRounds);
        Long i=roundService.updateRound(round);
        System.out.println(i);
    }
}