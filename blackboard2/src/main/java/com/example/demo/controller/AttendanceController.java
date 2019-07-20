package com.example.demo.controller;

import com.example.demo.Dao.AttendanceDao;
import com.example.demo.Dao.CourseAttendanceDao;
import com.example.demo.Domain.Attendance;
import com.example.demo.Domain.Course;
import com.example.demo.Domain.CourseAttendance;
import com.example.demo.Domain.Student_course;
import com.example.demo.Service.AttendanceService;
import com.example.demo.Service.CourseService;
import com.example.demo.Service.Student_courseService;
import com.example.demo.util.Messages;
import com.example.demo.util.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/attendances")
public class AttendanceController {
    @Autowired
    private Student_courseService studentCourseService;
    @Autowired
    private AttendanceService attendanceService;
    @Autowired
    private AttendanceDao attService;
    @Autowired
    private CourseAttendanceDao courseAttendanceService;

    @RequestMapping(value = "/save", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> save(@RequestBody Map<String,Object> map) {
        ResponseBean responseBean = new ResponseBean();
        //TODO: process POST request
        try {
            Optional<CourseAttendance> c = courseAttendanceService.findByUuid(map.get("courseAttendanceUuid").toString());
            if(c.isPresent()){
                List<String> li = (List<String>) map.get("attendanceInfo");
                for(String s:li){
                    String scUuid = s.split("_")[0];
                    boolean present = Boolean.parseBoolean(s.split("_")[1]);
                    Student_course sc = studentCourseService.findByUuid(scUuid);
                    if(sc!=null){
                        Optional<Attendance> check = attService.findByStudentCourseIdAndCourseAttendanceId(sc.getId(), c.get().getId());
                        if(check.isPresent()){
                            Attendance at = check.get();
                            at.setPresent(present);
                            attService.save(at);
                        }else{
                            Attendance at = new Attendance();
                            at.setPresent(present);
                            at.setCourseAttendance(c.get());
                            at.setStudentCourse(sc);
                            attService.save(at);
                        }
                    }
                }
                responseBean.setCode(200);
                responseBean.setDescription("attendance saved successfully");
            }else{
                responseBean.setCode(404);
                responseBean.setDescription("course attendance not found");
            }
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
            responseBean.setCode(Messages.ERROR_CODE);
            responseBean.setDescription(Messages.error);
            responseBean.setObject(null);
        }
        return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
    }

    @RequestMapping(value = "/courseattendance/{uuid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findAll(@PathVariable String uuid) {
        ResponseBean responseBean = new ResponseBean();
        try {
            Optional<CourseAttendance> c = courseAttendanceService.findByUuid(uuid);
            if(c.isPresent()){
                responseBean.setCode(200);
                responseBean.setDescription("success");
                responseBean.setObject(attService.findByCourseAttendanceId(c.get().getId()));
            }else{
                responseBean.setCode(404);
                responseBean.setDescription("courseAttendance not found");
            }
        } catch (Exception e) {
            // TODO: handle exception
            responseBean.setCode(Messages.ERROR_CODE);
            responseBean.setDescription(Messages.error);
            responseBean.setObject(null);
        }
        return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
    }


}
