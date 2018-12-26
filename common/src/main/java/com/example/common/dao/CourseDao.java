package com.example.common.dao;

import com.example.common.entity.*;
import com.example.common.mapper.*;
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

    @Autowired
    private RoundMapper roundMapper;

    @Autowired
    private ScoreMapper scoreMapper;

    public ArrayList<RoundTeamScoreVO>getRoundTeamScoreByCourseIdAndRoundId(Long courseId,Long roundId){
        ArrayList<Team> teams=teamMapper.selectTeamsByCourseId(courseId);
        ArrayList<RoundTeamScoreVO>roundTeamScoreVOS=new ArrayList<>();

        ArrayList<Seminar>seminars=seminarMapper.selectSeminarByCourseIdAndRoundId(roundId,courseId);
        for (Team team:teams
        ) {
            Score roundScore=scoreMapper.selectRoundScore(roundId,team.getId());
            RoundTeamScoreVO roundTeamScoreVO=new RoundTeamScoreVO();
            roundTeamScoreVO.setTeam(team);
            roundTeamScoreVO.setRoundScore(roundScore);
            ArrayList<Score>seminarScores=new ArrayList<>();
            for (Seminar seminar:seminars
                 ) {
                Score seminarScore=scoreMapper.selectSeminarScoreByTeamIdAndSeminarId(seminar.getId(),team.getId(),courseId);
                if(seminarScore==null){
                    seminarScore=new Score();
                }
                seminarScore.setSeminarOrRoundId(seminar.getId());
                seminarScore.setSeminarName(seminar.getSeminarName());
                seminarScores.add(seminarScore);
            }
            roundTeamScoreVO.setSeminarScores(seminarScores);
            roundTeamScoreVOS.add(roundTeamScoreVO);
        }
        return roundTeamScoreVOS;
    }

    public Course getCourseById(long courseId) {
        return courseMapper.getCourseById(courseId);
    }

    public long addCourse(Course course)
    {
        return courseMapper.addCourse(course);
    }

    public long deleteCourseById(long courseId)
    {
        return courseMapper.deleteCourseById(courseId);
    }

    public ArrayList<CourseVO> getAllCourseByStudentId(long studentId)
    {
        ArrayList<CourseVO> courseVOS = courseMapper.getAllCourseByStudentId(studentId);
        for(CourseVO courseVO : courseVOS)
        {
            courseVO.setKlass(klassMapper.getKlassByKlassId(courseVO.getKlassId()));
            courseVO.setCourseName(courseMapper.getCourseById(courseVO.getCourseId()).getCourseName());
        }
        return courseVOS;
    }

    public ArrayList<CourseVO> getAllCourseByTeacherId(long teacherId)
    {
        ArrayList<Course> courses = courseMapper.getAllCourseByTeacherId(teacherId);
        ArrayList<CourseVO> teacherCourseVOS = new ArrayList<>();
        for(Course course:courses)
        {
            CourseVO temp= new CourseVO();
            temp.setCourseVOByCourse(course);
            teacherCourseVOS.add(temp);
        }
        return teacherCourseVOS;
    }

    public ArrayList<Round> getAllRoundByCourseId(long courseId)
    {
        return roundMapper.getAllRoundByCourseId(courseId);
    }

    public ArrayList<Klass> getAllClassByCourseId(long courseId)
    {
         ArrayList<Klass> klasses= klassMapper.getAllClassByCourseId(courseId);
        System.out.println(courseId+"perth");
        System.out.println(klasses);
        return klasses;
    }

    public ArrayList<Student>getAllNoTeamByCourseId(long courseId)
    {
        ArrayList<Student> students = new ArrayList<Student>();
        ArrayList<Klass> klasses = klassMapper.getAllClassByCourseId(courseId);
        ArrayList<Long> studentIds = new ArrayList<>();
        ArrayList<Long> teamStudentIds=new ArrayList<>();
        ArrayList<Long> noStudentIds = new ArrayList<>();
        ArrayList<Long> teamIds = new ArrayList<>();
        for(Klass klass:klasses) {
            teamIds.addAll(teamMapper.findAllTeamByClassId(klass.getId()));
            studentIds.addAll(klassMapper.findAllStudentByClassId(klass.getId()));
        }
        for(Long teamId:teamIds)
        {
           teamStudentIds.addAll(teamMapper.findAllStudentIdByTeamId(teamId));
        }
        for(Long studentId:studentIds)
        {
            if(!teamStudentIds.contains(studentId))
            {
                noStudentIds.add(studentId);
            }
        }
        for(Long studentId:noStudentIds)
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

    public Long deleteTeamShareByTeamShareId(long teamShareId)
    {
        Long subCourseId=courseMapper.getSubCourseIdByTeamShareId(teamShareId);

        ArrayList<Klass> klasses=klassMapper.getAllClassByCourseId(subCourseId);
        for(Klass klass:klasses) {
            teamMapper.deleteAllKlassTeam(klass.getId());
        }
        return courseMapper.deleteTeamShareByteamShareId(teamShareId);
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
        long classId = klassMapper.getClassIdByCourseIdAndStudentId(courseId,studentId);
        long teamId=teamMapper.selectTeamIdByStudentIdAndCourseIdAndClassId(studentId,courseId,classId);
        Team team = teamMapper.selectTeamById(teamId);
        team.setStudents(teamMapper.selectStudentsByTeamId(teamId));
        return team;
    }

    public Long deleteSeminarShareBySeminarShareId(long seminarShareId)
    {
        Long subCourseId = courseMapper.getSubCourseIdBySeminarShareId(seminarShareId);
        Long mainCourseId=courseMapper.getSeminarShareBySeminarShareId(seminarShareId).getMainCourseId();
        ArrayList<Klass> klasses=klassMapper.getAllClassByCourseId(subCourseId);
        ArrayList<Seminar> seminars=seminarMapper.findAllSeminarByCourseId(mainCourseId);
        ArrayList<Round> rounds=roundMapper.getAllRoundByCourseId(mainCourseId);
        for(Klass klass:klasses)
        {
            for(Seminar seminar:seminars)
            {
                courseMapper.deleteKlassSeminarByCourseId(klass.getId(),seminar.getId());

            }
            klassMapper.deleteClassRoundByClassId(klass.getId());
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

    public Long isSelectCourse(Long courseId,Long studentId)
    {
        return courseMapper.isSelectCourse(courseId,studentId);
    }

    public Long updateTeamMainCourseIdByCourseId(Long mainCourseId,Long courseId)
    {
        return courseMapper.updateTeamMainCourseIdByCourseId(mainCourseId,courseId);
    }

    public Long updateSeminarMainCourseIdByCourseId(Long mainCourseId,Long courseId)
    {
        return courseMapper.updateSeminarMainCourseIdByCourseId(mainCourseId,courseId);
    }


}
