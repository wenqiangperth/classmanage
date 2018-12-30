package com.example.common.entity;

import lombok.Data;

import java.util.ArrayList;

/**
 * @ClassName CourseDTO
 * @Description
 * @Author perth
 * @Date 2018/12/27 0027 上午 10:35
 * @Version 1.0
 **/
@Data
public class CourseDTO {
    private Course course;
    private Long courseId;
    private Long minNum;
    private Long maxNum;
    private Long minCount;
    private Long maxCount;
    private ArrayList<CourseLimit> courseLimit;
    private Integer flag;
    private ArrayList<ArrayList<Long>> conflictCourseId;

//    public void setCourseCourseLimits(MemberLimitStrategy temp)
//    {
//            CourseLimit courseLimitTemp=new CourseLimit();
//            courseLimitTemp.setCourseId(temp.getCourseId());
//            courseLimitTemp.setTeamMaxCount(temp.getMaxMember());
//            courseLimitTemp.setTeamMinCount(temp.getMinMember());
//            courseLimit.add(courseLimitTemp);
//    }

    public void setConflictCourseIdTemp(ArrayList<Long> conflicts)
    {
        conflictCourseId.add(conflicts);
    }
}
