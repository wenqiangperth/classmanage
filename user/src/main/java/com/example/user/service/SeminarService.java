package com.example.user.service;

import com.example.common.dao.SeminarDao;
import com.example.common.entity.Klass;
import com.example.common.entity.Seminar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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
    private SeminarDao seminarDao;

    /**
     * 创建讨论课
     * @param seminar
     * @return
     */
    public long createSeminar(Seminar seminar){
        return seminarDao.addSeminar(seminar);
    }

    /**
     * 查询：seminarID->klass
     * @param seminarId
     * @return
     */
    public ArrayList<Klass>getKlassBySeminarId(Long seminarId){
        return seminarDao.getKlassBySeminarId(seminarId);
    }

}
