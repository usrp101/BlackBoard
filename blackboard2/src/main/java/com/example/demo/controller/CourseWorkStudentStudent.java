package com.example.demo.controller;


import com.example.demo.Dao.CourseWorkDao;
import com.example.demo.Dao.CourseWorkStudentDao;
import com.example.demo.Domain.CourseWork;
import com.example.demo.Domain.CourseWorkStudent;
import com.example.demo.Domain.Student;
import com.example.demo.Domain.Student_course;
import com.example.demo.Service.CourseService;
import com.example.demo.Service.StudentService;
import com.example.demo.Service.Student_courseService;
import com.example.demo.util.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("courseworkstudets")
public class CourseWorkStudentStudent {

    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseWorkDao courseWorkDao;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseWorkStudentDao cwsDao;
    @Autowired
    private Student_courseService studentCourseService;


    @PostMapping(value = "/create")
    public ResponseEntity<Object> save(@RequestBody List<InnerCourseWorkStudent> icwStudent){
        ResponseBean rs=new ResponseBean();
        try{
         
         
          for(InnerCourseWorkStudent cs: icwStudent){
                CourseWork c = courseWorkDao.findByUuid(cs.courseWorkUuid);
                Student_course st=studentCourseService.findByUuid(cs.studentUuid);
                CourseWorkStudent courseWorkStudent=cwsDao.findByCourseWorkIdAndStudentId(c.getId(),st.getStudent().getId());
                if(courseWorkStudent!=null){
                   courseWorkStudent.setMarks(cs.mark);
                   cwsDao.save(courseWorkStudent);
                }else{
                    CourseWorkStudent cws=new CourseWorkStudent();
                    cws.setCourseWork(c);
                    cws.setStudent(st.getStudent());
                    cws.setMarks(cs.getMark());
                    cwsDao.save(cws);
                }

          }
            rs.setDescription("STUDENT MARK WAS RECORDED SUCCESFULLY");
            rs.setCode(200);
            // rs.setObject(cws);
          
      }catch (Exception ex){

            rs.setDescription("ERROR OCCURRED TRY AGAIN");
            rs.setCode(400);
            ex.printStackTrace();
      }
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }




    @PutMapping(value = "/update/{uuid}")
    public ResponseEntity<Object> update(@RequestBody InnerCourseWorkStudent icwStudent,@PathVariable String uuid ){
        ResponseBean rs=new ResponseBean();
        try{
            CourseWorkStudent cws=cwsDao.findByUuid(uuid);
            if(cws!=null){
               cws.setMarks(icwStudent.mark);
               cwsDao.save(cws);
                    rs.setDescription(" MARKS CHANGED SUCESSFULLY ");
                    rs.setCode(200);
                    rs.setObject(cws);
            }else{
                rs.setDescription(" NOT FOUND");
                rs.setCode(400);
            }
        }catch (Exception ex){
            rs.setDescription("ERROR OCCURRED TRY AGAIN");
            rs.setCode(400);
            ex.printStackTrace();
        }
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }



    @GetMapping(value = "/student/{uuid}")
    public ResponseEntity<Object> findByStudent(@PathVariable String uuid ){
        ResponseBean rs=new ResponseBean();
        try{
            Student cws=studentService.findByUuid(uuid);
            if(cws!=null){
                List<CourseWorkStudent> courseWorkStudents=cwsDao.findByStudentId(cws.getId());
                rs.setDescription(" NOT FOUND");
                rs.setCode(400);
                rs.setObject(courseWorkStudents);
            }else{
                rs.setDescription(" NOT FOUND");
                rs.setCode(400);
            }
        }catch (Exception ex){

            rs.setDescription("ERROR OCCURRED TRY AGAIN");
            rs.setCode(400);
            ex.printStackTrace();

        }
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }


    @GetMapping(value = "/course/{uuid}")
    public ResponseEntity<Object> findByCourse(@PathVariable String uuid ){
        ResponseBean rs=new ResponseBean();
        try{
            CourseWork cw=courseWorkDao.findByUuid(uuid);
            if(cw!=null){
                List<CourseWorkStudent> courseWorkStudents=cwsDao.findByCourseWorkId(cw.getId());
                rs.setDescription(" FOUND");
                rs.setCode(200);
                rs.setObject(courseWorkStudents);
            }else{
                rs.setDescription(" NOT FOUND");
                rs.setCode(400);
            }
        }catch (Exception ex){
            rs.setDescription("ERROR OCCURRED TRY AGAIN");
            rs.setCode(400);
            ex.printStackTrace();

        }
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }




    
    public static class InnerCourseWorkStudent{
      
        private String courseWorkUuid;
        private String studentUuid;
        private double mark;

        public String getCourseWorkUuid() {
            return courseWorkUuid;
        }

        public void setCourseWorkUuid(String courseWorkUuid) {
            this.courseWorkUuid = courseWorkUuid;
        }

        public String getStudentUuid() {
            return studentUuid;
        }

        public void setStudentUuid(String studentUuid) {
            this.studentUuid = studentUuid;
        }

        public double getMark() {
            return mark;
        }

        public void setMark(double mark) {
            this.mark = mark;
        }
    }
}
