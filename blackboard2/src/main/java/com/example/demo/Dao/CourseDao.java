package com.example.demo.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Domain.Course;

import java.util.List;

public interface CourseDao extends JpaRepository<Course, Long>{


    public Course findByCourseCode(String code);
    public Course findByUuid(String code);
    public List<Course> findByTeacherId(long id);
}
