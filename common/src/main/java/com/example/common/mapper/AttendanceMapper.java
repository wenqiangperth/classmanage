package com.example.common.mapper;

import com.example.common.entity.Attendance;
import com.example.common.entity.Question;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;

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
     * 查询当前正在展示的attendance
     * @param klassSeminarId
     * @return
     */
    @Select("select * from attendance where klass_seminar_id=#{klassSeminarId} and is_present=1")
    @ResultMap(value = "attendanceMap")
    public Attendance selectIsPresentAttendanceByKlassSeminarId(@Param("klassSeminarId")Long klassSeminarId);


    /**
     * 查询：id->attendance
     * @param id
     * @return
     */
    @Select("select * from attendance where id=#{id}")
    @ResultMap(value = "attendanceMap")
    public Attendance selectAttendanceById(@Param("id")Long id);

    /**
     * 设置attendance的状态
     * @param attendance
     * @return
     */
    @Update("update attendance set is_present=#{isPresent} where id=#{id}")
    public Long updateAttendanceIsPresent(Attendance attendance);

    /**
     * 添加report存储路径
     * @param fileName
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
     * @param fileName
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
    @Select("select ppt_name from attendance where klass_seminar_id=#{classSeminarId} and ppt_name is not null")
    public ArrayList<String> getAllSeminarPptByClassSeminarId(@Param(value="classSeminarId") Long classSeminarId);

    /**
     * 获得该次讨论课所有展示reportName
     * @param classSeminarId
     * @return
     */
    @Select("select report_name from attendance where klass_seminar_id=#{classSeminarId} and report_name is not null")
    public ArrayList<String> getAllSeminarReportByClassSeminarId(@Param(value="classSeminarId") Long classSeminarId);

    /**
     * 更新讨论课次序
     * @param teamOrder
     * @param attendanceId
     * @return
     */
    @Update("update attendance set team_order=#{teamOrder} where id=#{attendanceId}")
    public Long updateAttendanceInfo(@Param(value="teamOrder")int teamOrder,@Param(value="attendanceId")Long attendanceId);

    /**
     * 根据id删除attendance表的记录
     * @param attendanceId
     * @return
     */
    @Delete("delete from attendance where id=#{attendanceId}")
    public Long deleteAttendanceByAttendanceId(@Param(value="attendanceId")Long attendanceId);

    /**
     * 查询：某组某次讨论课展示
     * @param klassSeminarId
     * @param teamOrder
     * @return
     */
    @Select("select * from attendance where klass_seminar_id=#{klassSeminarId} and team_order=#{teamOrder}")
    @ResultMap(value = "attendanceMap")
    public Attendance getAtteandanceByTeamOrderAndKlassSeminarId(@Param("klassSeminarId")Long klassSeminarId,@Param("teamOrder")int teamOrder);

    /**
     * 通过klass_seminar_id获得attendance
     * @param klassSeminarId
     * @return
     */
    @Select("select * from attendance where klass_seminar_id=#{klassSeminarId}")
    @Results(id = "attendanceMap",value = {
            @Result(property = "klassSeminarId",column = "klass_seminar_id"),
            @Result(property = "teamId",column = "team_id"),
            @Result(property = "teamOrder",column = "team_order"),
            @Result(property = "isPresent",column = "is_present"),
            @Result(property = "reportName",column = "report_name"),
            @Result(property = "reportUrl",column = "report_url"),
            @Result(property = "pptName",column = "ppt_name"),
            @Result(property = "pptUrl",column = "ppt_url")
    })
    public ArrayList<Attendance> getAllAttendanceByKlassSeminarId(@Param(value="klassSeminarId")Long klassSeminarId);

    /**
     * 在attendance插入数据
     * @param teamId
     * @param teamOrder
     * @param classSeminarId
     * @param isPresent
     * @return
     */
    @Insert("insert into attendance(team_id,team_order,klass_seminar_id,is_present) values(#{teamId},#{teamOrder},#{classSeminarId},#{isPresent})")
    public Long insertAttendanceByClassSeminarId(@Param(value="teamId")Long teamId,@Param(value="teamOrder")int teamOrder,@Param(value="classSeminarId")Long classSeminarId,@Param("isPresent")int isPresent);
}
