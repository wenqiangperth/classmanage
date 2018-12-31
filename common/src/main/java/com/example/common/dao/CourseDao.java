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

    @Autowired
    private ConflictCourseStrategyMapper conflictCourseStrategyMapper;

    @Autowired
    private CourseMemberLimitMapper courseMemberLimitMapper;

    @Autowired
    private MemberLimitStrategyMapper memberLimitStrategyMapper;

    @Autowired
    private TeamAndStrategyMapper teamAndStrategyMapper;

    @Autowired
    private TeamOrStrategyMapper teamOrStrategyMapper;

    @Autowired
    private TeamStrategyMapper teamStrategyMapper;

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

    public Course getCourseAndStrategyById(long courseId) {
//        CourseDTO courseDTO=new CourseDTO();
//        courseDTO.setCourse(courseMapper.getCourseById(courseId));
//        for(TeamStrategy teamStrategy:(teamStrategyMapper.selectTeamStrategyByCourseId(courseId)))
//        {
//            switch (teamStrategy.getStrategyName()){
//                case "TeamAndStrategy":teamAndStrategyMapper.selectTeamAndStrategyById(teamStrategy.getStrategyId());break;
//                case "TeamOrStrategy":teamOrStrategyMapper.selectTeamOrStrategyById(teamStrategy.getStrategyId());break;
//                case "CourseMemberLimitStrategy":courseDTO.setCourseCourseLimits(courseMemberLimitMapper.selectCourseMemberLimitStrategyById(teamStrategy.getStrategyId()));break;
//                case "ConflictCourseStrategy":courseDTO.setConflictCourseIdTemp(conflictCourseStrategyMapper.selectConflictCourseStrategyById(teamStrategy.getStrategyId()));break;
//                case "MemberLimitStrategy":courseDTO.setMaxCount(memberLimitStrategyMapper.selectMemberLimitStrategyById(teamStrategy.getStrategyId()).getMaxMember());
//                                               courseDTO.setMinCount(memberLimitStrategyMapper.selectMemberLimitStrategyById(teamStrategy.getStrategyId()).getMinMember());break;
//                default:break;
//            }
//        }
//        return courseDTO;
        return courseMapper.getCourseById(courseId);
    }

    public Long addCourse(CourseDTO courseDTO)
    {
        courseMapper.addCourse(courseDTO.getCourse());
        Long id=courseMapper.getMaxId();
        Long maxConflictCourseStrategyId=conflictCourseStrategyMapper.getMaxConflictCourseStrategyId();
        Long maxCourseMemberLimitId = courseMemberLimitMapper.getMaxCourseMemberLimitStrategyId();
        Long maxMemberLimitStrategyId=memberLimitStrategyMapper.getMaxMemberLimitStrategyId();
        Long maxTeamAndStrategyId=teamAndStrategyMapper.getMaxTeamAndStrategyId();
        Long maxTeamOrStrategyId=teamOrStrategyMapper.getMaxTeamOrStrategyId();
        //Long maxTeamStrategyId=teamStrategyMapper.getMaxTeamStrategyId();
        int serial=0;
        if(courseDTO.getConflictCourseId()!=null) {
            for (ArrayList<Long> conflictCourseStrategys : (courseDTO.getConflictCourseId())) {
                if (conflictCourseStrategys != null) {
                    for (long conflictId : conflictCourseStrategys) {
                        conflictCourseStrategyMapper.insertConflictCourseStrategy(maxConflictCourseStrategyId + 1, conflictId);
                    }
                    serial = serial + 1;
                    teamStrategyMapper.insertTeamStrategy(id, serial, "ConflictCourseStrategy", maxConflictCourseStrategyId + 1);
                }
            }
        }
        if(courseDTO.getCourseLimit()!=null) {
            for (CourseLimit courseLimit : (courseDTO.getCourseLimit())) {
                if (courseLimit != null) {
                    courseMemberLimitMapper.insertCourseMemberLimitStrategy(maxCourseMemberLimitId + 1, courseLimit.getCourseId(), courseLimit.getTeamMinCount(), courseLimit.getTeamMaxCount());
                    if (courseDTO.getFlag() == 1) {
                        teamAndStrategyMapper.insertTeamAndStrategy(id, "CourseMemberLimitStrategy", maxCourseMemberLimitId + 1);
                        serial = serial + 1;
                        teamStrategyMapper.insertTeamStrategy(id, serial, "TeamAndStrategy", maxTeamAndStrategyId + 1);
                    }
                    if (courseDTO.getFlag() == 0) {
                        teamOrStrategyMapper.insertTeamOrStrategy(maxTeamOrStrategyId + 1, "CourseMemberLimitStrategy", maxCourseMemberLimitId + 1);
                        serial = serial + 1;
                        teamStrategyMapper.insertTeamStrategy(id, serial, "TeamOrStrategy", maxTeamOrStrategyId + 1);
                    } else {
                        serial = serial + 1;
                        teamStrategyMapper.insertTeamStrategy(id, serial, "CourseMemberLimitStrategy", maxCourseMemberLimitId + 1);
                    }
                }


            }
        }
        courseMemberLimitMapper.insertCourseMemberLimitStrategy(maxCourseMemberLimitId + 1, courseDTO.getCourseId(), courseDTO.getMinNum(), courseDTO.getMaxNum());
        serial = serial + 1;
        teamStrategyMapper.insertTeamStrategy(id, serial, "CourseMemberLimitStrategy", maxCourseMemberLimitId+1);

        if(courseDTO.getMinCount()!=null||courseDTO.getMaxCount()!=null)
        {
            memberLimitStrategyMapper.insertMemberLimitStrategy(maxMemberLimitStrategyId+1,courseDTO.getMinCount(),courseDTO.getMaxCount());
            serial=serial+1;
            teamStrategyMapper.insertTeamStrategy(id,serial,"MemberLimitStrategy",maxMemberLimitStrategyId + 1);
        }
        return id;
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

//    public ArrayList<CourseVO> getAllCourses()
//    {
//        ArrayList<Course> courses = courseMapper.getAllCourses();
//        ArrayList<CourseVO> teacherCourseVOS = new ArrayList<>();
//        for(Course course:courses)
//        {
//            CourseVO temp= new CourseVO();
//            temp.setCourseVOByCourse(course);
//            temp.setStudentOrTeacherName(teacherMapper.getTeacherInfo(temp.getStudentOrTeacherId()).getName());
//            teacherCourseVOS.add(temp);
//        }
//        return teacherCourseVOS;
//    }

    public ArrayList<Round> getAllRoundByCourseId(long courseId)
    {
        return roundMapper.getAllRoundByCourseId(courseId);
    }

    public ArrayList<Klass> getAllClassByCourseId(long courseId)
    {
        ArrayList<Klass> klasses= klassMapper.getAllClassByCourseId(courseId);
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
        courseMapper.updateTeamMainCourseIdByCourseId(null,subCourseId);
        return courseMapper.deleteTeamShareByteamShareId(teamShareId);
    }

    public ArrayList<CourseVO> getAllCourses()
    {
        ArrayList<Course> courses = courseMapper.getAllCourses();
        ArrayList<CourseVO> teacherCourseVOS = new ArrayList<>();
        for(Course course:courses)
        {
            CourseVO temp= new CourseVO();
            temp.setCourseVOByCourse(course);
            temp.setStudentOrTeacherName(teacherMapper.getTeacherInfo(temp.getStudentOrTeacherId()).getName());
            teacherCourseVOS.add(temp);
        }
        return teacherCourseVOS;
    }



    public ArrayList<SeminarShareVO> getAllSeminarShare(long teacherId)
    {
        ArrayList<SeminarShareVO> allSeminarShareVOS=new ArrayList<>();
        for(Course course:(courseMapper.getAllCourseByTeacherId(teacherId))) {
                ArrayList<SeminarShareVO> seminarShareVOS = courseMapper.getAllSeminarShare(course.getId());

            for(SeminarShareVO seminarShareVO:seminarShareVOS){
                Course mainCourse = courseMapper.getCourseById(seminarShareVO.getMainCourseId());
                seminarShareVO.setMainCourseName(mainCourse.getCourseName());
                seminarShareVO.setMainCourseTeacherId(mainCourse.getTeacherId());
                seminarShareVO.setMainCourseTeacherName(teacherMapper.selectTeacherById(mainCourse.getTeacherId()).getTeacherName());
                if(course.getId().equals(seminarShareVO.getMainCourseId())) {
                    seminarShareVO.setMainCourse(1);
                }
                Course subCourse = courseMapper.getCourseById(seminarShareVO.getSubCourseId());
                seminarShareVO.setSubCourseName(subCourse.getCourseName());
                seminarShareVO.setMainCourseTeacherId(subCourse.getTeacherId());
                seminarShareVO.setSubCourseTeacherName(teacherMapper.selectTeacherById(subCourse.getTeacherId()).getTeacherName());
            }
            allSeminarShareVOS.addAll(seminarShareVOS);
        }
        return allSeminarShareVOS;
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
        Klass klass= klassMapper.getKlassByCourseIdAndStudentId(courseId,studentId);
        long teamId=teamMapper.selectTeamIdByStudentIdAndCourseIdAndClassId(studentId,courseId,klass.getId());
        Team team = teamMapper.selectTeamById(teamId);
        team.setKlassSerial(klass.getKlassSerial());
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
        teamShareVO.setMainCourse(0);
        //未用字段
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
