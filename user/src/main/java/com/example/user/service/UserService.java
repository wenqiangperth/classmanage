package com.example.user.service;

import com.example.common.dao.StudentDao;
import com.example.common.dao.TeacherDao;
import com.example.common.dao.UserDao;
import com.example.common.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author perth
 * @ClassName UserService
 * @Description TODO
 * @Date 2018/12/17 19:04
 * @Version 1.0
 **/
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public Long changeUserPassword(Long id,String role,String password){
        return userDao.updateUserPassword(id,role,password);
    }

}
