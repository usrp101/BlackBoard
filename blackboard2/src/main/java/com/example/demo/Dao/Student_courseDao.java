package com.example.demo.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Domain.Student_course;

import java.util.List;

public interface Student_courseDao extends JpaRepository<Student_course, Long> {
     List<Student_course> findByCourseId();
     Student_course findByUuid(String uuid);


}
