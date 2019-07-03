package com.example.demo.Service;

import java.util.List;

import com.example.demo.Domain.Course;

public interface CourseService {
	
		Course findone(long courseCode);
		Course  create(Course course);

		Course findByCourseCode(String code);
		Course findByUuid(String code);
		List<Course> findByTeacherId(long id);
		List<Course> findAll();

}
