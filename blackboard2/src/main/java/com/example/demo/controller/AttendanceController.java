package com.example.demo.controller;

import com.example.demo.Domain.Attendance;
import com.example.demo.Domain.Course;
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

@RestController
@RequestMapping("/attendances")
public class AttendanceController {
      @Autowired
      private Student_courseService studentCourseService;
      @Autowired
      private AttendanceService attendanceService;
      
      @Autowired
      private CourseService courseService;

    @RequestMapping(value = "/save", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> save(@RequestBody InnerAttendance a) {
        ResponseBean responseBean = new ResponseBean();
        //TODO: process POST request
        try {
            
             SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
             Date dt=sd.parse(a.attendanceDate);
            
             List<Attendance> tt=attendanceService.findByAttendanceDate(dt);
                Student_course stcheck=studentCourseService.findByUuid(a.attendanceInfo.get(0));
             makeUbsent(tt,stcheck);
            for (String u: a.attendanceInfo) {
                Student_course stc=studentCourseService.findByUuid(u);
                if(stc!=null){
                    Attendance att=new Attendance();
                    att.setAttendanceDate(dt);
                    att.setPresent(true);
                    att.setStudentCourse(stc);
                    Attendance chek=attendanceService.findByAttendanceDateAndStudentCourseUuid(dt, stc.getUuid());
                    if(chek!=null){
                         chek.setId(chek.getId());
                          attendanceService.create(att);
                    }else{
                          attendanceService.create(att);
                    }
                   
                }else{
                    responseBean.setCode(Messages.ERROR_CODE);
                    responseBean.setDescription(Messages.error);
                    responseBean.setObject(null);
                }
                responseBean.setCode(Messages.SUCCESS_CODE);
                responseBean.setDescription("Attendance is Done Successfull");
                responseBean.setObject("");
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
     



    public void makeUbsent(List<Attendance> attendances,Student_course s){
          for(Attendance a: attendances){
              if(a.getStudentCourse().getCourse().getId()==s.getCourse().getId()){
                   a.setPresent(false);
                 attendanceService.delete(a);
              }
          }
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

    @RequestMapping(value = "/attend/{uuid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findByDate(@PathVariable("uuid") String uuid) {
        ResponseBean responseBean = new ResponseBean();
        //TODO: process POST request
        try {
            Course c=courseService.findByUuid(uuid);
            if(c!=null){
                  
                    List<Attendance> att=new ArrayList<>();
                     List<Attendance> ac=attendanceService.findByAttendanceDate(new Date());
                    for(Attendance a: ac){
                          if(a.getStudentCourse().getCourse().getId()==c.getId()){
                               att.add(a);
                          }
                    }
                    responseBean.setCode(Messages.SUCCESS_CODE);
                    responseBean.setDescription("");
                    responseBean.setObject(att);
                   
            }else{
                    responseBean.setCode(Messages.ERROR_CODE);
                    responseBean.setDescription(Messages.error);
                    responseBean.setObject(null);
            }
//            Date date = new Date();
         
        } catch (Exception e) {
            //TODO: handle exception
            responseBean.setCode(Messages.ERROR_CODE);
            responseBean.setDescription(Messages.error);
            responseBean.setObject(null);
        }
        return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
    }


    private static  class InnerAttendance{
        private String attendanceDate;
        private List<String> attendanceInfo;

        public String getAttendanceDate() {
            return attendanceDate;
        }

        public void setAttendanceDate(String attendanceDate) {
            this.attendanceDate = attendanceDate;
        }

        public List<String> getAttendanceInfo() {
            return attendanceInfo;
        }

        public void setAttendanceInfo(List<String> attendanceInfo) {
            this.attendanceInfo = attendanceInfo;
        }
    }

   
}
