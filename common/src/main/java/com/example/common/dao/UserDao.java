package com.example.common.dao;

import com.example.common.entity.Administrator;
import com.example.common.entity.Student;
import com.example.common.entity.Teacher;
import com.example.common.entity.User;
import com.example.common.mapper.AdministratorMapper;
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
    @Autowired
    private AdministratorMapper administratorMapper;

    private final static String ROLE_STUDENT="ROLE_STUDENT";
    private final static String ROLE_TEACHER="ROLE_TEACHER";

    /**
     * 通过账号获得用户
     * @param account
     * @return
     */
    public User getUserByAccount(String account){
        User user=new User();
        Administrator administrator=administratorMapper.selectAdministratorByAccount(account);
        if(administrator!=null){
            user.setUserByAdministrator(administrator);
            return user;
        }
        Teacher teacher=teacherMapper.selectTeahcerByAccount(account);
        if(teacher!=null){
            user.setUserByTeacher(teacher);
            return user;
        }
        Student student=studentMapper.selectStudentByAccount(account);
        if(student!=null){
            user.setUserByStudent(student);
            return user;
        }
        return null;
    }

    /**
     * 更新用户的密码
     * @param user
     * @param role
     * @return
     */
    public Long updateUserPassword(User user,String role){
        if((ROLE_TEACHER.equals(role))){
            return teacherMapper.updateTeacherPassword(user.getPassword(),user.getOldPassword(),user.getId());
        }else if((ROLE_STUDENT.equals(role))){
            return studentMapper.updateStundentPassword(user.getPassword(),user.getId());
        }
        return 0L;
    }

    /**
     * 更新用户邮箱
     * @param id
     * @param role
     * @param email
     * @return
     */
    public Long updateUserEmail(Long id,String role,String email){
        if((ROLE_TEACHER.equals(role))){
            return teacherMapper.updateTeacherEmail(id,email);
        }else if((ROLE_STUDENT.equals(role))){
            return studentMapper.updateStudentEmail(id,email);
        }
        return 0L;
    }

    public User getUserPassWord(String account){
        User user=new User();
        Teacher teacher=teacherMapper.selectTeahcerByAccount(account);
        if(teacher!=null)
        {
            user.setId(teacher.getId());
            user.setAccount(teacher.getAccount());
            user.setPassword(teacher.getPassword());
            user.setEmail(teacher.getEmail());

        }
        else
        {
            Student student=studentMapper.selectStudentByAccount(account);
            user.setId(student.getId());
            user.setAccount(student.getAccount());
            user.setPassword(student.getPassword());
            user.setEmail(student.getEmail());
        }
        return user;


    }

    /**
     * 查询：id->user信息
     * @param id
     * @param role
     * @return
     */
    public User getUserById(Long id,String role){
        User user=new User();
        if((ROLE_STUDENT.equals(role))){
            Student student=studentMapper.selectStudentById(id);
            user.setUserByStudent(student);
        }else if((ROLE_TEACHER.equals(role))){
            Teacher teacher=teacherMapper.selectTeacherById(id);
            user.setUserByTeacher(teacher);
        }
        user.setPassword(null);
        return user;
    }
}
