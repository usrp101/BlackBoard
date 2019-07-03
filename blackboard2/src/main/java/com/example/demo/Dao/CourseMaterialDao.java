package com.example.demo.Dao;

import java.util.List;
import java.util.Optional;

import com.example.demo.Domain.Course;
import com.example.demo.Domain.CourseMaterial;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseMaterialDao extends JpaRepository<CourseMaterial,Long>{
    public Optional<CourseMaterial> findByUuid(String uuid);
    List<CourseMaterial> findByCourse(Course course);
}