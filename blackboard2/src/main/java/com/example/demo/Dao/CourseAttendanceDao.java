package com.example.demo.Dao;

import java.util.List;
import java.util.Optional;

import com.example.demo.Domain.CourseAttendance;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseAttendanceDao extends JpaRepository<CourseAttendance,Long> {
    
    List<CourseAttendance>findByCourseCourseCode(Long id);
    Optional<CourseAttendance>findByUuid(String uuid);
}