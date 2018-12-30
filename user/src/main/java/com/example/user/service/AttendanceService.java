package com.example.user.service;

import com.example.common.dao.AttendanceDao;
import com.example.common.dao.TeamDao;
import com.example.common.entity.Attendance;
import com.example.common.entity.Question;
import com.example.common.entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName AttendanceService
 * @Description
 * @Author perth
 * @Date 2018/12/23 0023 上午 11:20
 * @Version 1.0
 **/
@Service
public class AttendanceService {
    @Autowired
    private AttendanceDao attendanceDao;

    @Autowired
    private TeamDao teamDao;


    public Long updateQuestionScore(double score,Long id){
        return attendanceDao.updateQuestionScore(score,id);
    }

    public Long updateAttendanceStatus(Attendance attendance){
        return attendanceDao.updateAttendanceStatus(attendance);
    }


    public Attendance getAttendanceById(Long attendanceId){
        return attendanceDao.getAttendanceById(attendanceId);
    }

    /**
     * 抽取提问
     * @param attendanceId
     * @return
     */
    public Question getQuestionByAttendanceId(Long attendanceId){
        ArrayList<Question>questions=attendanceDao.getAllQuestionByAttendanceId(attendanceId);
        if(questions.isEmpty()){
            return null;
        }
        ArrayList<Question>allQuestions=attendanceDao.getAllQuestionByKlassSeminarId(questions.get(0).getKlassSeminarId());
        Map<Long,Long>teamMaps=new HashMap<Long,Long>();
        for (Question question:allQuestions
             ) {
            if(question.getIsSelected()==1){
                if(teamMaps.get(question.getTeamId())!=null) {
                    teamMaps.put(question.getTeamId(), teamMaps.get(question.getTeamId()) + 1);
                }else{
                   teamMaps.put(question.getTeamId(),1L);
                }
            }
        }
        Question chooseQuestion=questions.get(0);
        for (Question question:questions
             ) {
            if(teamMaps.get(chooseQuestion.getTeamId())==null){
                break;
            }
            if(teamMaps.get(question.getTeamId())==null||teamMaps.get(question.getTeamId())<teamMaps.get(chooseQuestion.getTeamId())){
                chooseQuestion=question;
            }

        }
        attendanceDao.updateQuestionIsSelected(chooseQuestion.getId());
        Team team=teamDao.getTeamByTeamId(chooseQuestion.getTeamId());
        chooseQuestion.setTeam(team);
        return chooseQuestion;
    }

    public Long setAttendanceReport(String fileName,String filePath,Long attendanceId)
    {
        return attendanceDao.setAttendanceReport(fileName,filePath,attendanceId);
    }

    public String getFileNameById(Long attendanceId)
    {
        return  attendanceDao.getFileNameById(attendanceId);
    }

    public Long setAttendancePpt(String fileName,String filePath,Long attendanceId)
    {
        return attendanceDao.setAttendancePpt(fileName,filePath,attendanceId);
    }

    public String getPptNameById(Long attendanceId)
    {
        return  attendanceDao.getPptNameById(attendanceId);
    }

    public Long updateAttendanceInfo(int teamOrder,Long attendanceId)
    {
        return attendanceDao.updateAttendanceInfo(teamOrder,attendanceId);
    }

    public Long deleteAttendanceByAttendanceId(Long attendanceId)
    {
        return attendanceDao.deleteAttendanceByAttendanceId(attendanceId);
    }
}
