package com.example.common.dao;

import com.example.common.entity.Klass;
import com.example.common.entity.Seminar;
import com.example.common.mapper.KlassMapper;
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

    /**
     * 创建:讨论课，klass_seminar关系
     * @param seminar
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public long addSeminar(Seminar seminar){
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
}
