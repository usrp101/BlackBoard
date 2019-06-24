package com.example.demo.Dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.Domain.Course;

public interface CourseDao extends CrudRepository<Course, String>{

}
