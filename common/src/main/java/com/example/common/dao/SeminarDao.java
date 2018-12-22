package com.example.common.dao;

import com.example.common.entity.Klass;
import com.example.common.entity.KlassSeminar;
import com.example.common.entity.Round;
import com.example.common.entity.Seminar;
import com.example.common.mapper.KlassMapper;
import com.example.common.mapper.RoundMapper;
import com.example.common.mapper.SeminarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;

/**
 * @author perth
 * @ClassName SeminarDao
 * @Description TODO
 * @Date 2018/12/17 23:03
 * @Version 1.0
 **/
@Repository
public class SeminarDao {
    @Autowired
    private SeminarMapper seminarMapper;

    @Autowired
    private KlassMapper klassMapper;

    @Autowired
    private RoundMapper roundMapper;

    /**
     * 创建:讨论课，klass_seminar关系
     * @param seminar
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public long addSeminar(Seminar seminar){
        if(seminar.getRoundId()==null){
            ArrayList<Round>rounds=roundMapper.getAllRoundByCourseId(seminar.getCourseId());
            int serial=0;
            for (Round round:rounds
            ) {
                if(serial<round.getRoundSerial()){
                    serial=round.getRoundSerial();
                }
            }
            Round round=new Round();
            round.setCourseId(seminar.getCourseId());
            round.setRoundSerial(serial+1);
            int method=1;
            round.setReportScoreMethod(method);
            round.setQuestionScoreMethod(method);
            round.setPresentationScoreMethod(method);
            roundMapper.insertRound(round);
            seminar.setRoundId(round.getId());
        }
        Long i=seminarMapper.insertSeminar(seminar);
        if(i<=0){return i;}
        int status=0;
        ArrayList<Klass>klasses=klassMapper.getAllClassByCourseId(seminar.getCourseId());
        for (Klass klass:klasses
             ) {
            seminarMapper.insertKlassSeminar(klass.getId(),seminar.getId(),status);
        }

        return seminar.getId();
    }

    /**
     * 查询：seminarId->klass 内含klass_seminar
     * @param seminarId
     * @return
     */
    public ArrayList<Klass> getKlassBySeminarId(Long seminarId){
        ArrayList<Klass>klasses=klassMapper.getAllKlassBySeminarId(seminarId);
        if(klasses!=null){
            for (Klass klass:klasses
                 ) {
                KlassSeminar klassSeminar=klassMapper.getKlassSeminarByKlassAndSeminar(klass.getId(),seminarId);
                klass.setKlassSeminar(klassSeminar);
            }
        }
        return klasses;
    }
}
