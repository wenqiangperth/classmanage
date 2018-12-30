package com.example.common.entity;

import lombok.Data;

/**
 * @author perth
 * @ClassName Score
 * @Description TODO
 * @Date 2018/12/22 10:59
 * @Version 1.0
 **/
@Data
public class Score {
    private Long seminarOrRoundId;
    private Long teamId;
    private double totalScore;
    private double presentationScore;
    private double questionScore;
    private double reportScore;
    private String seminarName;

    public void setSeminarName(String seminarName){
        this.seminarName=seminarName;
    }
    public double getPresentationScore(){
        return this.presentationScore;
    }
}
