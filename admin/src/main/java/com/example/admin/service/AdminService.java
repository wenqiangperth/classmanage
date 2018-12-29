package com.example.admin.service;

import com.example.common.dao.AdministratorDao;
import com.example.common.dao.StudentDao;
import com.example.common.dao.TeacherDao;
import com.example.common.entity.Administrator;
import com.example.common.entity.Student;
import com.example.common.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private TeacherDao teacherDao;

    public Long selectTeacherNum(){
        return teacherDao.seleceTeacherNum();
    }

    /**
     * 查询：分页获取所有学生
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ArrayList<Student>getAllStudent(int pageNum,int pageSize){
        return studentDao.getAllStudent(pageNum,pageSize);
    }

    /**
     * 查询：account or name ->student
     * @param accountOrName
     * @return
     */
    public ArrayList<Student>getStudentByAccountOrName(String accountOrName){
        return  studentDao.getStudentByAccountOrName(accountOrName);
    }

    /**
     * 更新:学生信息
     * @param student
     * @return
     */
    public Long updateStudentInformation(Student student){
        return studentDao.updateStudentInformation(student);
    }

    /**
     * 更新：学生密码
     * @param id
     * @param password
     * @return
     */
    public Long updateStudentPassword(Long id ,String password){
        return studentDao.updateStudentPassword(id,password);
    }

    /**
     * 删除：学生
     * @param id
     * @return
     */
    public Long deleteStudentById(Long id){
        return studentDao.deleteStudentById(id);
    }






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


    /**
     * 插入：增加teacher
     * @param teacher
     * @return
     */
    public Teacher addTeacher(Teacher teacher){
        teacher.setIsActive(0);
        Long i=teacherDao.insertTeacher(teacher);
        if(i<=0){
            return null;
        }
        return teacher;
    }

    /**
     * 查询:分页获取所有teacher
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ArrayList<Teacher>getAllTeacher(int pageNum,int pageSize){
        return teacherDao.selectAllTeacher(pageNum,pageSize);
    }

    /**
     * name or account->teacher
     * @param accountOrName
     * @return
     */
    public ArrayList<Teacher>getTeacherByAccountOrName(String accountOrName){
        return teacherDao.selectTeacherByAccountOrName(accountOrName);
    }

    /**
     * 更新：name,account ,email
     * @param teacher
     * @return
     */
    public Long updateTeacherInformation(Teacher teacher){
        return teacherDao.updateTeacherInformation(teacher);
    }

    /**
     * 更新：password
     * @param id
     * @param password
     * @return
     */
    public Long updateTeacherPassword(Long id,String password){
        return teacherDao.updateTeacherPassword(id,password);
    }

    /**
     * 删除teacher
     * @param id
     * @return
     */
    public Long deleteTeacherById(Long id){
        return teacherDao.deleteTeacherById(id);
    }

}
