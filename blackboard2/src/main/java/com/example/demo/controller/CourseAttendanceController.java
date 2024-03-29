package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.demo.Dao.CourseAttendanceDao;
import com.example.demo.Domain.Course;
import com.example.demo.Domain.CourseAttendance;
import com.example.demo.Service.CourseService;
import com.example.demo.util.ResponseBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/courseattendances")
public class CourseAttendanceController {
    @Autowired
    private CourseAttendanceDao courseAttendanceService;
    @Autowired
    private CourseService courseService;

    @GetMapping(value="/save/{uuid}")
    public ResponseEntity<Object> create(@PathVariable String uuid) {
        ResponseBean rs = new ResponseBean();
        try {
            Course c = courseService.findByUuid(uuid);
            if(c!= null){
                CourseAttendance catt = new CourseAttendance();
                catt.setDate(new Date());
                catt.setCourse(c);
                courseAttendanceService.save(catt);
                rs.setCode(200);
                rs.setDescription("success");
            }else{
                rs.setCode(404);
                rs.setDescription("course not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            rs.setCode(300);
            rs.setDescription("Error occured");
        }
        return new ResponseEntity<>(rs,HttpStatus.OK);
    }
    

    @GetMapping(value="/course/{uuid}")
    public ResponseEntity<Object> findAllByCourseCode(@PathVariable String uuid) {
        ResponseBean rs = new ResponseBean();
        try {
            List<CourseAttendance> li = courseAttendanceService.findByCourseUuid(uuid);
            rs.setCode(200);
            rs.setDescription("success");
            rs.setObject(li);
        } catch (Exception e) {
            e.printStackTrace();
            rs.setCode(300);
            rs.setDescription("Error occured");
        }
        return new ResponseEntity<>(rs,HttpStatus.OK);
    }

    @GetMapping(value="/{uuid}")
    public ResponseEntity<Object> findaTTEND(@PathVariable String uuid){
        ResponseBean rs = new ResponseBean();
        try {
            Optional<CourseAttendance> li = courseAttendanceService.findByUuid(uuid);
            rs.setCode(200);
            rs.setDescription("success");
            rs.setObject(li);
        } catch (Exception e) {
            e.printStackTrace();
            rs.setCode(300);
            rs.setDescription("Error occured");
        }
        return new ResponseEntity<>(rs,HttpStatus.OK);

    }



}