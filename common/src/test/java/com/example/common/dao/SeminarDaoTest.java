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
        seminar.setCourseId(5L);
        seminar.setSeminarName("加油");
        seminar.setIntroduction("best");
        seminar.setMaxTeam(5);
        seminar.setIsVisible(1);
        Long i=seminarDao.addSeminar(seminar);
        System.out.println(i);
    }
}