package com.example.demo.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Domain.Attendance;

public interface AttendanceDao extends JpaRepository<Attendance,Long > {

}
