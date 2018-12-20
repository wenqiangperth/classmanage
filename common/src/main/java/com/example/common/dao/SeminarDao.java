package com.example.common.dao;

import com.example.common.entity.Seminar;
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

    public long addSeminar(Seminar seminar){
        Long i=seminarMapper.insertSeminar(seminar);
        if(i<=0){return i;}
        return seminar.getId();
    }
}
