package com.example.user.controller;

import com.example.common.config.FileUploudConfig;
import com.example.common.entity.*;
import com.example.user.service.SeminarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        for(String ppt:ppts)
        {
            FileUploudConfig fileUploudConfig=new FileUploudConfig();
            String fileName=ppt;
            fileUploudConfig.downloadFile(request,response,fileName);
        }
        return "success";
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
         ArrayList<String> reportPaths=new ArrayList<>();
         for(String report:reportNames)
        {
              reportPaths.add("E:\\test\\"+report);
//            FileUploudConfig fileUploudConfig=new FileUploudConfig();
//            String fileName=report;
//            fileUploudConfig.downloadFile(request,response,fileName);
        }
//        return "success";
        /**
         * 下载
         * @param response
         */

        String directory = "E:\\test\\";
        File directoryFile=new File(directory);
        if(!directoryFile.isDirectory() && !directoryFile.exists()){
            directoryFile.mkdirs();
        }
        //设置最终输出zip文件的目录+文件名
        SimpleDateFormat formatter  = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
        String zipFileName = formatter.format(new Date())+".zip";
        String strZipPath = directory+"\\"+zipFileName;

        ZipOutputStream zipStream = null;
        FileInputStream zipSource = null;
        BufferedInputStream bufferStream = null;
        File zipFile = new File(strZipPath);
        try{
            //构造最终压缩包的输出流
            zipStream = new ZipOutputStream(new FileOutputStream(zipFile));
            for(String report:reportNames){
                //解码获取真实路径与文件名
                String realFileName = java.net.URLDecoder.decode(report,"UTF-8");
                String realFilePath = java.net.URLDecoder.decode("E:\\test\\"+report,"UTF-8");
                File file = new File(realFilePath);
                //TODO:未对文件不存在时进行操作，后期优化。
                if(file.exists())
                {
                    zipSource = new FileInputStream(file);//将需要压缩的文件格式化为输入流
                    /**
                     * 压缩条目不是具体独立的文件，而是压缩包文件列表中的列表项，称为条目，就像索引一样这里的name就是文件名,
                     * 文件名和之前的重复就会导致文件被覆盖
                     */
                    ZipEntry zipEntry = new ZipEntry(realFileName);//在压缩目录中文件的名字
                    zipStream.putNextEntry(zipEntry);//定位该压缩条目位置，开始写入文件到压缩包中
                    bufferStream = new BufferedInputStream(zipSource, 1024 * 10);
                    int read = 0;
                    byte[] buf = new byte[1024 * 10];
                    while((read = bufferStream.read(buf, 0, 1024 * 10)) != -1)
                    {
                        zipStream.write(buf, 0, read);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭流
            try {
                if(null != bufferStream) {bufferStream.close();}
                if(null != zipStream){
                    zipStream.flush();
                    zipStream.close();
                }
                if(null != zipSource) {zipSource.close();}
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //判断系统压缩文件是否存在：true-把该压缩文件通过流输出给客户端后删除该压缩文件  false-未处理
        if(zipFile.exists()){
            FileUploudConfig fileUploudConfig=new FileUploudConfig();
            fileUploudConfig.downFile(response,zipFileName,strZipPath);
            zipFile.delete();
        }
        return "success";
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
        return seminarService.getAllAttendance(seminarId,classId);
    }

    /**
     * 报名讨论课
     * @param seminarId
     * @param classId
     * @param teamId
     * @param teamOrder
     * @return
     */
    @PostMapping(value="/{seminarId}/class/{classId}/attendance")
    public Long updateAttendanceByClassSeminarId(@PathVariable(value="seminarId")Long seminarId,@PathVariable(value="classId")Long classId,@RequestParam(value="teamId") Long teamId,@RequestParam(value="teamOrder")int teamOrder)
    {
        return seminarService.updateAttendanceByClassSeminarId(seminarId,classId,teamId,teamOrder);
    }

    /**
     * 修改书面报告成绩
     * @param seminarId
     * @param classId
     * @param score
     * @return
     */
    @PutMapping(value="/{seminarId}/class/{classId}/reportScore")
    public Long updateReportScoreByTeamId(@PathVariable(value="seminarId")Long seminarId,@PathVariable(value="classId")Long classId,@RequestBody Score[] score)
    {
        for(int i=0;i<score.length;i++) {
            seminarService.updateReportScoreByTeamId(seminarId, classId, score[i].getTeamId(), score[i].getReportScore());
        }
        return 1L;
    }
}
