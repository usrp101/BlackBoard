package com.example.demo.ServiceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.Dao.Student_courseDao;
import com.example.demo.Domain.Attendance;
import com.example.demo.Domain.Student_course;
import com.example.demo.Service.Student_courseService;

public class Student_courseServiceImp implements Student_courseService {
	
	@Autowired
	public Student_courseDao studentCourseDao;

	@Override
	public Student_course findone(Integer stcsId) {
		Student_course student_course = null;
		Optional<Student_course> opstudentCourse =studentCourseDao.findById(stcsId); 
		if(opstudentCourse.isPresent()) {
			student_course = opstudentCourse.get();
		}
		return (student_course != null) ? student_course : null;
	}

	@Override
	public Student_course create(Student_course student_course) {
		studentCourseDao.save(student_course);
		return (student_course != null) ? student_course : null;
	}

	@Override
	public List<Student_course> findAll() {
		List<Student_course> list = new ArrayList<Student_course>();
		list =   (List<Student_course>) studentCourseDao.findAll();
		return list;
	}

}
