package com.example.demo.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Domain.Attendance;

import java.util.List;

public interface AttendanceDao extends JpaRepository<Attendance,Long > {
    List<Attendance>  findByStudentCourseStudentId(long id);
    List<Attendance> findByStudentCourseCourseId(long id);

}
