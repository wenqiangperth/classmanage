package com.example.common.dao;

import com.example.common.entity.Seminar;
import com.example.common.mapper.RoundMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author perth
 * @ClassName RoundDao
 * @Description TODO
 * @Date 2018/12/20 13:59
 * @Version 1.0
 **/
@Repository
public class RoundDao {
    @Autowired
    private RoundMapper roundMapper;

    /**
     * 一轮的所有讨论课
     * @param roundId
     * @return
     */
    public ArrayList<Seminar>selectAllSeminarsByRoundId(Long roundId){
        return roundMapper.selectAllSeminarsByRoundId(roundId);
    }
}
