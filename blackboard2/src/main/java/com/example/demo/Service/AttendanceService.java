package com.example.demo.Service;

import java.util.List;

import com.example.demo.Domain.Attendance;

public interface AttendanceService {
	Attendance findone(long atId);
	Attendance create(Attendance attendance);
	List<Attendance>findAll();
	List<Attendance> findByCourse(long id);
	List<Attendance> findByStudent(long id);

}
