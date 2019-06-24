package com.example.demo.Service;

import java.util.List;

import com.example.demo.Domain.Course;

public interface CourseService {
	
		Course findone(String courseCode);
		Course  create(Course course);
		List<Course> findAll();

}
