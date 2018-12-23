package com.example.common.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @ClassName AttendanceMapper
 * @Description
 * @Author perth
 * @Date 2018/12/17 0017 下午 7:55
 * @Version 1.0
 **/

@Mapper
@Repository
public interface AttendanceMapper {
    /**
     * 添加report存储路径
     * @param filePath
     * @param attendanceId
     * @return
     */
    @Update("update attendance set report_name=#{fileName},report_url=#{filePath} where id=#{attendanceId}")
    public Long setAttendanceReport(@Param(value="fileName")String fileName,@Param(value="filePath")String filePath,@Param(value="attendanceId") Long attendanceId);

    /**
     * 获得report文件名
     * @param attendanceId
     * @return
     */
    @Select("select report_name from attendance where id=#{attendanceId}")
    public String getFileNameById(@Param(value="attendanceId")Long attendanceId);

    /**
     * 添加ppt存储路径
     * @param filePath
     * @param attendanceId
     * @return
     */
    @Update("update attendance set ppt_name=#{fileName},ppt_url=#{filePath} where id=#{attendanceId}")
    public Long setAttendancePpt(@Param(value="fileName")String fileName,@Param(value="filePath")String filePath,@Param(value="attendanceId") Long attendanceId);

    /**
     * 获得ppt文件名
     * @param attendanceId
     * @return
     */
    @Select("select ppt_name from attendance where id=#{attendanceId}")
    public String getPptNameById(@Param(value="attendanceId")Long attendanceId);

    /**
     * 获得该次讨论课所有展示pptName
     * @param classSeminarId
     * @return
     */
    @Select("select ppt_name from attendance where klass_seminar_id=#{classSeminarId}")
    public ArrayList<String> getAllSeminarPptByClassSeminarId(@Param(value="classSeminarId") Long classSeminarId);

    /**
     * 获得该次讨论课所有展示reportName
     * @param classSeminarId
     * @return
     */
    @Select("select ppt_name from attendance where klass_seminar_id=#{classSeminarId}")
    public ArrayList<String> getAllSeminarReportByClassSeminarId(@Param(value="classSeminarId") Long classSeminarId);
}
