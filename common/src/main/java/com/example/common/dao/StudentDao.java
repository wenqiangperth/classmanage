package com.example.common.dao;

import com.example.common.entity.Course;
import com.example.common.entity.Student;
import com.example.common.mapper.StudentMapper;
import com.github.pagehelper.PageHelper;
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

    /**
     * 查询：分页获取所有学生
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ArrayList<Student>getAllStudent(int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize).setOrderBy("id asc");
        ArrayList<Student>stundets=new ArrayList<>(studentMapper.selectAllStudent());
        return stundets;
    }
}
