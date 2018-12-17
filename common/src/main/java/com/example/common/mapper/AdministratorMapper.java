package com.example.common.mapper;

import com.example.common.entity.Administrator;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


/**
 * @ClassName AdministratorMapper
 * @Description 处理管理员对象与数据库的交互
 * @author perth
 * @Date 2018/12/15 21:50
 * @Version 1.0
 **/
@Mapper
@Repository
public interface AdministratorMapper {

    /**
     * 通过account得到Administrator对象
     * @param account
     * @return
     */
    @Select("select * from admin where account=#{account}")
    public Administrator selectAdministratorByAccount(@Param(value = "account") String account);
}
