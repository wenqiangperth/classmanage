package com.example.common.entity;

import lombok.Data;

import java.util.ArrayList;

/**
 * @author perth
 * @ClassName RoundTeamScoreVO
 * @Description TODO
 * @Date 2018/12/26 3:35
 * @Version 1.0
 **/
@Data
public class RoundTeamScoreVO {
    private Team team;
    private Score roundScore;
    private ArrayList<Score>seminarScores;
}
