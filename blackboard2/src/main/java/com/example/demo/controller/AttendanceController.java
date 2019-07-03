package com.example.demo.controller;

import com.example.demo.Domain.Attendance;
import com.example.demo.Domain.Course;
import com.example.demo.Domain.Student_course;
import com.example.demo.Domain.Users;
import com.example.demo.Service.AttendanceService;
import com.example.demo.Service.Student_courseService;
import com.example.demo.util.Messages;
import com.example.demo.util.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/attendances")
public class AttendanceController {
      @Autowired
      private Student_courseService studentCourseService;
      @Autowired
      private AttendanceService attendanceService;

    @RequestMapping(value = "/save", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> save(@RequestBody InnerAttendance a) {
        ResponseBean responseBean = new ResponseBean();
        //TODO: process POST request
        try {

            for (AttendanceInfo u: a.attendanceInfo) {
                Student_course stc=studentCourseService.findByUuid(u.studentCourse);
                if(stc!=null){
                    Attendance att=new Attendance();
                    att.setAttendanceDate(a.attendanceDate);
                    att.setPresent(u.isPresent);
                    att.setStudentCourse(stc);
                    attendanceService.create(att);
                }else{
                    responseBean.setCode(Messages.ERROR_CODE);
                    responseBean.setDescription(Messages.error);
                    responseBean.setObject(null);
                }
            }

        } catch (Exception e) {
            //TODO: handle exception
            responseBean.setCode(Messages.ERROR_CODE);
            responseBean.setDescription(Messages.error);
            responseBean.setObject(null);
        }
        return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> update(@RequestBody InnerAttendance a) {
        ResponseBean responseBean = new ResponseBean();
        //TODO: process POST request
        try {

            for (AttendanceInfo u: a.attendanceInfo) {
                Student_course stc=studentCourseService.findByUuid(u.studentCourse);
                if(stc!=null){
                    Attendance att=new Attendance();
                    att.setAttendanceDate(a.attendanceDate);
                    att.setPresent(u.isPresent);
                    att.setStudentCourse(stc);
                    Attendance atCheck=attendanceService.findByAttendanceDateAndStudentCourseUuid(a.attendanceDate,stc.getCourse().getUuid());
                    if(atCheck!=null){
                        atCheck.setStudentCourse(stc);
                        atCheck.setPresent(u.isPresent);
                        atCheck.setAttendanceDate(a.attendanceDate);
                       attendanceService.create(atCheck);
                    }else{
                        attendanceService.create(att);
                    }

                }else{
                    responseBean.setCode(Messages.ERROR_CODE);
                    responseBean.setDescription(Messages.error);
                    responseBean.setObject(null);
                }
            }

        } catch (Exception e) {
            //TODO: handle exception
            responseBean.setCode(Messages.ERROR_CODE);
            responseBean.setDescription(Messages.error);
            responseBean.setObject(null);
        }
        return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
    }


    @RequestMapping(value = "", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findAll(@RequestBody InnerAttendance a) {
        ResponseBean responseBean = new ResponseBean();
        //TODO: process POST request
        try {

            responseBean.setCode(Messages.ERROR_CODE);
            responseBean.setDescription(Messages.error);
            responseBean.setObject(attendanceService.findAll());

        } catch (Exception e) {
            //TODO: handle exception
            responseBean.setCode(Messages.ERROR_CODE);
            responseBean.setDescription(Messages.error);
            responseBean.setObject(null);
        }
        return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
    }

    @RequestMapping(value = "/attend/{date}/{uuid}", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findByDate(@RequestBody InnerAttendance a, @PathVariable("date") String dt,@PathVariable("uuid") String uuid) {
        ResponseBean responseBean = new ResponseBean();
        //TODO: process POST request
        try {
//            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date date= formatter.parse(dt);
            responseBean.setCode(Messages.ERROR_CODE);
            responseBean.setDescription(Messages.error);
            responseBean.setObject(attendanceService.findByAttendanceDateAndStudentCourseCourseUuid(date,uuid));

        } catch (Exception e) {
            //TODO: handle exception
            responseBean.setCode(Messages.ERROR_CODE);
            responseBean.setDescription(Messages.error);
            responseBean.setObject(null);
        }
        return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
    }


    private static  class InnerAttendance{
        private Date attendanceDate;
        private List<AttendanceInfo> attendanceInfo;

        public Date getAttendanceDate() {
            return attendanceDate;
        }

        public void setAttendanceDate(Date attendanceDate) {
            this.attendanceDate = attendanceDate;
        }

        public List<AttendanceInfo> getAttendanceInfo() {
            return attendanceInfo;
        }

        public void setAttendanceInfo(List<AttendanceInfo> attendanceInfo) {
            this.attendanceInfo = attendanceInfo;
        }
    }

    private static class AttendanceInfo{
        private String studentCourse;
        private boolean isPresent;

        public String getStudentCourse() {
            return studentCourse;
        }

        public void setStudentCourse(String studentCourse) {
            this.studentCourse = studentCourse;
        }

        public boolean isPresent() {
            return isPresent;
        }

        public void setPresent(boolean present) {
            isPresent = present;
        }
    }
}
