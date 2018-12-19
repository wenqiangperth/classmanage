package com.example.common.dao;

import com.example.common.entity.Course;
import com.example.common.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author perth
 * @ClassName StundentDao
 * @Description 学生事务
 * @Date 2018/12/19 20:28
 * @Version 1.0
 **/
@Repository
public class StudentDao {
    @Autowired
    private StudentMapper studentMapper;

    /**
     * 查询一个学生的所有课程
     * @param id
     * @return
     */
    public ArrayList<Course> getAllCoursesByStundetId(Long id){
        return studentMapper.getAllCoursesByStundetId(id);
    }
}
