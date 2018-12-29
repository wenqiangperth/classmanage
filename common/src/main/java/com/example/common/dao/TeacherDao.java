package com.example.common.dao;

import com.example.common.entity.Student;
import com.example.common.entity.Teacher;
import com.example.common.mapper.TeacherMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author perth
 * @ClassName TeacherDao
 * @Description TODO
 * @Date 2018/12/20 18:02
 * @Version 1.0
 **/
@Repository
public class TeacherDao {
    @Autowired
    private TeacherMapper teacherMapper;


    public Long seleceTeacherNum(){
        return teacherMapper.selectTeacherNum();
    }

    /**
     * 插入：创建teacher
     * @param teacher
     * @return
     */
    public Long insertTeacher(Teacher teacher){
        return teacherMapper.insertTeacher(teacher);
    }

    /**
     * 查询：分页获取teacher
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ArrayList<Teacher>selectAllTeacher(int pageNum,int pageSize){
        PageHelper.startPage(pageNum, pageSize).setOrderBy("id asc");
        ArrayList<Teacher>teachers=new ArrayList<>(teacherMapper.selectAllTeacher());
        return teachers;
    }

    /**
     * 查询：account or name->teachers
     * @param accountOrName
     * @return
     */
    public ArrayList<Teacher>selectTeacherByAccountOrName(String accountOrName){
        ArrayList<Teacher>teachers=new ArrayList<>();
        Teacher teacher=teacherMapper.selectTeahcerByAccount(accountOrName);
        if(teacher!=null){
            teachers.add(teacher);
            return teachers;
        }
        teachers=teacherMapper.selectTeacherByName(accountOrName);
        return teachers;
    }

    /**
     * 更新：account，name,email
     * @param teacher
     * @return
     */
    public Long updateTeacherInformation(Teacher teacher){
        return teacherMapper.updateTeacherInformation(teacher);
    }

    /**
     * 更新：password
     * @param id
     * @param password
     * @return
     */
    public Long updateTeacherPassword(Long id,String password){
        return teacherMapper.updateTeacherPasswordByAdmin(password,id);
    }

    /**
     * 删除：teacher 根据ID
     * @param id
     * @return
     */
    public Long deleteTeacherById(Long id){
        return teacherMapper.deleteTeacherById(id);
    }

    /**
     * 更新：teacher激活
     * @param teacher
     * @return
     */
    public Long updateTeacherActive(Teacher teacher){
        return teacherMapper.updateTeacherAcive(teacher);
    }

}
