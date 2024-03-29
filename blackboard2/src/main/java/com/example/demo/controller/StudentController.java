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
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private Student_courseService stCourse;
    @RequestMapping(value = "/save", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> save(@RequestBody Student st) {
        ResponseBean responseBean = new ResponseBean();
        //TODO: process POST request
        try {
            Student u=studentService.findByStudentId(st.getStudentId());
            if(u==null){
                Student nu=studentService.create(st);
                responseBean.setCode(Messages.SUCCESS_CODE);
                responseBean.setDescription(Messages.save);
                responseBean.setObject(nu);
            }else{
                responseBean.setCode(Messages.ERROR_CODE);
                responseBean.setDescription("Student of this id is already registered");
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



//    Saving oneStudent
    @RequestMapping(value = "/saveone", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveOne(@RequestBody InnerStudent st) {
        ResponseBean responseBean = new ResponseBean();
        //TODO: process POST request
        try {
            Student u=studentService.findByStudentId(st.id);
            Course c=courseService.findByUuid(st.courseUuid);
            if(c!=null){
                if(u==null){
                    Student student=new Student();
                    student.setEmail(st.email);
                    student.setFirstname(st.name);
                    student.setStudentId(st.id);
                    student.setLastname("");
                    Student nu=studentService.create(student);

                    Student ost=studentService.findByStudentId(st.id);
                    if(ost!=null){
                        Student_course stc=new Student_course();
                        stc.setStudent(ost);
                        stc.setCourse(c);
                        stCourse.create(stc);
                        responseBean.setCode(Messages.SUCCESS_CODE);
                        responseBean.setDescription(Messages.save);
                    }else{
                        responseBean.setCode(Messages.ERROR_CODE);
                        responseBean.setDescription(Messages.error);
                    }

                }else{
                    Student_course stc=new Student_course();
                    stc.setStudent(u);
                    stc.setCourse(c);
                    stCourse.create(stc);
                    responseBean.setCode(Messages.ERROR_CODE);
                    responseBean.setDescription("Student of this id is already registered");
                    responseBean.setObject(null);
                }

            }else{
                responseBean.setCode(Messages.ERROR_CODE);
                responseBean.setDescription("Course Not Found");
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
     * Update
     * @param uuid

     * @return
     */
    @RequestMapping(value = "/update/{uuid}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> update(@PathVariable("uuid") String uuid, @RequestBody Student st) {
        ResponseBean responseBean = new ResponseBean();
        // TODO: process POST request
        try {
            Student u = studentService.findByUuid(uuid);
            if (u != null) {
                u.setId(u.getId());
                Student nu = studentService.create(st);
                responseBean.setCode(Messages.SUCCESS_CODE);
                responseBean.setDescription(Messages.update);
                responseBean.setObject(nu);
            } else {
                responseBean.setCode(Messages.ERROR_CODE);
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
    /**
     * Find BY Student Id
     * @param id
     * @return
     */
    @RequestMapping(value = "/student/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findByUserId(@PathVariable("id") String id) {
        ResponseBean responseBean = new ResponseBean();
        // TODO: process POST request
        try {

            responseBean.setCode(Messages.SUCCESS_CODE);
            responseBean.setDescription("");
            responseBean.setObject(studentService.findByStudentId(id));

        } catch (Exception e) {
            // TODO: handle exception
            responseBean.setCode(Messages.ERROR_CODE);
            responseBean.setDescription(Messages.error);
            responseBean.setObject(null);
        }
        return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
    }


    /**
     * Find BY UseId
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
            responseBean.setObject(studentService.findByUuid(uuid));

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
            responseBean.setObject(studentService.findAll());

        } catch (Exception e) {
            // TODO: handle exception
            responseBean.setCode(Messages.ERROR_CODE);
            responseBean.setDescription(Messages.error);
            responseBean.setObject(null);
        }
        return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
    }





    private static class InnerStudent{
        private String name;
        private String id;
        private String email;
        private String courseUuid;

        public String getCourseUuid() {
            return courseUuid;
        }

        public void setCourseUuid(String courseUuid) {
            this.courseUuid = courseUuid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

}
