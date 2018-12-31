package com.example.common.dao;

import com.example.common.entity.*;
import com.example.common.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;

/**
 * @author perth
 * @ClassName SeminarDao
 * @Description TODO
 * @Date 2018/12/17 23:03
 * @Version 1.0
 **/
@Repository
public class SeminarDao {
    @Autowired
    private SeminarMapper seminarMapper;

    @Autowired
    private KlassMapper klassMapper;

    @Autowired
    private RoundMapper roundMapper;

    @Autowired
    private TeamMapper teamMapper;

    @Autowired
    private ScoreMapper scoreMapper;

    @Autowired
    private AttendanceMapper attendanceMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private QuestionMapper questionMapper;

    public String addQuestion(Question question){
        Question oldQuestion=questionMapper.selectQuestionByAttendanceIdAndStudentId(question.getAttendanceId(),question.getStudentId(),question.getIsSelected());
        if(oldQuestion!=null){
            return "请您不要重复提问！";
        }
        else if(questionMapper.insertQuestion(question)<=0){
            return "提问失败";
        }else {
            return "报名提问成功";
        }
    }

    /**
     * 创建:讨论课，klass_seminar关系
     * @param seminar
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public long addSeminar(Seminar seminar){
        if(seminar.getRoundId()==null){
            ArrayList<Round>rounds=roundMapper.getAllRoundByCourseId(seminar.getCourseId());
            int serial=0;
            for (Round round:rounds
            ) {
                if(serial<round.getRoundSerial()){
                    serial=round.getRoundSerial();
                }
            }
            Round round=new Round();
            round.setCourseId(seminar.getCourseId());
            round.setRoundSerial(serial+1);
            int method=1;
            round.setReportScoreMethod(method);
            round.setQuestionScoreMethod(method);
            round.setPresentationScoreMethod(method);
            roundMapper.insertRound(round);
            seminar.setRoundId(round.getId());
        }
        Long i=seminarMapper.insertSeminar(seminar);
        if(i<=0){return i;}
        int status=0;
        ArrayList<Klass>klasses=klassMapper.getAllClassByCourseId(seminar.getCourseId());
        for (Klass klass:klasses
             ) {
            seminarMapper.insertKlassSeminar(klass.getId(),seminar.getId(),status);
        }

        return seminar.getId();
    }

    /**
     * 查询：seminarId->klass 内含klass_seminar
     * @param seminarId
     * @return
     */
    public ArrayList<Klass> getKlassBySeminarId(Long seminarId){
        ArrayList<Klass>klasses=klassMapper.getAllKlassBySeminarId(seminarId);
        if(klasses!=null){
            for (Klass klass:klasses
                 ) {
                KlassSeminar klassSeminar=klassMapper.getKlassSeminarByKlassAndSeminar(klass.getId(),seminarId);
                klass.setKlassSeminar(klassSeminar);
            }
        }
        return klasses;
    }

    /**
     * 查询：id->seminar
     * @param id
     * @return
     */
    public Seminar selectSeminarById(Long id){

        return seminarMapper.selectSeminarById(id);
    }

    /**
     * 更新：修改seminar
     * @param seminar
     * @return
     */
    public Long updateSeminar(Seminar seminar){
        return seminarMapper.updateSeminarById(seminar);
    }

    /**
     * 删除：讨论课，以及klass_seminar关系
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Long deleteSeminarById(Long id){
        Long i=seminarMapper.deleteSeminarById(id);
        if(i<=0){
            return i;
        }
        seminarMapper.deleteKlassSeminarBySeimarId(id);
        return i;
    }

    /**
     * 更新：修改klass_seminar
     * @param klassSeminar
     * @return
     */
    public Long updateKlassSeminar(KlassSeminar klassSeminar){
        return seminarMapper.updateKlassSeminarByKlassIdAndSeminarId(klassSeminar);
    }

    /**
     * 更新：设置讨论课轮次
     * @param roundId
     * @param id
     * @return
     */
    public Long updateSeminarRoundId(Long roundId,Long id){
        return seminarMapper.updateSeminarRoundId(roundId,id);
    }

    /**
     * 更新：设置班级讨论课状态
     * @param klassSeminar
     * @return
     */
    public Long updateSeminarStatus(KlassSeminar klassSeminar){
        return seminarMapper.updateSeminarStatus(klassSeminar);
    }
    /**
     * 查询：klassId,seminarid->klass_seminar关系
     * @param klassId
     * @param seminarId
     * @return
     */
    public KlassSeminar selectKlassSeminarByKlassIdAndSeminarId(Long klassId,Long seminarId){
        return klassMapper.getKlassSeminarByKlassAndSeminar(klassId,seminarId);
    }

    /**
     * 查询：team在一次讨论课的成绩
     * @param teamId
     * @param seminarId
     * @return
     */
    public Team selectTeamSeminarScore(Long teamId,Long seminarId){
        Team team=teamMapper.selectTeamById(teamId);

        Long courseId=courseMapper.findCourseIdByClassId(team.getKlassId());
        Course course=courseMapper.getCourseById(courseId);
        Score score=scoreMapper.selectTeamSeminarScore(teamId,team.getKlassId(),seminarId);
        score.setTotalScore((score.getPresentationScore()*course.getPresentationPercentage()/100+score.getReportScore()*course.getReportPercentage()/100+score.getQuestionScore()*course.getQuestionPercentage()/100));
        scoreMapper.updateTeamSeminarScore(score);
        team.setScore(score);
        return team;
    }

    public Long updateTeamSeminarPresentationScore(Score score){
        return scoreMapper.updateTeamSeminarPresentationScore(score);
    }

    /**
     * 更新：团队讨论课成绩
     * @param score
     * @param seminarId
     * @return
     */
    public Long updateTeamSeminarScore(Score score,Long seminarId){
        Team team=teamMapper.selectTeamById(score.getTeamId());
        KlassSeminar klassSeminar=klassMapper.getKlassSeminarByKlassAndSeminar(team.getKlassId(),seminarId);
        score.setSeminarOrRoundId(klassSeminar.getId());
        return scoreMapper.updateTeamSeminarScore(score);
    }

    /**
     * 查询：一个班级的一次讨论课成绩
     * @param seminarId
     * @param klassId
     * @return
     */
    public ArrayList<Team>getSeminarScore(Long seminarId,Long klassId){
        ArrayList<Score>scores=scoreMapper.selectSeminarScore(klassId,seminarId);
        ArrayList<Team>teams=new ArrayList<>();
        for (Score score:scores
             ) {
            Team team=teamMapper.selectTeamById(score.getTeamId());
            score.setTotalScore((score.getPresentationScore()+score.getQuestionScore()+score.getReportScore())/3);
            team.setScore(score);
            teams.add(team);
        }
        return teams;
    }
    public ArrayList<Seminar> findAllSeminarByCourseId(Long courseId)
    {
        return seminarMapper.findAllSeminarByCourseId(courseId);
    }

    public Long deleteAllSeminarByCourseId(Long courseId)
    {
        return seminarMapper.deleteSeminarByCourseId(courseId);
    }

    public Long deleteAllClassSeminarByClassId(Long classId)
    {
        return seminarMapper.deleteSeminarByCourseId(classId);
    }

    public Long getClassSeminarIdBySeminarIdAndClassId(Long classId,Long seminarId)
    {
        return seminarMapper.getClassSeminarIdBySeminarIdAndClassId(classId,seminarId);
    }

    public ArrayList<String> getAllSeminarReportByClassSeminarId(Long classSeminarId)
    {
        return attendanceMapper.getAllSeminarReportByClassSeminarId(classSeminarId);
    }

    public ArrayList<String> getAllSeminarPptByClassSeminarId(Long classSeminarId)
    {
        return attendanceMapper.getAllSeminarPptByClassSeminarId(classSeminarId);
    }

    public ArrayList<Attendance> getAllAttendance(Long seminarId,Long classId)
    {
        Long classSeminarId=seminarMapper.getClassSeminarIdBySeminarIdAndClassId(classId,seminarId);
        ArrayList<Attendance> attendances= attendanceMapper.getAllAttendanceByKlassSeminarId(classSeminarId);
        Seminar seminar=seminarMapper.getSeminarByKlassSeminarId(classSeminarId);
        for(Attendance attendance:attendances)
        {
            Team team=teamMapper.selectTeamById(attendance.getTeamId());
            team.setKlassSerial(klassMapper.getKlassByKlassId(team.getKlassId()).getKlassSerial());
            attendance.setTeam(team);
            Score score=scoreMapper.selectSeminarScoreByClassSeminarIdAndTeamId(classSeminarId,attendance.getTeamId());
            if(score!=null) {
                score.setSeminarName(seminar.getSeminarName());
                attendance.setScore(score);
            }
        }
        return attendances;
    }

    public Attendance getAtteandanceByTeamOrderAndKlassSeminarId(Long klassSeminarId,int teamOrder){
        return attendanceMapper.getAtteandanceByTeamOrderAndKlassSeminarId(klassSeminarId,teamOrder);
    }

    public Long updateAttendanceByClassSeminarId(Long seminarId,Long classId,Long teamId,int teamOrder)
    {
        Long classSeminarId=seminarMapper.getClassSeminarIdBySeminarIdAndClassId(classId,seminarId);
        return attendanceMapper.insertAttendanceByClassSeminarId(teamId,teamOrder,classSeminarId,0);
    }

    public Long updateReportScoreByKlassSeminarIdTeamId(Long klassSeminarId,Long teamId,double reportScore)
    {
        return seminarMapper.updateReportScoreByKlassSeminarIdTeamId(klassSeminarId,teamId,reportScore);
    }

}
