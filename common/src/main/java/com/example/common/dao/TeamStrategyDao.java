package com.example.common.dao;

import com.example.common.entity.*;
import com.example.common.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author perth
 * @ClassName TeamStrategyDao
 * @Description TODO
 * @Date 2018/12/25 23:53
 * @Version 1.0
 **/
@Repository
public class TeamStrategyDao {
    @Autowired
    private TeamStrategyMapper teamStrategyMapper;
    @Autowired
    private TeamMapper teamMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private CourseMemberLimitMapper courseMemberLimitMapper;
    @Autowired
    private MemberLimitStrategyMapper memberLimitStrategyMapper;
    @Autowired
    private ConflictCourseStrategyMapper conflictCourseStrategyMapper;
    @Autowired
    private TeamAndStrategyMapper teamAndStrategyMapper;

    @Autowired
    private TeamDao teamDao;

    @Autowired
    private TeamOrStrategyMapper teamOrStrategyMapper;



    public boolean isTeamValid(Long teamId){
        Team team=teamDao.getTeamByTeamId(teamId);
        ArrayList<TeamStrategy>teamStrategies=teamStrategyMapper.selectTeamStrategyByCourseId(team.getCourseId());
        if(teamStrategies!=null) {
            for (TeamStrategy teamStrategy : teamStrategies) {
                if (!isStrategyOK(team, teamStrategy.getStrategyName(), teamStrategy.getStrategyId())) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isStrategyOK(Team team,String strategyName,Long id){
        boolean isok=false;
        switch (strategyName){
            case "TeamAndStrategy":isok=isTeamAndStrategy(team,id);break;
            case "TeamOrStrategy":isok=isTeamOrStrategy(team,id);break;
            case "CourseMemberLimitStrategy":isok=isCourseMemberLimitStrategy(team,id);break;
            case "ConflictCourseStrategy":isok=isConflictCourseStrategy(team,id);break;
            case "MemberLimitStrategy":isok=isMemberLimitStrategy(team,id);break;
            default:isok=true;
        }
        return isok;
    }

    public boolean isCourseMemberLimitStrategy(Team team,Long id){
        MemberLimitStrategy courseMemberLimitStrategy=courseMemberLimitMapper.selectCourseMemberLimitStrategyById(id);
        if(courseMemberLimitStrategy==null){
            return true;
        }
        ArrayList<Student> students=team.getStudents();
        int count=0;
        for (Student student:students) {
            ArrayList<Course>courses=studentMapper.getAllCoursesByStundetId(student.getId());
            for (Course course:courses) {
                if(course.getId().equals(courseMemberLimitStrategy.getCourseId())){
                    count++;
                }
            }
        }
        if(courseMemberLimitStrategy.getMinMember()<=count){
            if(courseMemberLimitStrategy.getMaxMember()==0){
                return true;
            }else if(courseMemberLimitStrategy.getMaxMember()>=count){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    public boolean isMemberLimitStrategy(Team team,Long id){
        MemberLimitStrategy memberLimitStrategy=memberLimitStrategyMapper.selectMemberLimitStrategyById(id);
        if(memberLimitStrategy==null){
            return true;
        }
        int num=team.getStudents().size();
        if(memberLimitStrategy.getMinMember()<=num){
            if(memberLimitStrategy.getMaxMember()<=0){
                return true;
            }else if(memberLimitStrategy.getMaxMember()>=num){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }

    }

    public boolean isConflictCourseStrategy(Team team, Long id){
        ArrayList<Long> courseIds=conflictCourseStrategyMapper.selectConflictCourseStrategyById(id);
        if(courseIds==null){
            return true;
        }
        ArrayList<Student>students=team.getStudents();
        int isOk=0;
        for (Long courseId:courseIds
        ) {
            int count=0;
            for (Student student:students
            ) {
                if(studentMapper.isSelectCourse(student.getId(),courseId)!=null) {
                    count=1;
                    break;
                }
                if(isOk==0&&count>0) {
                    isOk = 1;
                }
                if(isOk==1&&count>0) {
                    return false;
                }
            }

        }
        return true;
    }

    public boolean isTeamOrStrategy(Team team,Long id){
        ArrayList<TeamAndOrStrategy>teamOrStrategies=teamOrStrategyMapper.selectTeamOrStrategyById(id);
        if(teamOrStrategies==null){
            return true;
        }
        for (TeamAndOrStrategy teamOrStrategy:teamOrStrategies) {
            if(isStrategyOK(team,teamOrStrategy.getStrategyName(),teamOrStrategy.getStrategyId())){
                return true;
            }
        }
        return false;

    }

    public boolean isTeamAndStrategy(Team team,Long id){
        ArrayList<TeamAndOrStrategy> teamAndStrategies=teamAndStrategyMapper.selectTeamAndStrategyById(id);
        if(teamAndStrategies==null){
            return true;
        }
        for (TeamAndOrStrategy teamAndStrategy:teamAndStrategies) {
            if(!isStrategyOK(team,teamAndStrategy.getStrategyName(),teamAndStrategy.getStrategyId())){
                return false;
            }
        }
        return true;
    }
//    public boolean isTeamValid(Long teamId){
//        Team team=teamMapper.selectTeamById(teamId);
//        ArrayList<TeamStrategy>teamStrategies=teamStrategyMapper.selectTeamStrategyByCourseId(team.getCourseId());
//        for (TeamStrategy teamStrategy:teamStrategies
//             ) {
//            if(!isTeamStrategyValid(team,teamStrategy.getStrategyName(),teamStrategy.getStrategyId())){
//                return false;
//            }
//        }
//        return true;
//    }
//
//    public boolean isTeamStrategyValid(Team team,String strategyName,Long strategyId){
//        boolean isok=false;
//        switch (strategyName){
//            case "TeamAndStrategy":isok=teamAndStrategyDao.isTeamValid(team,strategyId);break;
//            case "TeamOrStrategy":isok=teamOrStrategyDao.isTeamValid(team,strategyId);break;
//            case "CourseMemberLimitStrategy":isok=courseMemberLimitStrategyDao.isTeamValid(team,strategyId);break;
//            case "ConflictCourseStrategy":isok=conflictCourseStrategyDao.isTeamValid(team,strategyId);break;
//            case "MemberLimitStrategy":isok=memberLimitStrategyDao.isTeamValid(team,strategyId);break;
//        }
//        return isok;
//    }
}
