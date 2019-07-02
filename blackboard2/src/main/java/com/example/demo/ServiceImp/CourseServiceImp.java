package com.example.demo.ServiceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Dao.CourseDao;
import com.example.demo.Domain.Course;
import com.example.demo.Service.CourseService;

@Service
public class CourseServiceImp implements CourseService{

	
	@Autowired
	public CourseDao courseDao;
	@Override
	public Course findone(long courseCode) {
		Course course = null;
		Optional<Course> opcourse =courseDao.findById(courseCode); 
		if(opcourse.isPresent()) {
			course = opcourse.get();
		}
		return (course != null) ? course : null;	
	}

	@Override
	public Course create(Course course) {
		
		courseDao.save(course);
		return (course != null) ? course : null;
	}

	@Override
	public List<Course> findAll() {
		List<Course> list = new ArrayList<Course>();
		list =   (List<Course>) courseDao.findAll();
		return list;
	}

	

	
	
	

}
