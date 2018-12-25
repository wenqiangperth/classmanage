package com.example.common.dao;

import com.example.common.entity.Course;
import com.example.common.entity.MemberLimitStrategy;
import com.example.common.entity.Student;
import com.example.common.entity.Team;
import com.example.common.mapper.CourseMemberLimitMapper;
import com.example.common.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author perth
 * @ClassName CourseMemberLimitStrategyDao
 * @Description TODO
 * @Date 2018/12/25 23:49
 * @Version 1.0
 **/
@Repository
public class CourseMemberLimitStrategyDao {
    @Autowired
    private CourseMemberLimitMapper courseMemberLimitMapper;

    @Autowired
    private StudentMapper studentMapper;

    public boolean isTeamValid(Team team, Long strategyId){
        MemberLimitStrategy courseMemberLimitStrategy=courseMemberLimitMapper.selectCourseMemberLimitStrategyById(strategyId);
        if(courseMemberLimitStrategy==null){
            return true;
        }
        ArrayList<Student> students=team.getStudents();
        int count=0;
        for (Student student:students
        ) {
            ArrayList<Course>courses=studentMapper.getAllCoursesByStundetId(student.getId());
            for (Course course:courses
            ) {
                if(course.getId()==courseMemberLimitStrategy.getCourseId()){
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

}
