package com.example.user.controller;

import com.example.common.config.FileUploudConfig;
import com.example.common.entity.*;
import com.example.common.mapper.SeminarMapper;
import com.example.user.service.SeminarService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author perth
 * @ClassName SeminarController
 * @Description TODO
 * @Date 2018/12/17 19:07
 * @Version 1.0
 **/

@RestController
@RequestMapping(value = "/seminar")
public class SeminarController {
    @Autowired
    private SeminarService seminarService;

    @Autowired
    private SeminarMapper seminarMapper;

    private final static String WEBSEVER="47.107.103.28:8081";


    /**
     * 返回websocket的url
     * @param klassSeminarId
     * @param httpServletRequest
     * @return
     */
    @GetMapping(value = "/{klassseminarId}/enterseminar")
    public String enterWebSocket(@PathVariable("klassseminarId")Long klassSeminarId,HttpServletRequest httpServletRequest){
        Long userId=Long.parseLong(httpServletRequest.getAttribute("id").toString());
        String role=httpServletRequest.getAttribute("role").toString();
        return "ws://"+WEBSEVER+"/websocket/"+klassSeminarId+"/"+userId+"/"+role;
    }

    /**
     * 获取当前正在展示小组
     * @param klassSeminarId
     * @return
     */
    @GetMapping(value = "/{klassseminarId}/presentattendance")
    public Attendance getIsPresentAttendanceByKlassSeminarId(@PathVariable("klassseminarId") Long klassSeminarId){
        return seminarService.getIsPresentAttendanceByKlassSeminarId(klassSeminarId);
    }

    /**
     * 获取当前提问人数
     * @param klassSeminarId
     * @param attendanceId
     * @return
     */
    @GetMapping(value = "/{klassseminarId}/attendance/{attendanceId}/questionnumber")
    public int getQuestionNumByKlassSeminarIdAndAttendanceId(@PathVariable("klassseminarId")Long klassSeminarId,@PathVariable("attendanceId")Long attendanceId){
        System.out.println(klassSeminarId+"perth"+attendanceId);
        int i=seminarService.getQuestionNumByKlassSeminarIdAndAttendanceId(klassSeminarId,attendanceId);
        System.out.println("dasas"+i);
        return i;
    }


    @GetMapping(value = "/{seminarId}/class/{klassId}/enterseminar")
    public String enterSeminar(HttpServletRequest httpServletRequest, @PathVariable("seminarId")Long seminarId,@PathVariable("klassId")Long klassId){
        Long userId=Long.parseLong(httpServletRequest.getAttribute("id").toString());
        String role=httpServletRequest.getAttribute("role").toString();
        Long klassSeminarId=seminarMapper.getKlassSeminarId(klassId,seminarId);
        return "ws://"+WEBSEVER+"/websocket/"+klassSeminarId+"/"+userId+"/"+role;
    }

    /**
     * 提问
     * @param httpServletRequest
     * @param klassSeminarId
     * @param attendanceId
     * @param teamId
     * @return
     */
    @PostMapping(value = "/{klassseminarId}/attendance/{attendanceId}/team/{teamId}/question")
    public String addQuestion(HttpServletRequest httpServletRequest, @PathVariable("klassseminarId")Long klassSeminarId,@PathVariable("attendanceId")Long attendanceId,@PathVariable("teamId")Long teamId){
        Question question=new Question();
        question.setAttendanceId(attendanceId);
        question.setKlassSeminarId(klassSeminarId);
        Long studentId=Long.parseLong(httpServletRequest.getAttribute("id").toString());
        question.setStudentId(studentId);
        question.setTeamId(teamId);
        question.setIsSelected(0);
        return seminarService.addQuestion(question);
    }

    /**
     * 删除所有未被选中的问题
     * @param klassSeminarId
     * @return
     */
    @DeleteMapping(value = "/{klassseminarId}/question")
    public Long deleteQuestion(@PathVariable("klassseminarId")Long klassSeminarId){
        return seminarService.deleteQuestion(klassSeminarId);
    }

    /**
     * 创建讨论课
     * @param seminar
     * @return讨论课ID
     */
    @PostMapping(value = "")
    public long createSeminar(@RequestBody Seminar seminar){
        return seminarService.createSeminar(seminar);
    }

    /**
     * 查询：seminarID->klass
     * @param semianrId
     * @return
     */
    @GetMapping(value = "/{seminarId}/class")
    public ArrayList<Klass>getKlassBySeminarId(@PathVariable(name = "seminarId")Long semianrId){
        return seminarService.getKlassBySeminarId(semianrId);
    }

    /**
     * 查询:id->seminar
     * @param seminarId
     * @return
     */
    @GetMapping(value = "/{seminarId}")
    public Seminar getSeminarById(@PathVariable(name = "seminarId")Long seminarId){
        return seminarService.getSeminarById(seminarId);
    }

    /**
     * 更新：修改讨论课
     * @param seminarId
     * @param seminar
     * @return
     */
    @PutMapping(value = "/{seminarId}")
    public Long updateSeminarById(@PathVariable(name = "seminarId")Long seminarId,@RequestBody Seminar seminar){
        seminar.setId(seminarId);
        return seminarService.updateSeminar(seminar);
    }

    /**
     * 更新：修改班级讨论课
     * @param seminarId
     * @param klassId
     * @param klassSeminar
     * @return
     */
    @PutMapping(value = "{seminarId}/class/{classId}")
    public Long updateKlassSeminar(@PathVariable(name = "seminarId") Long seminarId, @PathVariable(name = "classId")Long klassId, @RequestBody KlassSeminar klassSeminar){
        klassSeminar.setKlassId(klassId);
        klassSeminar.setSeminarId(seminarId);
        return seminarService.updateKlassSeminar(klassSeminar);
    }

    /**
     * 删除：seminar
     * @param seminarId
     * @return
     */
    @DeleteMapping(value = "/{seminarId}")
    public Long deleteSeminarById(@PathVariable(name = "seminarId")Long seminarId){
        return seminarService.deleteSeminarById(seminarId);
    }

    /**
     * 查询：班级讨论课
     * @param klassId
     * @param seminarId
     * @return
     */
    @GetMapping(value = "/{seminarId}/class/{classId}")
    public KlassSeminar getKlassSeminarByKlassIdAndSeminarId(@PathVariable(name = "classId")Long klassId,@PathVariable(name = "seminarId")Long seminarId){
        return seminarService.getKlassSeminarByKlassIdAndSeminarId(klassId,seminarId);
    }

    /**
     * 更新：设置讨论课轮次
     * @param seminarId
     * @param roundId
     * @return
     */
    @PutMapping(value = "/{seminarId}/round")
    public Long updateSeminarRoundId(@PathVariable(name = "seminarId")Long seminarId,@RequestBody Long roundId){
        return seminarService.updateSeminarRoundId(roundId,seminarId);
    }

    /**
     * 更新：设置班级讨论课状态
     * @param seminarId
     * @param klassSeminar
     * @return
     */
    @PutMapping(value = "/{seminarId}/status")
    public Long updateSeminarStatus(@PathVariable(name = "seminarId")Long seminarId,@RequestBody KlassSeminar klassSeminar){
        klassSeminar.setSeminarId(seminarId);
        return seminarService.updateSeminarStatus(klassSeminar);
    }

    /**
     * 查询：小组讨论课成绩
     * @param teamId
     * @param seminarId
     * @return
     */
    @GetMapping(value = "/{seminarId}/team/{teamId}/seminarscore")
    public Team getTeamSeminarScore(@PathVariable(name = "teamId")Long teamId,@PathVariable(name = "seminarId")Long seminarId){
        return seminarService.getTeamSeminarSocre(teamId,seminarId);
    }

    /**
     * 更新：团队讨论课成绩
     * @param teamId
     * @param seminarId
     * @param score
     * @return
     */
    @PutMapping(value = "/{seminarId}/team/{teamId}/seminarscore")
    public Long updateTeamSeminarScore(@PathVariable(name = "teamId")Long teamId,@PathVariable(name = "seminarId")Long seminarId,@RequestBody Score score){
        score.setTeamId(teamId);
        return seminarService.updateTeamSeminarScore(score,seminarId);
    }

    /**
     * 更新展示成绩
     * @param klassSeminarId
     * @param teamId
     * @param presentationScore
     * @return
     */
    @PutMapping(value = "/{klassseminarId}/team/{teamId}/presentationscore")
    public Long updateTeamSeminarPresentationScore(@PathVariable(name = "klassseminarId")Long klassSeminarId,@PathVariable(name = "teamId")Long teamId,@RequestBody Score presentationScore){
        presentationScore.setSeminarOrRoundId(klassSeminarId);
        presentationScore.setTeamId(teamId);
        return seminarService.updateTeamSeminarPresentationScore(presentationScore);
    }

    /**
     * 查询：一个班级的一次讨论课成绩
     * @param klassId
     * @param seminarId
     * @return
     */
    @GetMapping(value = "/{seminarId}/class/{classId}/seminarscore")
    public ArrayList<Team>getSeminarScore(@PathVariable(name = "classId")Long klassId, @PathVariable(name = "seminarId")Long seminarId){
        return seminarService.getSeminarScore(klassId,seminarId);
    }

    /**
     * 批量下载讨论课ppt
     * @param request
     * @param response
     * @param seminarId
     * @param classId
     * @return
     * @throws IOException
     */
    @GetMapping(value="/{seminarId}/class/{classId}/ppt")
    public String getAllAttendancePpt(HttpServletRequest request, HttpServletResponse response, @PathVariable(value="seminarId")Long seminarId, @PathVariable(value="classId")Long classId)throws IOException
    {
        Long klassSeminarId=seminarService.getClassSeminarIdBySeminarIdAndClassId(classId,seminarId);
        ArrayList<String> ppts=seminarService.getAllSeminarPptByClassSeminarId(klassSeminarId);
        FileUploudConfig fileUploudConfig=new FileUploudConfig();
        return fileUploudConfig.downLoadAllFile(request,response,ppts);
    }

    /**
     * 批量下载讨论课报告
     * @param request
     * @param response
     * @param seminarId
     * @param classId
     * @return
     * @throws IOException
     */
    @GetMapping(value="/{seminarId}/class/{classId}/report")
    public String getAllAttendanceReport(HttpServletRequest request, HttpServletResponse response, @PathVariable(value="seminarId")Long seminarId, @PathVariable(value="classId")Long classId)throws IOException
    {
         Long klassSeminarId=seminarService.getClassSeminarIdBySeminarIdAndClassId(classId,seminarId);
         ArrayList<String> reportNames=seminarService.getAllSeminarReportByClassSeminarId(klassSeminarId);
        FileUploudConfig fileUploudConfig=new FileUploudConfig();
         return fileUploudConfig.downLoadAllFile(request,response,reportNames);

    }

    /**
     * 查询：某组某次讨论课展示
     * @param klassSeminarId
     * @param teamOrder
     * @return
     */
    @GetMapping(value = "/{klassseminarId}/team/{teamorder}/attendance")
    public Attendance getAtteandanceByTeamOrderAndKlassSeminarId(@PathVariable(name = "klassseminarId")Long klassSeminarId,@PathVariable(name = "teamorder")int teamOrder){
        return seminarService.getAtteandanceByTeamOrderAndKlassSeminarId(klassSeminarId,teamOrder);
    }

    /**
     * 获得讨论课报名信息
     * @param seminarId
     * @param classId
     * @return
     */
    @GetMapping(value="/{seminarId}/class/{classId}/attendance")
    public ArrayList<Attendance> getAllAttendance(@PathVariable(value="seminarId")Long seminarId,@PathVariable(value="classId")Long classId)
    {
       ArrayList<Attendance> attendances= seminarService.getAllAttendance(seminarId,classId);

        return attendances;
    }

    /**
     * 报名讨论课
     * @param seminarId
     * @param classId
     * @param attendance
     * @return
     */
    @PostMapping(value="/{seminarId}/class/{classId}/attendance")
    public Long updateAttendanceByClassSeminarId(@PathVariable(value="seminarId")Long seminarId,@PathVariable(value="classId")Long classId,@RequestBody Attendance attendance)
    {
        System.out.println(attendance.getTeamId());
        return seminarService.updateAttendanceByClassSeminarId(seminarId,classId,attendance.getTeamId(),attendance.getTeamOrder());
    }

    /**
     * 修改书面报告成绩
     * @param seminarId
     * @param classId
     * @param scores
     * @return
     */
    @PutMapping(value="/{seminarId}/class/{classId}/reportScore")
    public Long updateReportScoreByTeamId(@PathVariable(value="seminarId")Long seminarId,@PathVariable(value="classId")Long classId,@RequestBody ArrayList<Score> scores)
    {
        for(Score score:scores) {
            seminarService.updateReportScoreByTeamId(seminarId, classId, score.getTeamId(), score.getReportScore());
        }
        return 1L;
    }
}
