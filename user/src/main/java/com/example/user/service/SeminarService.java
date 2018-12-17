package com.example.user.service;

import com.example.common.dao.SeminarDao;
import com.example.common.entity.Seminar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author perth
 * @ClassName SeminarService
 * @Description TODO
 * @Date 2018/12/17 19:07
 * @Version 1.0
 **/

@Service
public class SeminarService {
    @Autowired
    SeminarDao seminarDao;

    public long createSeminar(Seminar seminar){
        return seminarDao.insertSeminar(seminar.getCourseId(),seminar.getRoundId(),seminar.getSeminarName(),seminar.getIntroduction(),seminar.getMaxTeam(),seminar.getIsVisible(),seminar.getSeminarSerial(),seminar.getEnrollStartTime(),seminar.getEnrollEndTime());
    }
}
