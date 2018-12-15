package com.example.common.dao;

import com.example.common.entity.Administrator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdministratorDaoTest {
    @Autowired
    private AdministratorDao administratorDao;

    @Test
    public void test(){
        Administrator administrator=administratorDao.getAdministratorByAccount("12");
        System.out.println(administrator.getAccount()+"  "+administrator.getPassword());
    }
}