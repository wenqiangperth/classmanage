package com.example.admin.service;

import com.example.common.dao.AdministratorDao;
import com.example.common.entity.Administrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName AdminService
 * @Description 管理员端的业务逻辑类
 * @Author perth
 * @Date 2018/12/15 19:11
 * @Version 1.0
 **/

@Service
public class AdminService {

    @Autowired
    private AdministratorDao administratorDao;

    public String adminstratorLogin(Administrator administrator){
        Administrator admin=administratorDao.getAdministratorByAccount(administrator.getAccount());
        try {
            if (administrator.getPassword().equals(admin.getPassword())) {
                return "登陆成功";
            } else {
                return "登陆失败";
            }
        }catch (Exception e){
            return "登陆失败";
        }
    }

}
