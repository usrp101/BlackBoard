package com.example.demo.Service;

import java.util.List;

import com.example.demo.Domain.Student_course;

public interface Student_courseService {
	Student_course findone(long stcsId);
	Student_course  create(Student_course student_course);
	List<Student_course> findAll();
}
