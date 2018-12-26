package com.example.common.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.ws.Action;

import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConflictCourseStrategyMapperTest {
    @Autowired
    private ConflictCourseStrategyMapper conflictCourseStrategyMapper;

    @Test
    public void selectConflictCourseStrategyById() {
        ArrayList<Long>a=conflictCourseStrategyMapper.selectConflictCourseStrategyById(1L);
        System.out.println(a);
    }
}