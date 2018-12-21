package com.example.user.service;

import com.example.common.dao.StudentDao;
import com.example.common.dao.TeacherDao;
import com.example.common.dao.UserDao;
import com.example.common.entity.User;
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

    /**
     * 更新：id,role->密码修改
     * @param id
     * @param role
     * @param password
     * @return
     */
    public Long changeUserPassword(Long id,String role,String password){
        return userDao.updateUserPassword(id,role,password);
    }

    /**
     * 查询：id,role->User
     * @param id
     * @param role
     * @return
     */
    public User getUserById(Long id,String role){
        return userDao.getUserById(id,role);
    }

    /**
     * 更新：id,role->email
     * @param id
     * @param role
     * @param email
     * @return
     */
    public Long changeUserEmail(Long id,String role,String email){
        return userDao.updateUserEmail(id,role,email);
    }
}
