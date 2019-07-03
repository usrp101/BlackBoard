package com.example.demo.controller;

import com.example.demo.Domain.Course;
import com.example.demo.Domain.Student;
import com.example.demo.Domain.Student_course;
import com.example.demo.Service.CourseService;
import com.example.demo.Service.StudentService;
import com.example.demo.Service.Student_courseService;
import com.example.demo.util.Messages;
import com.example.demo.util.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public class StudentCourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private Student_courseService studentCourseService;

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/save/course/{uuid}", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> save(@PathVariable("uuid")String uuid, @RequestBody List<StudentCourse> c) {
        ResponseBean responseBean = new ResponseBean();
        //TODO: process POST request
        try {
            Course cou=courseService.findByUuid(uuid);
            if(cou!=null){
                for (StudentCourse stc:c) {
                    Student st=studentService.findByStudentId(stc.studentCode);
                    Student_course studCourse=new Student_course();
                    if(st!=null){
                        studCourse.setCourse(cou);
                        studCourse.setStudent(st);
                    }else{
                       Student nst=new Student();
                       nst.setFirstname(stc.firstName);
                       nst.setLastname(stc.lastName);
                       nst.setStudentId(stc.studentCode);
                       studentService.create(nst);
                       Student student=studentService.findByStudentId(nst.getStudentId());
                       studCourse.setStudent(student);
                       studCourse.setCourse(cou);
                    }
                    studCourse.setMarks(stc.marks);
                    studentCourseService.create(studCourse);

                }
                responseBean.setCode(Messages.SUCCESS_CODE);
                responseBean.setDescription(Messages.save);
                responseBean.setObject(c);
            }else{
                responseBean.setCode(Messages.ERROR_CODE);
                responseBean.setDescription(Messages.error);
                responseBean.setObject(null);
            }


        } catch (Exception e) {
            //TODO: handle exception
            responseBean.setCode(Messages.ERROR_CODE);
            responseBean.setDescription(Messages.error);
            responseBean.setObject(null);
        }
        return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
    }


    /**
     * Update Course
     * @param uuid
     * @param c
     * @return
     */
    @RequestMapping(value = "/update/{uuid}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> update(@PathVariable String uuid, @RequestBody StudentCourse c) {
        ResponseBean responseBean = new ResponseBean();
        // TODO: process POST request
        try {
           Student_course sCourse=studentCourseService.findByUuid(uuid);
            if (c != null) {
                sCourse.setMarks(c.marks);
                Student_course nu = studentCourseService.create(sCourse);
                responseBean.setCode(Messages.SUCCESS_CODE);
                responseBean.setDescription(Messages.update);
                responseBean.setObject(sCourse);
            } else {
                responseBean.setCode(Messages.ERROR_CODE);
                responseBean.setDescription(Messages.update);
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

    /**
     * Find BY Uuid
     *
     * @param uuid
     * @return
     */
    @RequestMapping(value = "/{uuid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findByUserUuid(@PathVariable("uuid") String uuid) {
        ResponseBean responseBean = new ResponseBean();
        // TODO: process POST request
        try {

            responseBean.setCode(Messages.SUCCESS_CODE);
            responseBean.setDescription("");
            responseBean.setObject(studentCourseService.findByUuid(uuid));

        } catch (Exception e) {
            // TODO: handle exception
            responseBean.setCode(Messages.ERROR_CODE);
            responseBean.setDescription(Messages.error);
            responseBean.setObject(null);
        }
        return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
    }


    /**
     * Find all
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findAll() {
        ResponseBean responseBean = new ResponseBean();
        // TODO: process POST request
        try {

            responseBean.setCode(Messages.SUCCESS_CODE);
            responseBean.setDescription("");
            responseBean.setObject(studentCourseService.findAll());

        } catch (Exception e) {
            // TODO: handle exception
            responseBean.setCode(Messages.ERROR_CODE);
            responseBean.setDescription(Messages.error);
            responseBean.setObject(null);
        }
        return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
    }

    @RequestMapping(value = "/course/{uuid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findByTeacher(@PathVariable("uuid")String uuid) {
        ResponseBean responseBean = new ResponseBean();
        // TODO: process POST request
        try {

            Course c=courseService.findByUuid(uuid);
            if(c!=null){

                responseBean.setCode(Messages.SUCCESS_CODE);
                responseBean.setDescription("");
                responseBean.setObject(studentCourseService.findByCourseId(c.getId()));

            }else{
                responseBean.setCode(Messages.NOT_FOUND);
                responseBean.setDescription(Messages.not_found);
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



    public static class StudentCourse{
        private String studentCode;
        private String firstName;
        private String lastName;
        private double marks;

        public double getMarks() {
            return marks;
        }

        public void setMarks(double marks) {
            this.marks = marks;
        }

        public String getStudentCode() {
            return studentCode;
        }

        public void setStudentCode(String studentCode) {
            this.studentCode = studentCode;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }

}
