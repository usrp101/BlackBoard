package com.example.demo.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Domain.Student_course;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Student_courseDao extends JpaRepository<Student_course, Long> {
     List<Student_course> findByCourseId(long id);
     Student_course findByUuid(String uuid);
     List<Student_course> findByStudentId(long id);

}
