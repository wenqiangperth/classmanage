package com.example.common.mapper;

import com.example.common.entity.KlassRound;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoundMapperTest {
    @Autowired
    private RoundMapper roundMapper;

    @Test
    public void selectRoundById() {
    }

    @Test
    public void selectKlassRoundByRoundId() {
        ArrayList<KlassRound>klassRounds=roundMapper.selectKlassRoundByRoundId(1L);
        System.out.println(klassRounds);
    }

    @Test
    public void deleteRoundByCourseId() {
    }
}