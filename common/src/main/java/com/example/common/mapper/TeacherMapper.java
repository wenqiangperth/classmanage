package com.example.common.mapper;

import com.example.common.entity.Teacher;
import com.example.common.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @ClassName TeacherMapper
 * @Description
 * @Author perth
 * @Date 2018/12/17 0017 下午 7:59
 * @Version 1.0
 **/

@Mapper
@Repository
public interface TeacherMapper {

    /**
     * 查询老师总数
     * @return
     */
    @Select("select count(id) from teacher")
    public Long selectTeacherNum();

    /**
     * 查询：所有老师
     * @return
     */
    @Select("select id,account,is_active,teacher_name,email from teacher")
    @ResultMap(value = "teacherMap")
    public ArrayList<Teacher>selectAllTeacher();

    /**
     * 查询：根据账号获取teacher->User
     * @param account
     * @return
     */
    @Select("select * from teacher where account=#{account}")
    @ResultMap(value = "teacherMap")
    public Teacher selectTeahcerByAccount(String account);

    /**
     * 查询：ID->teacher
     * @param teacherId
     * @return
     */
    @Select("select * from teacher where id=#{teacherId}")
    @Results(id = "teacherMap",value = {
            @Result(property = "account",column = "account"),
            @Result(property = "password",column = "password"),
            @Result(property = "teacherName",column = "teacher_name"),
            @Result(property = "isActive",column = "is_active"),
            @Result(property = "email",column = "email")
    })
    public Teacher selectTeacherById(@Param(value="teacherId") long teacherId);

    /***
     * 查询：name->teacher
     * @param teacherName
     * @return
     */
    @Select("select * from teacher where teacher_name=#{teacherName}")
    @ResultMap(value = "teacherMap")
    public ArrayList<Teacher>selectTeacherByName(@Param("teacherName")String teacherName);

    /**
     * 更新：password
     * @param password
     * @param oldPassword
     * @param id
     * @return
     */
    @Update("update teacher set password=#{password} where id=#{id} and password=#{oldPassword}")
    public Long updateTeacherPassword(@Param("password")String password,@Param("oldPassword")String oldPassword,@Param("id") Long id);

    /**
     * 修改老师密码
     * @param password
     * @param id
     * @return
     */
    @Update("update teacher set password=#{password} where id=#{id}")
    public Long updateTeacherPasswordByAdmin(@Param("password")String password,@Param("id")Long id);

    /**
     * 获得用户信息
     * @param id
     * @return
     */
    @Select("select * from teacher where id=#{id}")
    @Results(id="UserTeacherMap",value = {
            @Result(property = "account",column = "account"),
            @Result(property = "password",column = "password"),
            @Result(property = "isActived",column = "is_active"),
            @Result(property = "name",column ="teacher_name" ),
            @Result(property = "email",column = "email")
    })
    public User getTeacherInfo(@Param("id") Long id);

    /**
     * 更新：email
     * @param id
     * @param email
     * @return
     */
    @Update("update teacher set email=#{email} where id=#{id}")
    public Long updateTeacherEmail(@Param("id")Long id,@Param("email")String email);

    /**
     * 插入：创建teacher
     * @param teacher
     * @return
     */
    @Insert("insert teacher set account=#{account},password=#{password},teacher_name=#{teacherName},email=#{email},is_active=#{isActive}")
    @Options(useGeneratedKeys = true,keyColumn = "id")
    public Long insertTeacher(Teacher teacher);

    /**
     * 更新：account,name,email
     * @param teacher
     * @return
     */
    @Update("update teacher set account=#{account},teacher_name=#{teacherName},email=#{email} where id=#{id}")
    public Long updateTeacherInformation(Teacher teacher);

    /**
     * 删除：id->teacher
     * @param id
     * @return
     */
    @Delete("delete from teacher where id=#{id}")
    public Long deleteTeacherById(@Param("id")Long id);

    /**
     * 更新：teacher激活
     * @param teacher
     * @return
     */
    @Update("update teacher set password=#{password},is_active=#{isActive} where id=#{id}")
    public Long updateTeacherAcive(Teacher teacher);

}
