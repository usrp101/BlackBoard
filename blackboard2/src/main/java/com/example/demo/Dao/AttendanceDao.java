package com.example.demo.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Domain.Attendance;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceDao extends JpaRepository<Attendance,Long > {
    List<Attendance>  findByStudentCourseStudentId(long id);
    List<Attendance> findByStudentCourseCourseId(long id);
    Attendance findByCourseAttendanceDateAndStudentCourseUuid (Date dt,String uuid);
    List<Attendance> findByCourseAttendanceDate(Date dt);
    List<Attendance> findByCourseAttendanceId(Long id);
    Optional<Attendance> findByStudentCourseIdAndCourseAttendanceId(long l,long m);
    Optional<Attendance> findByStudentCourseStudentIdAndCourseAttendanceId(long l,long m);
    List<Attendance> findByStudentCourseId(Long id);
}
