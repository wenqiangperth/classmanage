package com.example.common.dao;

import com.example.common.mapper.AttendanceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
