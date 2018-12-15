package com.example.common.mapper;

import com.example.common.entity.Administrator;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

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
