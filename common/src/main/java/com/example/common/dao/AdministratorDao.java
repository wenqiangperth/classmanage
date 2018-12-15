package com.example.common.dao;

import com.example.common.entity.Administrator;
import com.example.common.mapper.AdministratorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @ClassName AdministratorDao
 * @Description
 * @Author perth
 * @Date 2018/12/15 17:21
 * @Version 1.0
 **/

@Repository
public class AdministratorDao {
    @Autowired
    private AdministratorMapper administratorMapper;

    public Administrator getAdministratorByAccount(String account){
        return administratorMapper.selectAdministratorByAccount(account);
    }

}
