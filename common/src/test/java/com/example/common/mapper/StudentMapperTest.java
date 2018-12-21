package com.example.common.mapper;

import com.example.common.entity.Student;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentMapperTest {
    @Autowired
    private StudentMapper studentMapper;

    @Test
    public void selectAllStudent() {
        PageHelper.startPage(2, 1).setOrderBy("id asc");
//        final PageInfo<Student> userPageInfo = new PageInfo<>(studentMapper.selectAllStudent());
//        ArrayList<Student>stundets=(ArrayList)userPageInfo.getList();
//        System.out.println();
        ArrayList<Student>stundets=new ArrayList<>(studentMapper.selectAllStudent());
        System.out.println(stundets);
    }

    @Test
    public void selectStudentByAccount() {
    }

    @Test
    public void selectStudentById() {
    }

    @Test
    public void getAllCoursesByStundetId() {
    }

    @Test
    public void updateStundentPassword() {
    }

    @Test
    public void updateStudnetEmail() {
    }
}