package com.example.common.dao;

import com.example.common.entity.Course;
import com.example.common.entity.Student;
import com.example.common.entity.Team;
import com.example.common.mapper.ConflictCourseStrategyMapper;
import com.example.common.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author perth
 * @ClassName ConflictCourseStrategyDao
 * @Description TODO
 * @Date 2018/12/25 20:38
 * @Version 1.0
 **/
@Repository
public class ConflictCourseStrategyDao{
    @Autowired
    private ConflictCourseStrategyMapper conflictCourseStrategyMapper;

    @Autowired
    private StudentMapper studentMapper;


    public boolean isTeamValid(Team team, Long strategyId){

        ArrayList<Long> courseIds=conflictCourseStrategyMapper.selectConflictCourseStrategyById(strategyId);
        if(courseIds==null){
            return true;
        }
        ArrayList<Student>students=team.getStudents();
        for (Student student:students
        ) {
            ArrayList<Course>courses=studentMapper.getAllCoursesByStundetId(student.getId());
            int count=0;
            for (Course course:courses
            ) {
                for (Long courseId:courseIds
                ) {
                    if(courseId==course.getId()){
                        count++;
                    }
                }
            }
            if(count>=2){
                return false;
            }
        }
        return true;
    }

}
