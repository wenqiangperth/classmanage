package com.example.user.service;

import com.example.common.dao.KlassDao;
import com.example.common.dao.TeacherDao;
import com.example.common.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author perth
 * @ClassName TeacherService
 * @Description TODO
 * @Date 2018/12/17 19:03
 * @Version 1.0
 **/
@Service
public class TeacherService {
    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private KlassDao klassDao;

    /**
     * 更新：teacher激活
     * @param teacher
     * @return
     */
    public Long activeTeacher(Teacher teacher){
        return teacherDao.updateTeacherActive(teacher);
    }

    /**
     * 删除：id->klass,以及关系
     * @param id
     * @return
     */
    public Long deleteKlassById(Long id){
        return klassDao.deleteKlassById(id);
    }

}
