package com.example.user.service;

import com.example.common.dao.StudentDao;
import com.example.common.dao.TeamDao;
import com.example.common.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author perth
 * @ClassName TeamService
 * @Description 处理Team的事务
 * @Date 2018/12/17 19:08
 * @Version 1.0
 **/
@Service
public class TeamService {
    @Autowired
    private TeamDao teamDao;
    @Autowired
    private StudentDao studentDao;


    public boolean isTeamValid(Long teamId){
        Team team=teamDao.getTeamById(teamId);
        TeamStrategy teamStrategy=teamDao.getTeamStrategy(team.getCourseId());
        return isStrategyOK(teamStrategy.getStrategyName(),teamStrategy.getStrategyId(),team);
    }

    /**
     * 通过策略名，调用不同策略验证
     * @param strategyName
     * @param strategyId
     * @param team
     * @return
     */
    public boolean isStrategyOK(String strategyName,Long strategyId,Team team){
        if(strategyName.equals("conflict_course_strategy")){
            return isConflictCourseStrategy(strategyId,team);
        }else if(strategyName.equals("member_limit_strategy")){
            return isMemberLimit(strategyId,team);
        }else if(strategyName.equals("course_member_limit_strategy")){
            return isCourseMemberLimit(strategyId,team);
        }else if(strategyName.equals("team_and_strategy")){
            return isTeamAndStrategy(strategyId,team);
        }else if(strategyName.equals("team_or_strategy")){
            return isTeamOrStrategy(strategyId,team);
        }
        return true;
    }

    /**
     * 组队或策略是否符合
     * @param strategyId
     * @param team
     * @return
     */
    public boolean isTeamOrStrategy(Long strategyId,Team team){
        TeamAndOrStrategy teamAndOrStrategy=teamDao.getTeamOrStrategy(strategyId);
        if(isStrategyOK(teamAndOrStrategy.getStrategyName1(),teamAndOrStrategy.getStrategyId1(),team)
        ||isStrategyOK(teamAndOrStrategy.getStrategyName2(),teamAndOrStrategy.getStrategyId2(),team)){
            return true;
        }
        return false;
    }

    /**
     * 组队或策略是否符合
     * @param strategyId
     * @param team
     * @return
     */
    public boolean isTeamAndStrategy(Long strategyId,Team team){
        TeamAndOrStrategy teamAndOrStrategy=teamDao.getTeamAndStrategy(strategyId);
        if(isStrategyOK(teamAndOrStrategy.getStrategyName1(),teamAndOrStrategy.getStrategyId1(),team)
                &&isStrategyOK(teamAndOrStrategy.getStrategyName2(),teamAndOrStrategy.getStrategyId2(),team)){
            return true;
        }
        return false;
    }

    /**
     * 判断组内选择某一课程的人数是否超限
     * @param strategyId
     * @param team
     * @return
     */
    public boolean isCourseMemberLimit(Long strategyId,Team team){
        MemberLimitStrategy memberLimitStrategy=teamDao.getCourseMemberLimit(strategyId);
        ArrayList<Student>students=team.getStudents();
        int count=0;
        for (Student student:students
             ) {
            ArrayList<Course>courses=studentDao.getAllCoursesByStundetId(student.getId());
            for (Course course:courses
                 ) {
                if(course.getId()==memberLimitStrategy.getCourseId()){
                    count++;
                }
            }
        }
        if(count>=memberLimitStrategy.getMinMember()&&count<=memberLimitStrategy.getMaxMember()){
            return true;
        }
        return false;
    }

    /**
     * 判断组队人数限制
     * @param strategyId
     * @param team
     * @return
     */
    public boolean isMemberLimit(Long strategyId,Team team){
        MemberLimitStrategy memberLimitStrategy=teamDao.getMemberLimitStrategy(strategyId);
        int num=team.getStudents().size();
        if(num>=memberLimitStrategy.getMinMember()&&num<=memberLimitStrategy.getMaxMember()){
            return  true;
        }
        return false;
    }
    /**
     * 判断小组中成员是否符合冲突课程规则,true,符合
     * @param strategyId
     * @param team
     * @return
     */
    public boolean isConflictCourseStrategy(Long strategyId,Team team){
        ConflictCourseStrstegy conflictCourseStrstegy =teamDao.getConflictCourseId(strategyId);
        ArrayList<Student>students=team.getStudents();
        boolean courseId1=false,courseId2=false;
        for (Student student:students
             ) {
            ArrayList<Course> courses=studentDao.getAllCoursesByStundetId(student.getId());
            for (Course course:courses
                 ) {
                if(course.getId()==conflictCourseStrstegy.getCourseId1()){
                    courseId1=true;
                }else if(course.getId()==conflictCourseStrstegy.getCourseId2()){
                    courseId2=true;
                }
            }
        }
        if(courseId1&&courseId2) {
            return false;
        }else{
            return true;
        }
    }

    /**
     * 创建小组，设置小组序号
     * @param team
     * @return小组ID
     */
    public Long addTeam(Team team){
        ArrayList<Team> teams=teamDao.getAllTeamsByCourseId(team.getKlassId(),team.getCourseId());
        int teamSerial=1;
        for (Team team1:teams
             ) {
            if(teamSerial<team1.getTeamSerial()){teamSerial=team1.getTeamSerial();}
        }
        team.setTeamSerial(teamSerial);
        return teamDao.addTeam(team);
    }

    /**
     * 根据ID获取小组
     * @param teamId
     * @return
     */
    public Team getTeamById(Long teamId){
        return teamDao.getTeamById(teamId);
    }

    /**
     * 根据ID删除小组
     * @param id
     * @return
     */
    public Long deleteTeamById(Long id){
        return teamDao.deleteTeamById(id);
    }

    /**
     * 增加组员
     * @param teamId
     * @param studentId
     * @return
     */
    public Long addTeamMemberById(Long teamId,Long studentId){
        return teamDao.addTeamMemberById(teamId,studentId);
    }

    /**
     * 删除组员
     * @param teamId
     * @param studentId
     * @return
     */
    public Long removeTeamMember(Long teamId,Long studentId){
        return teamDao.removeTeamMember(teamId,studentId);
    }
}
