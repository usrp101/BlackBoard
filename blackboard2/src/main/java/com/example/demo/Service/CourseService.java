package com.example.demo.Service;

import java.util.List;

import com.example.demo.Domain.Course;

public interface CourseService {
	
		Course findone(long courseCode);
		Course  create(Course course);
		List<Course> findAll();

}
