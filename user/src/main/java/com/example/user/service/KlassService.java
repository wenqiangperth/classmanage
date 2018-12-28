package com.example.user.service;

import com.example.common.config.FileUploudConfig;
import com.example.common.config.ReadExcelServlet;
import com.example.common.dao.CourseDao;
import com.example.common.dao.KlassDao;
import com.example.common.dao.StudentDao;
import com.example.common.entity.Course;
import com.example.common.entity.Klass;
import com.example.common.entity.Round;
import com.example.common.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName KlassService
 * @Description
 * @Author perth
 * @Date 2018/12/24 0024 下午 8:05
 * @Version 1.0
 **/
@Service
public class KlassService {
    private final static String FILEPATH="/www/wwwroot/zhaotao/";
    @Autowired
    KlassDao klassDao;

    @Autowired
    StudentDao studentDao;

    @Autowired
    private CourseDao courseDao;

    /**
     * 创建班级
     * @param klass
     * @return
     */
    public Long addKlass(Klass klass){
        return klassDao.insertKlass(klass);
    }

    public Long updateClassStudentByExcel(Long classId, MultipartFile file)
    {
        Klass klass = klassDao.getClassByClassId(classId);
        FileUploudConfig fileUploudConfig = new FileUploudConfig();
        String fileName=fileUploudConfig.upload(file);
        String filePath=FILEPATH+fileName;
        ReadExcelServlet readExcelServlet=new ReadExcelServlet();
        List<List<String>> list=readExcelServlet.readExcelInfo(filePath);
        for(List<String> temps:list)
        {
            Student student=new Student();
            student.setAccount(temps.get(0));
            student.setStudentName(temps.get(1));
            student.setIsActive(0);
            student.setPassword("123456");
            Student s=studentDao.findStudentByAccountAndStudentName(student.getAccount(),student.getStudentName());
            if(s==null)
            {
                studentDao.insertStudent(student);
                s=studentDao.findStudentByAccountAndStudentName(student.getAccount(),student.getStudentName());
            }
            System.out.println(s.getAccount());
            System.out.println(s.getStudentName());
            klassDao.insertKlassStudent(klass.getId(),s.getId(),klass.getCourseId());

        }
        return 1L;
    }

    public Long deleteClassByClassId(Long classId)
    {
        return klassDao.deleteClassByClassId(classId);
    }

    public Klass getKlassById(Long id){
        return klassDao.getClassByClassId(id);
    }
}
