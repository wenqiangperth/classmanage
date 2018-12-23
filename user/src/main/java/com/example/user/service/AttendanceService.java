package com.example.user.service;

import com.example.common.dao.AttendanceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
