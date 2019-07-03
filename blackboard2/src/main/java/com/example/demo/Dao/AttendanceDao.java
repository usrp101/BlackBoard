package com.example.demo.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Domain.Attendance;

import java.util.Date;
import java.util.List;

public interface AttendanceDao extends JpaRepository<Attendance,Long > {
    List<Attendance>  findByStudentCourseStudentId(long id);
    List<Attendance> findByStudentCourseCourseId(long id);
    Attendance findByAttendanceDateAndStudentCourseUuid (Date dt,String uuid);
    List<Attendance> findByAttendanceDateAndStudentCourseCourseUuid(Date dt,String uuid);

}
