package com.example.common.dao;

import com.example.common.entity.User;
import com.example.common.mapper.StudentMapper;
import com.example.common.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author perth
 * @ClassName UserDao
 * @Description TODO
 * @Date 2018/12/20 18:08
 * @Version 1.0
 **/
@Repository
public class UserDao {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private TeacherMapper teacherMapper;

    public User getUserByAccount(String account){
        User user=teacherMapper.selectTeahcerByAccount(account);
        if(user!=null){
            user.setRole("teacher");
            return user;
        }
        user=studentMapper.selectStudentByAccount(account);
        if(user!=null){
            user.setRole("student");
            return user;
        }
        return null;
    }
}
