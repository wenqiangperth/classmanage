package com.example.common.mapper;

import com.example.common.entity.Question;
import org.apache.commons.collections4.QueueUtils;
import org.apache.ibatis.annotations.*;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @ClassName QuestionMapper
 * @Description
 * @Author perth
 * @Date 2018/12/17 0017 下午 7:57
 * @Version 1.0
 **/

@Mapper
@Repository
public interface QuestionMapper {


    /**
     * 获取某展示当前提问人数
     * @param klassSeminarId
     * @param attendanceId
     * @return
     */
    @Select("select count(id) from question where klass_seminar_id=#{klassSeminarId} and attendance_id=#{attendanceId} and is_selected=#{isSelected}")
    public int selectQuestionNumByKlassSeminarIdAndAttendanceId(@Param("klassSeminarId")Long klassSeminarId,@Param("attendanceId")Long attendanceId,@Param("isSelected") int isSelected);

    /**
     * 查询，某展示下，某人 的提问
     * @param attendanceId
     * @param studentId
     * @param isSelected
     * @return
     */
    @Select("select * from question where attendance_id=#{attendanceId} and student_id=#{studentId} and is_selected=#{isSelected}")
    @ResultMap(value = "questionMap")
    public Question selectQuestionByAttendanceIdAndStudentId(@Param("attendanceId")Long attendanceId, @Param("studentId")Long studentId,@Param("isSelected")int isSelected);

    /**
     * 插入：question
     * @param question
     * @return
     */
    @Insert("insert into question (klass_seminar_id,attendance_id,team_id,student_id,is_selected) values (#{klassSeminarId},#{attendanceId},#{teamId},#{studentId},#{isSelected})")
    public Long insertQuestion(Question question);
    /**
     * 查询：attenanceID->questions
     * @param attendanceId
     * @return
     */
    @Select("select * from question where attendance_id=#{attendanceId} and is_selected=0")
    @Results(id = "questionMap",value = {
            @Result(property = "klassSeminarId",column = "klass_seminar_id"),
            @Result(property = "teamId",column = "team_id"),
            @Result(property = "attendanceId",column = "attendance_id"),
            @Result(property = "studentId",column = "student_id"),
            @Result(property = "isSelected",column = "is_selected"),
            @Result(property = "score",column = "score")
    })
    public ArrayList<Question>getAllQuestionByAttendanceId(@Param("attendanceId")Long attendanceId);

    /**
     * 查询：某节讨论课下所有question
     * @param klassSeminarId
     * @return
     */
    @Select("select * from question where klass_seminar_id=#{klassSeminarId}")
    @ResultMap(value = "questionMap")
    public ArrayList<Question>getAllQuestionByKlassSeminarId(@Param("klassSeminarId")Long klassSeminarId);

    /**
     * 更新：问题
     * @param id
     * @return
     */
    @Update("update question set is_selected=1 where id=#{id}")
    public Long updateQuestionIsSelected(@Param("id")Long id);

    /**
     * 删除：所有未被选中的提问
     * @param klassSeminarId
     * @return
     */
    @Delete("delete from question where klass_seminar_id=#{klassSeminarId} and is_selected=0")
    public Long deleteQuestion(@Param("klassSeminarId")Long klassSeminarId);

    /**
     * 设置分数
     * @param score
     * @param id
     * @return
     */
    @Update("update question set score=#{score} where id=#{id}")
    public Long updateQuestionScore(@Param("score")double score,@Param("id")Long id);


}
