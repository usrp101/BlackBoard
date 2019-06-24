package com.example.demo.Service;

import java.util.List;

import com.example.demo.Domain.Attendance;

public interface AttendanceService {
	Attendance findone(Integer atId);
	Attendance create(Attendance attendance);
	List<Attendance>findAll();

}
