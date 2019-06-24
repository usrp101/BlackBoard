package com.example.demo.Dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.Domain.Attendance;

public interface AttendanceDao extends CrudRepository<Attendance, Integer> {

}
