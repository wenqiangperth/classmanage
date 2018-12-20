package com.example.user.service;

import com.example.common.dao.RoundDao;
import com.example.common.entity.Seminar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author perth
 * @ClassName RoundService
 * @Description TODO
 * @Date 2018/12/17 19:06
 * @Version 1.0
 **/
@Service
public class RoundService {
    @Autowired
    private RoundDao roundDao;

    /**
     * 一轮的所有讨论课
     * @param id
     * @return
     */
    public ArrayList<Seminar>getAllSeminarsByRoundId(Long id){
        return roundDao.selectAllSeminarsByRoundId(id);
    }
}
