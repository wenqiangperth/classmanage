package com.example.common.dao;

import com.example.common.entity.Course;
import com.example.common.entity.Klass;
import com.example.common.entity.Round;
import com.example.common.entity.Seminar;
import com.example.common.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import java.util.Date;

/**
 * @ClassName KlassDao
 * @Description
 * @Author perth
 * @Date 2018/12/22 0022 下午 2:48
 * @Version 1.0
 **/
@Repository
public class KlassDao {

    @Autowired
    private KlassMapper klassMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private TeamMapper teamMapper;

    @Autowired
    private RoundMapper roundMapper;

    @Autowired
    private SeminarMapper seminarMapper;

    /**
     * 新建班级,同时增减klass_seminar和klass_round关系
     * @param klass
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Long insertKlass(Klass klass){
        Long i=klassMapper.insertKlass(klass);
        if(i<=0){
            return i;
        }
        Course course=courseMapper.getCourseById(klass.getCourseId());
        Long courseId=course.getId();
        if(course.getSeminarMainCourseId()!=null){
            courseId=course.getSeminarMainCourseId();
        }
        ArrayList<Round> rounds=roundMapper.getAllRoundByCourseId(courseId);
        for (Round round:rounds
        ) {
            klassMapper.insertKlassRound(klass.getId(),round.getId());
        }
        ArrayList<Seminar>seminars=seminarMapper.findAllSeminarByCourseId(courseId);
        for (Seminar seminar:seminars
             ) {
            seminarMapper.insertKlassSeminar(klass.getId(),seminar.getId(),0);
        }
        return i;
    }

    /**
     * 查询:klass
     * @param classId
     * @return
     */
    public Klass getClassByClassId(Long classId)
    {
        return klassMapper.getKlassByKlassId(classId);
    }


    /**
     * 删除：id->klass,以及关系
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Long deleteKlassById(Long id){
        Long i=klassMapper.deleteKlassById(id);
        if(i>0){
            klassMapper.deleteClassRoundByClassId(id);
            klassMapper.deleteClassSeminarByClassId(id);
            klassMapper.deleteClassStudentByClassId(id);
            teamMapper.deleteTeamByKlassId(id);
        }
        return i;
    }

    /**
     * 插入:班级学生
     * @param klassId
     * @param studentId
     * @param courseId
     * @return
     */
    public Long insertKlassStudent(Long klassId,Long studentId,Long courseId)
    {
        return klassMapper.insertKlassStudent(klassId,studentId,courseId);
    }

    @Transactional(rollbackFor = Exception.class)
    public Long deleteClassByClassId(Long classId)
    {
        klassMapper.deleteClassRoundByClassId(classId);
        klassMapper.deleteClassSeminarByClassId(classId);
        klassMapper.deleteClassStudentByClassId(classId);
        klassMapper.deleteClassTeamByClassId(classId);
        return klassMapper.deleteClassByClassId(classId);
    }

    public Long getClassIdByCourseIdAndStudentId(Long courseId,Long studentId){
        return klassMapper.getClassIdByCourseIdAndStudentId(courseId,studentId);
    }

    public Long deleteAllKlassRoundByKlassId(Long classId)
    {
        return klassMapper.deleteAllKlassRoundByKlassId(classId);
    }


    public Long deleteAllKlassSeminarByKlassId(Long classId)
    {
        return klassMapper.deleteAllKlassSeminarByKlassId(classId);
    }
    public Long insertKlassRound(Long klassId,Long roundId)
    {
        return klassMapper.insertKlassRound(klassId,roundId);
    }

    public Long insertKlassSeminar(Long klassId, Long seminarId, Date reportDdl,int status)
    {
        return klassMapper.insertKlassSeminar(klassId,seminarId,reportDdl,status);
    }

    public Long deleteAllKlassTeamByClassId(Long classId)
    {
        return klassMapper.deleteAllKlassTeamByClassId(classId);
    }
}
