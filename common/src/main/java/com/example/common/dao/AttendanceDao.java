package com.example.common.dao;

import com.example.common.entity.Question;
import com.example.common.mapper.AttendanceMapper;
import com.example.common.mapper.QuestionMapper;
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

    /**
     * 更新问题状态
     * @param id
     * @return
     */
    public Long uodateQuestionIsSelected(Long id){
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
