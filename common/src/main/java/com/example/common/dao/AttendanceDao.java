package com.example.common.dao;

import com.example.common.entity.Attendance;
import com.example.common.entity.Question;
import com.example.common.entity.Team;
import com.example.common.mapper.AttendanceMapper;
import com.example.common.mapper.QuestionMapper;
import com.example.common.mapper.TeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @ClassName attendanceDao
 * @Description
 * @Author perth
 * @Date 2018/12/23 0023 上午 11:21
 * @Version 1.0
 **/
@Repository
public class AttendanceDao {
    @Autowired
    private AttendanceMapper attendanceMapper;
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private TeamMapper teamMapper;

    public Long updateQuestionScore(double score,Long id){
        return questionMapper.updateQuestionScore(score,id);
    }

    public Long updateAttendanceStatus(Attendance attendance){
        return attendanceMapper.updateAttendanceIsPresent(attendance);
    }

    /**
     * 获取当前提问数
     * @param klassSeminarId
     * @param attendanceId
     * @return
     */
    public int selectQuestionNumByKlassSeminarIdAndAttendanceId(Long klassSeminarId,Long attendanceId){
        return questionMapper.selectQuestionNumByKlassSeminarIdAndAttendanceId(klassSeminarId,attendanceId,0);
    }

    /**
     * 查询当前正在展示attendance
     * @param klassSeminarId
     * @return
     */
    public Attendance selectIsPresentAttendanceByKlassSeminarId(Long klassSeminarId){
        return attendanceMapper.selectIsPresentAttendanceByKlassSeminarId(klassSeminarId);
    }

    /**
     * 删除所有未被选中的question
     * @param klassSeminarId
     * @return
     */
    public Long deleteQuestion(Long klassSeminarId){
        return questionMapper.deleteQuestion(klassSeminarId);
    }

    /**
     * 查询：获得展示
     * @param id
     * @return
     */
    public Attendance getAttendanceById(Long id){
        Attendance attendance= attendanceMapper.selectAttendanceById(id);
        Team team=teamMapper.selectTeamById(attendance.getTeamId());
        attendance.setTeam(team);
        return attendance;
    }

    /**
     * 更新问题状态
     * @param id
     * @return
     */
    public Long updateQuestionIsSelected(Long id){
        return questionMapper.updateQuestionIsSelected(id);
    }

    public ArrayList<Question>getAllQuestionByAttendanceId(Long attendanceId){
        return questionMapper.getAllQuestionByAttendanceId(attendanceId);
    }

    public ArrayList<Question>getAllQuestionByKlassSeminarId(Long klassSeminarId){
        return questionMapper.getAllQuestionByKlassSeminarId(klassSeminarId);
    }

    public Long setAttendanceReport(String fileName,String filePath,Long attendanceId)
    {
        attendanceMapper.setAttendanceReport(fileName,filePath,attendanceId);
        return  1L;
    }

    public String getFileNameById(Long attendanceId)
    {
        return attendanceMapper.getFileNameById(attendanceId);
    }

    public Long setAttendancePpt(String fileName,String filePath,Long attendanceId)
    {
        attendanceMapper.setAttendancePpt(fileName,filePath,attendanceId);
        return  1L;
    }

    public String getPptNameById(Long attendanceId)
    {
        return attendanceMapper.getPptNameById(attendanceId);
    }

    public Long updateAttendanceInfo(int teamOrder,Long attendanceId)
    {
        return attendanceMapper.updateAttendanceInfo(teamOrder,attendanceId);
    }

    public Long deleteAttendanceByAttendanceId(Long attendanceId)
    {
        return attendanceMapper.deleteAttendanceByAttendanceId(attendanceId);
    }
}
