package com.example.common.dao;

import com.example.common.entity.*;
import com.example.common.mapper.*;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * @ClassName CourseDao
 * @Description
 * @Author perth
 * @Date 2018/12/18 0018 下午 4:41
 * @Version 1.0
 **/
@Repository
public class CourseDao {

    @Autowired
    private  CourseMapper courseMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private KlassMapper klassMapper;

    @Autowired
    private TeamMapper teamMapper;

    @Autowired
    private SeminarMapper seminarMapper;

    public Course getCourseById(long courseId) {
        return courseMapper.getCourseById(courseId);
    }

    public long addCourse(long teacherId, String courseName, String introduction, int presentationPercentage, int questionPercentage, int reportPercentage, DateTimeLiteralExpression.DateTime teamStartTime, DateTimeLiteralExpression.DateTime teamEndTime, long teamMainCourseId, long seminarMainCourseId)
    {
        return courseMapper.addCourse(teacherId,courseName,introduction,presentationPercentage,questionPercentage,reportPercentage,teamStartTime,teamEndTime,teamMainCourseId,seminarMainCourseId);
    }

    public long deleteCourseById(long courseId)
    {
        return courseMapper.deleteCourseById(courseId);
    }

    public ArrayList<StudentCourseVO> getAllCourseByStudentId(long studentId)
    {
        ArrayList<StudentCourseVO> studentCourseVOS= courseMapper.getAllCourseByStudentId(studentId);
        for(StudentCourseVO studentCourseVO:studentCourseVOS)
        {
            studentCourseVO.setKlass(klassMapper.getKlassByKlassId(studentCourseVO.getKlassId()));
            studentCourseVO.setCourseName(courseMapper.getCourseById(studentCourseVO.getCourseId()).getCourseName());
        }
        return studentCourseVOS;
    }

    public ArrayList<StudentCourseVO> getAllCourseByTeacherId(long teacherId)
    {
        ArrayList<Course> courses = courseMapper.getAllCourseByTeacherId(teacherId);
        ArrayList<StudentCourseVO> teacherCourseVOS = new ArrayList<>();
        for(Course course:courses)
        {
            StudentCourseVO temp= new StudentCourseVO();
            temp.setCourseId(course.getId());
            temp.setCourseName(course.getCourseName());
            temp.setStudentId(course.getTeacherId());
            teacherCourseVOS.add(temp);
        }
        return teacherCourseVOS;
    }

    public ArrayList<Round> getAllRoundByCourseId(long courseId)
    {
        return courseMapper.getAllRoundByCourseId(courseId);
    }

    public ArrayList<Klass> getAllClassByCourseId(long courseId)
    {
        return klassMapper.getAllClassByCourseId(courseId);
    }

    public ArrayList<Student> getAllNoTeamByCourseId(long courseId)
    {
        ArrayList<Student> students = new ArrayList<Student>();
        ArrayList<Long> studentIds = courseMapper.getAllNoTeamStudentByCourseId(courseId);
        for(Long studentId:studentIds)
        {
            Student student = studentMapper.selectStudentById(studentId);
            students.add(student);
        }
        return students;
    }

    public ArrayList<TeamShareVO> getAllTeamShare(long courseId)
    {
        ArrayList<TeamShareVO> teamShareVOS=courseMapper.getAllTeamShare(courseId);
        for(TeamShareVO teamShareVO:teamShareVOS){
            Course mainCourse = courseMapper.getCourseById(teamShareVO.getMainCourseId());
            teamShareVO.setMainCourseName(mainCourse.getCourseName());
            teamShareVO.setMainCourseTeacherId(mainCourse.getTeacherId());
            teamShareVO.setMainCourseTeacherName(teacherMapper.selectTeacherById(mainCourse.getTeacherId()).getTeacherName());
            if(courseId==teamShareVO.getMainCourseId()) {
                teamShareVO.setMainCourse(1);
            }
            Course subCourse = courseMapper.getCourseById(teamShareVO.getSubCourseId());
            teamShareVO.setSubCourseName(subCourse.getCourseName());
            teamShareVO.setMainCourseTeacherId(subCourse.getTeacherId());
            teamShareVO.setSubCourseTeacherName(teacherMapper.selectTeacherById(subCourse.getTeacherId()).getTeacherName());
        }
        return teamShareVOS;
    }

    public ArrayList<Team> deleteTeamShareByTeamShareId(long teamShareId)
    {
        Long subCourseId=courseMapper.getSubCourseIdByTeamShareId(teamShareId);
        courseMapper.deleteTeamShareByteamShareId(teamShareId);
        ArrayList<Team> teams = teamMapper.selectTeamsByCourseId(subCourseId);
        return teams;
    }

    public ArrayList<SeminarShareVO> getAllSeminarShare(long courseId)
    {
        ArrayList<SeminarShareVO> seminarShareVOS=courseMapper.getAllSeminarShare(courseId);
        for(SeminarShareVO seminarShareVO:seminarShareVOS){
            Course mainCourse = courseMapper.getCourseById(seminarShareVO.getMainCourseId());
            seminarShareVO.setMainCourseName(mainCourse.getCourseName());
            seminarShareVO.setMainCourseTeacherId(mainCourse.getTeacherId());
            seminarShareVO.setMainCourseTeacherName(teacherMapper.selectTeacherById(mainCourse.getTeacherId()).getTeacherName());
            if(courseId==seminarShareVO.getMainCourseId()) {
                seminarShareVO.setMainCourse(1);
            }
            Course subCourse = courseMapper.getCourseById(seminarShareVO.getSubCourseId());
            seminarShareVO.setSubCourseName(subCourse.getCourseName());
            seminarShareVO.setMainCourseTeacherId(subCourse.getTeacherId());
            seminarShareVO.setSubCourseTeacherName(teacherMapper.selectTeacherById(subCourse.getTeacherId()).getTeacherName());
        }
        return seminarShareVOS;
    }


    public ArrayList<Team> getAllTeamByCourseId(long courseId)
    {
           ArrayList<Team> teams = new ArrayList<Team>();
           ArrayList<Klass> klasses=klassMapper.getAllClassByCourseId(courseId);
           for(Klass klass:klasses)
           {
               ArrayList<Team> temps = teamMapper.selectTeamsByCourseIdAndClassId(klass.getId(),courseId);
               for(Team temp:temps)
               {
                   temp.setStudents(teamMapper.selectStudentsByTeamId(temp.getId()));
               }
               teams.addAll(temps);
           }
           return teams;
    }

    public Team getTeamByCourseIdAndStudentId(long studentId,long courseId)
    {
        long teamId=courseMapper.getTeamIdByCourseIdAndStudentId(courseId,studentId);
        Team team = teamMapper.selectTeamById(teamId);
        team.setStudents(teamMapper.selectStudentsByTeamId(teamId));
        return team;
    }

    public Long deleteSeminarShareBySeminarShareId(long seminarShareId)
    {
        Long subCourseId = courseMapper.getSubCourseIdBySeminarShareId(seminarShareId);
        ArrayList<Klass> klasses=klassMapper.getAllClassByCourseId(subCourseId);
        ArrayList<Seminar> seminars=seminarMapper.findAllSeminarByCourseId(subCourseId);
        for(Klass klass:klasses)
        {
            for(Seminar seminar:seminars)
            {
                courseMapper.deleteKlassSeminarByCourseId(klass.getId(),seminar.getId());
            }

        }
        return courseMapper.deleteSeminarShareBySeminarShareId(seminarShareId);
    }

    public Long createTeamShareRequest(Long courseId,Long subCourseId)
    {
        Course course=courseMapper.getCourseById(subCourseId);
        return courseMapper.createTeamShareRequest(courseId,subCourseId,course.getTeacherId());
    }

    public Long createSeminarShareRequest(Long courseId,Long subCourseId)
    {
        Course course=courseMapper.getCourseById(subCourseId);
        return courseMapper.createSeminarShareRequest(courseId,subCourseId,course.getTeacherId());
    }

    public TeamShareVO getTeamShareByTeamShareId(long teamShareId)
    {
        TeamShareVO teamShareVO=courseMapper.getTeamShareByTeamShareId(teamShareId);
        Course mainCourse = courseMapper.getCourseById(teamShareVO.getMainCourseId());
        teamShareVO.setMainCourseName(mainCourse.getCourseName());
        teamShareVO.setMainCourseTeacherId(mainCourse.getTeacherId());
        teamShareVO.setMainCourseTeacherName(teacherMapper.selectTeacherById(mainCourse.getTeacherId()).getTeacherName());
        teamShareVO.setMainCourse(0);    //未用字段
        Course subCourse = courseMapper.getCourseById(teamShareVO.getSubCourseId());
        teamShareVO.setSubCourseName(subCourse.getCourseName());
        teamShareVO.setMainCourseTeacherId(subCourse.getTeacherId());
        teamShareVO.setSubCourseTeacherName(teacherMapper.selectTeacherById(subCourse.getTeacherId()).getTeacherName());

        return teamShareVO;
    }

    public SeminarShareVO getSeminarShareBySeminarShareId(long seminarShareId)
    {
        SeminarShareVO seminarShareVO=courseMapper.getSeminarShareBySeminarShareId(seminarShareId);
        Course mainCourse = courseMapper.getCourseById(seminarShareVO.getMainCourseId());
        seminarShareVO.setMainCourseName(mainCourse.getCourseName());
        seminarShareVO.setMainCourseTeacherId(mainCourse.getTeacherId());
        seminarShareVO.setMainCourseTeacherName(teacherMapper.selectTeacherById(mainCourse.getTeacherId()).getTeacherName());
        seminarShareVO.setMainCourse(0);
        Course subCourse = courseMapper.getCourseById(seminarShareVO.getSubCourseId());
        seminarShareVO.setSubCourseName(subCourse.getCourseName());
        seminarShareVO.setMainCourseTeacherId(subCourse.getTeacherId());
        seminarShareVO.setSubCourseTeacherName(teacherMapper.selectTeacherById(subCourse.getTeacherId()).getTeacherName());

        return seminarShareVO;
    }


}
