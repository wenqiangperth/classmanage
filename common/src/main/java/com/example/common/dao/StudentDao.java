package com.example.common.dao;

import com.example.common.entity.Course;
import com.example.common.entity.Student;
import com.example.common.mapper.StudentMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * @author perth
 * @ClassName StundentDao
 * @Description 学生事务
 * @Date 2018/12/19 20:28
 * @Version 1.0
 **/
@Repository
public class StudentDao {
    @Autowired
    private StudentMapper studentMapper;

    /**
     * 查询一个学生的所有课程
     * @param id
     * @return
     */
    public ArrayList<Course> getAllCoursesByStundetId(Long id){
        return studentMapper.getAllCoursesByStundetId(id);
    }

    /**
     * 查询：分页获取所有学生
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ArrayList<Student>getAllStudent(int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize).setOrderBy("id asc");
        ArrayList<Student>stundets=new ArrayList<>(studentMapper.selectAllStudent());
        return stundets;
    }

    /**
     * 查询：account or name->students
     * @param accountOrName
     * @return
     */
    public ArrayList<Student>getStudentByAccountOrName(String accountOrName){
        ArrayList<Student> students=new ArrayList<>();
        Student student=studentMapper.selectStudentByAccount(accountOrName);
        if(student!=null){
            students.add(student);
            return students;
        }
        students=studentMapper.selectStudentByName(accountOrName);
        return students;
    }

    /**
     * 更新：学生信息
     * @param student
     * @return
     */
    public Long updateStudentInformation(Student student){
        return studentMapper.updateStudentInformation(student);
    }

    /**
     * 更新：学生密码
     * @param id
     * @param password
     * @return
     */
    public Long updateStudentPassword(Long id,String password){
        return studentMapper.updateStundentPasswordByAdmin(password,id);
    }

    /**
     * 删除：学生，及klass_student表关系
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Long deleteStudentById(Long id){
        studentMapper.deleteKlaaStudentByStudent(id);
        return studentMapper.deleteStudentById(id);
    }

    /**
     * 更新：学生激活
     * @param student
     * @return
     */
    public Long updateStudentActive(Student student){
        return studentMapper.updateStudentAcctive(student);
    }

    public Student findStudentByAccountAndStudentName(String account,String studentName)
    {
        return studentMapper.findStudentByAccountAndStudentName(account,studentName);
    }

    public Long insertStudent(Student student)
    {
        return studentMapper.insertStudent(student);
    }

}

