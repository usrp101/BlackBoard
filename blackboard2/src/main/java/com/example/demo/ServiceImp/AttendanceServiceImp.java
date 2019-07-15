package com.example.demo.ServiceImp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.Dao.AttendanceDao;
import com.example.demo.Domain.Attendance;
import com.example.demo.Service.AttendanceService;
import org.springframework.stereotype.Service;

@Service
public class AttendanceServiceImp implements AttendanceService {
	
	@Autowired
	public AttendanceDao attendanceDao;

	@Override
	public Attendance findone(long atId) {
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

	@Override
	public List<Attendance> findByCourse(long id) {
		List<Attendance> list = new ArrayList<Attendance>();
		list =   (List<Attendance>) attendanceDao.findByStudentCourseCourseId(id);
		return list;
	}

	@Override
	public List<Attendance> findByStudent(long id) {
		List<Attendance> list = new ArrayList<Attendance>();
		list =   (List<Attendance>) attendanceDao.findByStudentCourseStudentId(id);
		return list;
	}

	@Override
	public Attendance findByAttendanceDateAndStudentCourseUuid(Date dt, String uuid) {
		Attendance attendance = null;
		Optional<Attendance> opattendance =Optional.ofNullable(attendanceDao.findByAttendanceDateAndStudentCourseUuid(dt,uuid));
		if(opattendance.isPresent()) {
			attendance = opattendance.get();
		}
		return (attendance != null) ? attendance : null;
	}

	@Override
	public List<Attendance> findByAttendanceDate(Date dt) {
		List<Attendance> list = new ArrayList<Attendance>();
		list =   (List<Attendance>) attendanceDao.findByAttendanceDate(dt);
		return list;
	}

	@Override
	public void delete(Attendance attendance) {
		attendanceDao.delete(attendance);
	}


}
