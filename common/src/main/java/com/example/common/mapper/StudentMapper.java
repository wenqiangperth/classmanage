package com.example.common.mapper;

import com.example.common.entity.Course;
import com.example.common.entity.Student;
import com.example.common.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @ClassName StudentMapper
 * @Description
 * @Author perth
 * @Date 2018/12/17 0017 下午 7:58
 * @Version 1.0
 **/

@Mapper
@Repository
public interface StudentMapper {


    /**
     * 查询：获取所有学生
     * @return
     */
    @Select("select id,account,is_active,student_name,email  from student")
    @ResultMap(value = "studentMap")
    public ArrayList<Student>selectAllStudent();

    /**
     * 查询：根据账号获取student
     * @param account
     * @return
     */
    @Select("select * from student where account=#{account}")
    @ResultMap(value = "studentMap")
    public Student selectStudentByAccount(@Param("account")String account);


    /**
     * 查询：name->students
     * @param studentName
     * @return
     */
    @Select("select * from student where student_name=#{studentName}")
    @ResultMap(value = "studentMap")
    public ArrayList<Student> selectStudentByName(@Param("studentName")String studentName);
    /**
     * 查询：根据ID获取学生
     * @param studentId
     * @return
     */
    @Select("select * from student where id=#{studentOrTeacherId}")
    @Results(id="studentMap",value = {
            @Result(property = "account",column = "account"),
            @Result(property = "isActive",column = "is_active"),
            @Result(property = "studentName",column ="student_name" ),
            @Result(property = "email",column = "email")
    })
    public Student selectStudentById(@Param(value="studentOrTeacherId") long studentId);


    /**
     * 查询：根据学生id获取他的所有课程
     * @param studentId
     * @return
     */
    @Select("select c.id,c.teacher_id,c.course_name,c.introduction,c.presentation_percentage," +
            "c.question_percentage,c.report_percentage,c.team_start_time,c.team_end_time,c.team_main_course_id,c.seminar_main_course_id from course c,klass_student ks where c.id=ks.course_id and ks.student_id=#{studentId}")
    @Results(id="courseMap",value = {
            @Result(property = "teacherId",column = "teacher_id"),
            @Result(property = "courseName",column = "course_name"),
            @Result(property = "presentationPercentage",column = "presentation_percentage"),
            @Result(property = "questionPercentage",column ="question_percentage" ),
            @Result(property = "reportPercentage",column = "report_percentage"),
            @Result(property = "teamStartTime",column = "team_start_time"),
            @Result(property = "teamEndTime",column = "team_end_time"),
            @Result(property = "teamMainCourseId",column = "team_main_course_id"),
            @Result(property = "seminarMainCourseId",column = "seminar_main_course_id")
    })
    public ArrayList<Course> getAllCoursesByStundetId(@Param("studentId")Long studentId);


    /**
     * 更新：password
     * @param password
     * @param id
     * @return
     */
    @Update("update student set password=#{password} where id=#{id}")
    public Long updateStundentPassword(@Param("password") String password,@Param("id")Long id);

    /**
     * 管理员修改学生密码
     * @param password
     * @param id
     * @return
     */
    @Update("update student set password=#{password} where id=#{id}")
    public Long updateStundentPasswordByAdmin(@Param("password") String password,@Param("id")Long id);


    /**
     * 更新：email
     * @param id
     * @param email
     * @return
     */
    @Update("update student set email=#{email} where id=#{id}")
    public Long updateStudentEmail(@Param("id")Long id,@Param("email")String email);

    /**
     * 更新：account,name,email
     * @param student
     * @return
     */
    @Update("update student set account=#{account},student_name=#{studentName},email=#{email} where id=#{id}")
    public Long updateStudentInformation(Student student);

    /**
     * 更新：学生激活
     * @param student
     * @return
     */
    @Update("update student set password=#{password},email=#{email},is_active=#{isActive} where id=#{id}")
    public Long updateStudentAcctive(Student student);

    /**
     * 删除:student
     * @param id
     * @return
     */
    @Delete("delete from student where id=#{id}")
    public Long deleteStudentById(@Param("id")Long id);

    /**
     * 删除：klass_student表的关系
     * @param studentId
     * @return
     */
    @Delete("delete from klass_student where student_id=#{studentOrTeacherId}")
    public Long deleteKlaaStudentByStudent(@Param("studentOrTeacherId")Long studentId);

    /**
     * 获得用户信息
     * @param id
     * @return
     */
    @Select("select * from student where id=#{id}")
    @Results(id="UserMap",value = {
            @Result(property = "account",column = "account"),
            @Result(property = "password",column = "password"),
            @Result(property = "isActived",column = "is_active"),
            @Result(property = "name",column ="student_name" ),
            @Result(property = "email",column = "email")
    })
    public User getStudentInfo(@Param("id") Long id);

    /**
     * 通过学号和姓名查询学生信息
     * @param account
     * @param studentName
     * @return
     */
    @Select("select * from student where account=#{account} and student_name=#{studentName}")
    @ResultMap(value="studentMap")
    public Student findStudentByAccountAndStudentName(@Param(value="account") String account,@Param(value="studentName") String studentName);

    /**
     * 添加学生
     * @param student
     * @return
     */
    @Insert("insert into student(account,password,is_active,student_name,email) values(#{account},#{password},#{isActive},#{studentName},#{email})")
    public Long insertStudent(Student student);

    /**
     * 查看是否学生选课
     * @param studentId
     * @param courseId
     * @return
     */
    @Select("select klass_id from klass_student ks,klass k where ks.klass_id=k.id and ks.student_id=#{studentId} and k.course_id=#{courseId}")
    public Long isSelectCourse(@Param("studentId") Long studentId,@Param("courseId") Long courseId);
}
