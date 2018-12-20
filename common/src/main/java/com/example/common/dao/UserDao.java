package com.example.common.dao;

import com.example.common.entity.Student;
import com.example.common.entity.Teacher;
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

    /**
     * 通过账号获得用户
     * @param account
     * @return
     */
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

    /**
     * 更新用户的密码
     * @param id
     * @param role
     * @param password
     * @return
     */
    public Long updateUserPassword(Long id,String role,String password){
        if(role.equals("teacher")){
            return teacherMapper.updateTeahcerPassword(password,id);
        }else if(role.equals("student")){
            return studentMapper.updateStundentPassword(password,id);
        }
        return 0L;
    }

    /**
     * 查询：id->user信息
     * @param id
     * @param role
     * @return
     */
    public User getUserById(Long id,String role){
        User user=new User();
        if(role.equals("student")){
            Student student=studentMapper.selectStudentById(id);
            user.setUserByStudent(student);
        }else if(role.equals("student")){
            Teacher teacher=teacherMapper.selectTeacherById(id);
            user.setUserByTeacher(teacher);
        }
        user.setPassword(null);
        return user;
    }
}
