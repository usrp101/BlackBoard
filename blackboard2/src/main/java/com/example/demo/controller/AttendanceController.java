package com.example.demo.controller;

import com.example.demo.Domain.Attendance;

import com.example.demo.Domain.Student_course;
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
            
             SimpleDateFormat sd=new SimpleDateFormat("yyy-MM-dd");
             Date dt=sd.parse(a.attendanceDate);
             System.out.println(studentCourseService.findByUuid(a.attendanceInfo.get(0)).getUuid());
             List<Attendance> tt=attendanceService.findByAttendanceDateAndStudentCourseCourseUuid(dt,studentCourseService.findByUuid(a.attendanceInfo.get(0)).getUuid() );
             makeUbsent(tt);
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
     



    public void makeUbsent(List<Attendance> attendances){
          for(Attendance a: attendances){
              a.setPresent(false);
              attendanceService.delete(a);
          }
    }





    // @RequestMapping(value = "/update", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    // public ResponseEntity<Object> update(@RequestBody InnerAttendance a) {
    //     ResponseBean responseBean = new ResponseBean();
    //     //TODO: process POST request
    //     try {

    //         for (AttendanceInfo u: a.attendanceInfo) {
    //             Student_course stc=studentCourseService.findByUuid(u.studentCourse);
    //             if(stc!=null){
    //                 Attendance att=new Attendance();
    //                 att.setAttendanceDate(a.attendanceDate);
    //                 att.setPresent(u.isPresent);
    //                 att.setStudentCourse(stc);
    //                 Attendance atCheck=attendanceService.findByAttendanceDateAndStudentCourseUuid(a.attendanceDate,stc.getCourse().getUuid());
    //                 if(atCheck!=null){
    //                     atCheck.setStudentCourse(stc);
    //                     atCheck.setPresent(u.isPresent);
    //                     atCheck.setAttendanceDate(a.attendanceDate);
    //                    attendanceService.create(atCheck);
    //                 }else{
    //                     attendanceService.create(att);
    //                 }

    //             }else{
    //                 responseBean.setCode(Messages.ERROR_CODE);
    //                 responseBean.setDescription(Messages.error);
    //                 responseBean.setObject(null);
    //             }
    //         }

    //     } catch (Exception e) {
    //         //TODO: handle exception
    //         responseBean.setCode(Messages.ERROR_CODE);
    //         responseBean.setDescription(Messages.error);
    //         responseBean.setObject(null);
    //     }
    //     return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
    // }


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
