package com.example.demo.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Domain.Attendance;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AttendanceDao extends JpaRepository<Attendance,Long > {
    List<Attendance>  findByStudentCourseStudentId(long id);
    List<Attendance> findByStudentCourseCourseId(long id);
    Attendance findByAttendanceDateAndStudentCourseUuid (Date dt,String uuid);
    List<Attendance> findByAttendanceDate(Date dt);


}
