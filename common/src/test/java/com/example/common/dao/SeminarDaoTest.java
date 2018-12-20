package com.example.common.dao;

import com.example.common.entity.Seminar;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SeminarDaoTest {
    @Autowired
    private SeminarDao seminarDao;

    @Test
    public void addSeminar() {
        Seminar seminar=new Seminar();
        seminar.setCourseId(3);
        seminar.setRoundId(2);
        seminar.setSeminarName("测试里");
        seminar.setIntroduction("dafsfad");
        seminar.setMaxTeam(5);
        seminar.setIsVisible(1);
        seminarDao.addSeminar(seminar);
    }
}