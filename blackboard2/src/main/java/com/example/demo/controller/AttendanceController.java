package com.example.demo.controller;

import com.example.demo.Dao.AttendanceDao;
import com.example.demo.Dao.CourseAttendanceDao;
import com.example.demo.Dao.Student_courseDao;
import com.example.demo.Domain.*;
import com.example.demo.Service.AttendanceService;
import com.example.demo.Service.CourseService;
import com.example.demo.Service.StudentService;
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
    @Autowired
    private StudentService studentService;
     @Autowired
    private CourseService courseService;
    @Autowired
    private Student_courseDao studentCourseDao;

    @RequestMapping(value = "/save", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> save(@RequestBody InnerAttend attend) {
        ResponseBean responseBean = new ResponseBean();
        //TODO: process POST request
        try {
            Optional<CourseAttendance> c = courseAttendanceService.findByUuid(attend.courseAttend);
            if(c.isPresent()){
                List<String> li = attend.attend;
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



    @RequestMapping(value = "/studentattend/studentid/{stid}/course/{cuuid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findStudentId(@PathVariable("stid") String stid,@PathVariable("cuuid")String uuid) {
        ResponseBean responseBean = new ResponseBean();
        try {
            Student st=studentService.findByStudentId(stid);
            Course c=courseService.findByUuid(uuid);
            if(st!=null){
               if(c!=null){
                   Student_course stc=studentCourseDao.findByStudentIdAndCourseId(st.getId(),c.getId());
                   if(stc!=null){
                         List<Attendance> attendance=attService.findByStudentCourseId(stc.getId());
                       responseBean.setCode(Messages.SUCCESS_CODE);
                       responseBean.setDescription("FOUND");
                       responseBean.setObject(attendance);
                   }else{
                       responseBean.setCode(Messages.ERROR_CODE);
                       responseBean.setDescription("DATA NOT FOUND");
                       responseBean.setObject(null);
                   }
//                   Attendance a=attendanceService.
               }else{
                   responseBean.setCode(Messages.ERROR_CODE);
                   responseBean.setDescription("COURSE NOT FOUND");
                   responseBean.setObject(null);
               }
            }else{
                responseBean.setCode(Messages.ERROR_CODE);
                responseBean.setDescription("STUDENT NOT FOUND");
                responseBean.setObject(null);
            }
        } catch (Exception e) {
            // TODO: handle exception
            responseBean.setCode(Messages.ERROR_CODE);
            responseBean.setDescription(Messages.error);
            responseBean.setObject(null);
        }
        return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
    }



    public static class InnerAttend{
        private List<String> attend;
        private String courseAttend;

        public List<String> getAttend() {
            return attend;
        }

        public void setAttend(List<String> attend) {
            this.attend = attend;
        }

        public String getCourseAttend() {
            return courseAttend;
        }

        public void setCourseAttend(String courseAttend) {
            this.courseAttend = courseAttend;
        }
    }

}



