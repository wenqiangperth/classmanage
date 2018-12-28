package com.example.user.service;

import com.example.common.config.MailConfig;
import com.example.common.config.SendMailConfig;
import com.example.common.dao.UserDao;
import com.example.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

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
     * @param user
     * @param role
     * @return
     */
    public Long changeUserPassword(User user,String role){
        return userDao.updateUserPassword(user,role);
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

    public long getUserPassword(String account) throws MessagingException
    {
        SendMailConfig mailConfig=new SendMailConfig();
        User user=userDao.getUserPassWord(account);
        if(user!=null) {
            String title = "温馨提醒";
            String text = "您当前使用的" + user.getAccount() + "用户初始PS为:" + user.getPassword() + "。请及时修改！";
            mailConfig.sendEmail(title,text,user.getEmail());
        }
        return user.getId();
    }
}
