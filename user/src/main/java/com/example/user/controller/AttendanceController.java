package com.example.user.controller;

import com.example.common.config.FileUploudConfig;
import com.example.common.entity.Question;
import com.example.user.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName AtteandanceController
 * @Description
 * @Author perth
 * @Date 2018/12/23 0023 上午 10:59
 * @Version 1.0
 **/
@RestController
@RequestMapping(value="/attendance")
public class AttendanceController {
    private final static String FILEPATH="/www/wwwroot/zhaotao/";

    @Autowired
    private AttendanceService attendanceService;



    /**
     * 抽取提问
     * @param atteandanceId
     * @return
     */
    @GetMapping(value = "/{attendanceId}/question")
    public Question getQuestionByAttendance(@PathVariable("attendanceId")Long atteandanceId){
        return attendanceService.getQuestionByAttendanceId(atteandanceId);
    }

    /**
     * 上传书面报告
     * @param attendanceId
     * @param file
     * @return
     */
    @PostMapping(value="/{attendanceId}/report")
    public String setAttendanceReport(@PathVariable(value="attendanceId")Long attendanceId,MultipartFile file)
    {
        FileUploudConfig fileUploudConfig=new FileUploudConfig();
        String fileName=fileUploudConfig.upload(file);
        String filePath=FILEPATH+fileName;
        if((!filePath.equals("文件为空"))&&(!filePath.equals("上传失败"))) {
            attendanceService.setAttendanceReport(fileName,filePath, attendanceId);
        }
        return filePath;
    }

    /**
     * 下载书面报告
     * @param attendanceId
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @GetMapping(value="/{attendanceId}/report")
    public String getAttendanceReport(@PathVariable(value="attendanceId")Long attendanceId, HttpServletRequest request, HttpServletResponse response)throws IOException
    {
        FileUploudConfig fileUploudConfig=new FileUploudConfig();
        String fileName=attendanceService.getFileNameById(attendanceId);
        fileUploudConfig.downloadFile(request,response,fileName);
        return fileName;
    }

    /**
     * 上传ppt
     * @param attendanceId
     * @param file
     * @return
     */
    @PostMapping(value="/{attendanceId}/powerpoint")
    public String setAttendancePpt(@PathVariable(value="attendanceId")Long attendanceId,MultipartFile file)
    {
        FileUploudConfig fileUploudConfig=new FileUploudConfig();
        String fileName=fileUploudConfig.upload(file);
        String filePath=FILEPATH+fileName;
        if((!filePath.equals("文件为空"))&&(!filePath.equals("上传失败"))) {
            attendanceService.setAttendancePpt(fileName,filePath, attendanceId);
        }
        return filePath;
    }

    /**
     * 下载ppt
     * @param attendanceId
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @GetMapping(value="/{attendanceId}/powerpoint")
    public String getAttendancePpt(@PathVariable(value="attendanceId")Long attendanceId, HttpServletRequest request, HttpServletResponse response)throws IOException
    {
        FileUploudConfig fileUploudConfig=new FileUploudConfig();
        String fileName=attendanceService.getPptNameById(attendanceId);
        fileUploudConfig.downloadFile(request,response,fileName);
        return fileName;
    }

    @PutMapping(value="/{attendanceId}")
    public Long updateAttendanceInfo(@RequestParam(value="teamOrder")int teamOrder,@PathVariable(value="attendanceId")Long attendanceId)
    {
        return attendanceService.updateAttendanceInfo(teamOrder,attendanceId);
    }

    @DeleteMapping(value="/{attendanceId}")
    public Long deleteAttendanceByAttendanceId(@PathVariable(value="attendanceId")Long attendanceId)
    {
        return attendanceService.deleteAttendanceByAttendanceId(attendanceId);
    }

}
