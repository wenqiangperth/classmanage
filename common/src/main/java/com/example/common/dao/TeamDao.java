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
     * 创建小组，同时在klass_student表设置了teamID->team和student之间的关系
     * @param team
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Long addTeam(Team team){
        Long i=teamMapper.insertTeam(team);
        if(i<=0){return i;}
        Student leaderStudent=new Student();
        leaderStudent.setId(team.getLeaderId());
        ArrayList<Student> students=team.getStudents();
        if(students==null){
            students=new ArrayList<>();
        }
        students.add(leaderStudent);
        Long teamId=team.getId();
        for (Student student:students) {
            if(student!=null&&student.getId()!=null) {
                Long j = teamMapper.insertTeamStudent(teamId, student.getId());
                if (j <= 0) {
                    return j;
                }
            }
        }
        teamMapper.insertKlassTeam(team.getKlassId(),teamId);
        return teamId;
    }

    /**
     * 课程下班级team
     * @param klassId
     * @param courseId
     * @return
     */
    public ArrayList<Team>getAllTeamsByCourseId(Long klassId,Long courseId){
        return teamMapper.selectTeamsByCourseIdAndClassId(klassId,courseId);
    }
    public ArrayList<Long> getAllTeamIdByClassId(Long klassId)

    {
        return teamMapper.getAllTeamIdByClassId(klassId);
    }


    public Team getTeamById(Long teamId,Long courseId){
        ArrayList<Student>students=teamMapper.selectStudentsByTeamId(teamId);
        ArrayList<Student> selectStudents=new ArrayList<>();
        for (Student tempStudent:students)
        {
            if(courseMapper.isSelectCourse(courseId,tempStudent.getId())==1)
            {
                selectStudents.add(tempStudent);
            }
        }
        Team team=teamMapper.selectTeamById(teamId);
        if(team==null){
            return null;
        }
        team.setStudents(selectStudents);
        return team;
    }

    public Team getTeamInfoById(Long teamId)
    {
        return teamMapper.selectTeamById(teamId);
    }

    /**
     * 根据ID获取team包括组员
     * @param teamId
     * @return
     */
    public Team getTeamByTeamId(Long teamId)
    {
        ArrayList<Student>students=teamMapper.selectStudentsByTeamId(teamId);
        Team team=teamMapper.selectTeamById(teamId);
        if(team==null){
            return null;
        }
        team.setStudents(students);
        return team;
    }

    public ArrayList<Team>getTeamsByCourseId(Long courseId){
        return teamMapper.selectTeamsByCourseId(courseId);
    }

    /**
     * 根据ID删除小组，且删除klass_student表中的关系
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Long deleteTeamById(Long id){
        Team team=getTeamByTeamId(id);
        ArrayList<Student>students=team.getStudents();
        teamMapper.deleteKlassTeam(team.getKlassId(),team.getId());
        for (Student student:students
             ) {
            teamMapper.deleteTeamStudentByTeamId(team.getId(),student.getId());
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
        if(team==null){
            return 0L;
        }
        return teamMapper.insertTeamStudent(teamId,studentId);
    }

    public Long createTeamValidApplication(TeamValidApplication teamValidApplication){
        Course course=courseMapper.getCourseById(teamValidApplication.getCourseId());
        teamValidApplication.setTeacherId(course.getTeacherId());
        teamMapper.updateTeamStatus(teamValidApplication.getTeamId(),2L);
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
        if(team==null){
            return 0L;
        }
        return teamMapper.deleteTeamStudentByTeamIdAndStudentId(teamId,studentId);
    }



    /**
     * 设置队伍状态
     * @param teamId
     * @return
     */
    public Long updateTeamStatus(Long teamId,Long status){
        return teamMapper.updateTeamStatus(teamId,status);
    }

    /**
     * 更新：team的name
     * @param team
     * @return
     */
    public Long updateTeamName(Team team){
        return teamMapper.updateTeamName(team);
    }


    public Long insertKlassTeam(Long klassId,Long teamId)
    {
        return teamMapper.insertKlassTeam(klassId,teamId);
    }

    public Long insertTeamStudent(Long teamId,Long studentId)
    {
        return teamMapper.insertTeamStudent(teamId,studentId);
    }
}
