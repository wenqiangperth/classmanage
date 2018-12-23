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


    /**
     * 组队是否合法
     * @param teamId
     * @return
     */
    public boolean isTeamValid(Long teamId){
        Team team=teamDao.getTeamById(teamId);
        TeamStrategy teamStrategy=teamDao.getTeamStrategy(team.getCourseId());
        if(teamStrategy==null){
            return true;
        }else {
            return isStrategyOK(teamStrategy.getStrategyName(), teamStrategy.getStrategyId(), team);
        }
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
        TeamAndOrStrategy teamOrStrategy=teamDao.getTeamOrStrategy(strategyId);
        if(teamOrStrategy==null){
            return true;
        }
        if(isStrategyOK(teamOrStrategy.getStrategyName1(),teamOrStrategy.getStrategyId1(),team)
        ||isStrategyOK(teamOrStrategy.getStrategyName2(),teamOrStrategy.getStrategyId2(),team)){
            return true;
        }
        return false;
    }

    /**
     * 组队与策略是否符合
     * @param strategyId
     * @param team
     * @return
     */
    public boolean isTeamAndStrategy(Long strategyId,Team team){
        TeamAndOrStrategy teamAndStrategy=teamDao.getTeamAndStrategy(strategyId);
        if(teamAndStrategy==null){
            return true;
        }
        if(isStrategyOK(teamAndStrategy.getStrategyName1(),teamAndStrategy.getStrategyId1(),team)
                &&isStrategyOK(teamAndStrategy.getStrategyName2(),teamAndStrategy.getStrategyId2(),team)){
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
        if(memberLimitStrategy==null){
            return true;
        }
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
        if(memberLimitStrategy==null){
            return true;
        }
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
        if(conflictCourseStrstegy==null){
            return true;
        }
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
        if(isTeamValid(team.getId())){
            team.setStatus(1);
        }else{
            team.setStatus(0);
        }
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
     * 增加组员，设置分组是否合法
     * @param teamId
     * @param studentId
     * @return
     */
    public Long addTeamMemberById(Long teamId,Long studentId){
        Long i=teamDao.addTeamMemberById(teamId,studentId);
        if(isTeamValid(teamId)){
            teamDao.updateTeamStatus(teamId,1);
        }else{
            teamDao.updateTeamStatus(teamId,0);
        }
        return i;
    }

    /**
     * 删除组员,设置分组是否合法
     * @param teamId
     * @param studentId
     * @return
     */
    public Long removeTeamMember(Long teamId,Long studentId){
        Long i=teamDao.removeTeamMember(teamId,studentId);
        if(isTeamValid(teamId)){
            teamDao.updateTeamStatus(teamId,1);
        }else{
            teamDao.updateTeamStatus(teamId,0);
        }
        return i;
    }

    /**
     * 创建特殊组队申请
     * @param teamValidApplication
     * @return
     */
    public Long createTeamValisApplication(TeamValidApplication teamValidApplication){
        return teamDao.createTeamValidApplication(teamValidApplication);
    }

    /**
     * 同意特殊组队申请,设置status为1，合法
     * @param teamId
     * @return
     */
    public Long approveTeam(Long teamId){
        return teamDao.updateTeamStatus(teamId,1);
    }

    /**
     * 修改设置队伍name
     * @param team
     * @return
     */
    public Long updateTeamName(Team team){
        return teamDao.updateTeamName(team);
    }
}
