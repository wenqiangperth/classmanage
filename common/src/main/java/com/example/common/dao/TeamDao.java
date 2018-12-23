package com.example.common.dao;

import com.example.common.entity.*;
import com.example.common.mapper.CourseMapper;
import com.example.common.mapper.TeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * @author perth
 * @ClassName TeamDao
 * @Description 处理组队相关事务
 * @Date 2018/12/19 10:36
 * @Version 1.0
 **/

@Repository
public class TeamDao {
    @Autowired
    private TeamMapper teamMapper;

    @Autowired
    private CourseMapper courseMapper;

    /**
     * 创建小组，同时在klass_student表设置了teamID
     * @param team
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Long addTeam(Team team){
        Long i=teamMapper.insertTeam(team.getKlassId(),team.getCourseId(),team.getLeaderId(),team.getTeamName(),team.getTeamSerial(),team.getStatus());
        if(i<=0){return i;}
        ArrayList<Student> students=team.getStudents();
        Long teamId=teamMapper.selectTeamByCourseIdAndLeaderId(team.getKlassId(),team.getCourseId(),team.getLeaderId()).getId();
        for (Student student:students
             ) {
          Long j=teamMapper.updateKlassStudent(team.getKlassId(),student.getId(),teamId);
            if(j<=0){return j;}
        }
        return teamId;
    }

    /**
     * 获取课程下所有讨论课
     * @param klassId
     * @param courseId
     * @return
     */
    public ArrayList<Team>getAllTeamsByCourseId(Long klassId,Long courseId){
        return teamMapper.selectTeamsByCourseIdAndClassId(klassId,courseId);
    }

    /**
     * 根据ID获取team包括组员
     * @param teamId
     * @return
     */
    public Team getTeamById(Long teamId){
        ArrayList<Student>students=teamMapper.selectStudentsByTeamId(teamId);
        Team team=teamMapper.selectTeamById(teamId);
        team.setStudents(students);
        return team;
    }

    /**
     * 根据ID删除小组，且删除klass_student表中的关系
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Long deleteTeamById(Long id){
        Team team=getTeamById(id);
        ArrayList<Student>students=team.getStudents();
        for (Student student:students
             ) {
            teamMapper.updateKlassStudent(team.getKlassId(),student.getId(),null);
        }
        return teamMapper.deleteTeamById(id);
    }

    /**
     * 设置某个学生属于某个team
     * @param teamId
     * @param studentId
     * @return
     */
    public Long addTeamMemberById(Long teamId,Long studentId){
        Team team=teamMapper.selectTeamById(teamId);
        return teamMapper.updateKlassStudent(team.getKlassId(),studentId,teamId);
    }

    public Long createTeamValidApplication(TeamValidApplication teamValidApplication){
        Course course=courseMapper.selectCourseByTeamId(teamValidApplication.getTeamId());
        teamValidApplication.setTeacherId(course.getTeacherId());
        return teamMapper.insertTeamValidApplication(teamValidApplication);
    }

    /**
     * 删除klass_student表中学生与某组的关系
     * @param teamId
     * @param studentId
     * @return
     */
    public Long removeTeamMember(Long teamId,Long studentId){
        Team team=teamMapper.selectTeamById(teamId);
        return teamMapper.updateKlassStudent(team.getKlassId(),studentId,null);
    }

    /**
     * 查找课程组队策略总表
     * @param courseId
     * @return
     */
    public TeamStrategy getTeamStrategy(Long courseId){
        return teamMapper.getTeamStrategy(courseId);
    }

    /**
     * 查找冲突课程
     * @param id
     * @return
     */
    public ArrayList<Long>getConflictCourseId(Long id){
        return teamMapper.getConflictCourseId(id);
    }

    /**
     * 查找小组人数量限制
     * @param id
     * @return
     */
    public MemberLimitStrategy getMemberLimitStrategy(Long id){
        return teamMapper.getMemberLimitStrategy(id);
    }

    /**
     * 查找小组内选择某一课程的人数限制
     * @param id
     * @return
     */
    public MemberLimitStrategy getCourseMemberLimit(Long id){
        return teamMapper.getCourseMemberLimit(id);
    }

    /**
     * 组队与策略
     * @param id
     * @return
     */
    public TeamAndOrStrategy getTeamAndStrategy(Long id){
        return teamMapper.getTeamAndStrategy(id);
    }

    /**
     * 组队或策略
     * @param id
     * @return
     */
    public TeamAndOrStrategy getTeamOrStrategy(Long id){
        return teamMapper.getTeamOrStrategy(id);
    }
}
