package com.example.demo.ServiceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.Dao.AttendanceDao;
import com.example.demo.Domain.Attendance;
import com.example.demo.Domain.Comments;
import com.example.demo.Service.AttendanceService;

public class AttendanceServiceImp implements AttendanceService {
	
	@Autowired
	public AttendanceDao attendanceDao;

	@Override
	public Attendance findone(Integer atId) {
		Attendance attendance = null;
		Optional<Attendance> opattendance =attendanceDao.findById(atId); 
		if(opattendance.isPresent()) {
			attendance = opattendance.get();
		}
		return (attendance != null) ? attendance : null;
	}

	@Override
	public Attendance create(Attendance attendance) {
		attendanceDao.save(attendance);
		return (attendance != null) ? attendance : null;
	}

	@Override
	public List<Attendance> findAll() {
		List<Attendance> list = new ArrayList<Attendance>();
		list =   (List<Attendance>) attendanceDao.findAll();
		return list;
	}

	
}
