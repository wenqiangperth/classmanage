package com.example.common.dao;

import com.example.common.mapper.SeminarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;

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

    public long insertSeminar(Long courseId, Long roundId, String seminarName,String introduction,int maxTeam,boolean isVisible,int seminarSerial,Date enrollStartTime,Date enrollEndTime){
        return seminarMapper.insertSeminar(courseId,roundId,seminarName,introduction,maxTeam,isVisible,seminarSerial,enrollStartTime,enrollEndTime);
    }
}
