package com.example.user.service;

import com.example.common.dao.AttendanceDao;
import com.example.common.dao.CourseDao;
import com.example.common.dao.SeminarDao;
import com.example.common.entity.*;
import com.example.common.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author perth
 * @ClassName SeminarService
 * @Description TODO
 * @Date 2018/12/17 19:07
 * @Version 1.0
 **/

@Service
public class SeminarService {
    @Autowired
    private SeminarDao seminarDao;

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private AttendanceDao attendanceDao;


    /**
     * 获取当前提问人数
     * @param klassSeminarId
     * @param attendanceId
     * @return
     */
    public int getQuestionNumByKlassSeminarIdAndAttendanceId(Long klassSeminarId,Long attendanceId){
        return attendanceDao.selectQuestionNumByKlassSeminarIdAndAttendanceId(klassSeminarId,attendanceId);
    }

    /**
     * 获取当前展示
     * @param klassSeminarId
     * @return
     */
    public Attendance getIsPresentAttendanceByKlassSeminarId(Long klassSeminarId){
        return attendanceDao.selectIsPresentAttendanceByKlassSeminarId(klassSeminarId);
    }

    /**
     * 提问
     * @param question
     * @return
     */
    public String addQuestion(Question question){
       return seminarDao.addQuestion(question);
    }

    /**
     * 删除：所有未被选中的question
     * @param klassSeminarId
     * @return
     */
    public Long deleteQuestion(Long klassSeminarId){
        return attendanceDao.deleteQuestion(klassSeminarId);
    }

    /**
     * 创建讨论课
     * @param seminar
     * @return
     */
    public long createSeminar(Seminar seminar){
        return seminarDao.addSeminar(seminar);
    }

    /**
     * 查询：seminarID->klass
     * @param seminarId
     * @return
     */
    public ArrayList<Klass>getKlassBySeminarId(Long seminarId){
        return seminarDao.getKlassBySeminarId(seminarId);
    }

    /**
     * 查询：id->seminar
     * @param id
     * @return
     */
    public Seminar getSeminarById(Long id){
        return seminarDao.selectSeminarById(id);
    }

    /**
     * 更新：修改讨论课
     * @param seminar
     * @return
     */
    public Long updateSeminar(Seminar seminar){
        return seminarDao.updateSeminar(seminar);
    }

    /**
     * 更新：修改讨论课
     * @param klassSeminar
     * @return
     */
    public Long updateKlassSeminar(KlassSeminar klassSeminar){
        return seminarDao.updateKlassSeminar(klassSeminar);
    }

    /**
     * 更新：设置讨论课轮次
     * @param roundId
     * @param id
     * @return
     */
    public Long updateSeminarRoundId(Long roundId,Long id){
        return seminarDao.updateSeminarRoundId(roundId,id);
    }

    /**
     * 更新：设置班级讨论课状态
     * @param klassSeminar
     * @return
     */
    public Long updateSeminarStatus(KlassSeminar klassSeminar){
        return seminarDao.updateSeminarStatus(klassSeminar);
    }

    /**
     * 删除：seminar
     * @param id
     * @return
     */
    public Long deleteSeminarById(Long id){
        return seminarDao.deleteSeminarById(id);
    }

    /**
     * 查询：班级讨论课
     * @param klassId
     * @param seminarid
     * @return
     */
    public KlassSeminar getKlassSeminarByKlassIdAndSeminarId(Long klassId,Long seminarid){
        return seminarDao.selectKlassSeminarByKlassIdAndSeminarId(klassId,seminarid);
    }

    /**
     * 查询：小组的讨论课成绩
     * @param teamId
     * @param seminaId
     * @return
     */
    public Team getTeamSeminarSocre(Long teamId,Long seminaId){
        return seminarDao.selectTeamSeminarScore(teamId,seminaId);
    }

    public Long updateTeamSeminarPresentationScore(Score score){
        return seminarDao.updateTeamSeminarPresentationScore(score);
    }

    /**
     * 更新：团队讨论课成绩,计算总成绩
     * @param score
     * @param seminarId
     * @return
     */
    public Long updateTeamSeminarScore(Score score,Long seminarId){
        Seminar seminar=seminarDao.selectSeminarById(seminarId);
        double totalScore=calculateTeamSeminarScore(score,seminar.getCourseId());
        score.setTotalScore(totalScore);
        return seminarDao.updateTeamSeminarScore(score,seminarId);
    }

    /**
     * 查询：一个班级一次讨论课的成绩
     * @param klassId
     * @param seminarId
     * @return
     */
    public ArrayList<Team>getSeminarScore(Long klassId,Long seminarId){
        return seminarDao.getSeminarScore(seminarId,klassId);
    }


    /**
     * 计算团队讨论课的totalScore
     * @param score
     * @param courseId
     * @return
     */
    public double calculateTeamSeminarScore(Score score,Long courseId){
        Course course=courseDao.getCourseById(courseId);
        double totalScore=0;
        totalScore+=score.getPresentationScore()*course.getPresentationPercentage();
        totalScore+=score.getQuestionScore()*course.getQuestionPercentage();
        totalScore+=score.getReportScore()*course.getReportPercentage();
        return totalScore;
    }

    public Long getClassSeminarIdBySeminarIdAndClassId(Long classId,Long seminarId)
    {
        return seminarDao.getClassSeminarIdBySeminarIdAndClassId(classId,seminarId);
    }

    public ArrayList<String> getAllSeminarReportByClassSeminarId(Long classSeminarId)
    {
        return seminarDao.getAllSeminarReportByClassSeminarId(classSeminarId);
    }

    public ArrayList<String> getAllSeminarPptByClassSeminarId(Long classSeminarId)
    {
        return seminarDao.getAllSeminarPptByClassSeminarId(classSeminarId);
    }

    public ArrayList<Attendance> getAllAttendance(Long seminarId,Long classId)
    {
        return seminarDao.getAllAttendance(seminarId,classId);
    }

    public Attendance getAtteandanceByTeamOrderAndKlassSeminarId(Long klassSeminarId,int teamOrder){
        return seminarDao.getAtteandanceByTeamOrderAndKlassSeminarId(klassSeminarId,teamOrder);
    }

    public Long updateAttendanceByClassSeminarId(Long seminarId,Long classId,Long teamId,int teamOrder)
    {
        return seminarDao.updateAttendanceByClassSeminarId(seminarId,classId,teamId,teamOrder);
    }

    public Long updateReportScoreByTeamId(Long seminarId,Long classId,Long teamId,double reportScore)
    {
        Long klassSeminarId=seminarDao.getClassSeminarIdBySeminarIdAndClassId(classId,seminarId);
        return seminarDao.updateReportScoreByKlassSeminarIdTeamId(klassSeminarId,teamId,reportScore);
    }


}
