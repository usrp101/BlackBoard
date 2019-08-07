package com.example.demo.controller;



import javax.servlet.http.HttpServletRequest;

import com.example.demo.Domain.Course;
import com.example.demo.Domain.Users;
import com.example.demo.Service.CourseService;
import com.example.demo.Service.UserService;
import com.example.demo.util.Messages;
import com.example.demo.util.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/save", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> save(@RequestBody InnerCourse c,HttpServletRequest request) {
        ResponseBean responseBean = new ResponseBean();
        //TODO: process POST request
  
        try {


                Course course=new Course();
                Course courseCheck=courseService.findByCourseCode(c.getCourseCode());
               if(courseCheck==null){
                   course.setCourseCode(c.courseCode);
                   course.setCourseGroup(c.courseGroup);
                   course.setCourseName(c.courseName);
                   course.setAcademicyear(c.academicYear);
                   course.setSemester(c.semester);
                   course.setTeacherId(c.teacher);
                   Course nc=courseService.create(course);
                       responseBean.setCode(Messages.SUCCESS_CODE);
                       responseBean.setDescription("SAVE SUCCESSFULLY");
                       responseBean.setObject(nc);
               }else{
                   responseBean.setCode(Messages.ERROR_CODE);
                   responseBean.setDescription("EXIST");
                   responseBean.setObject(null);
               }



        } catch (Exception e) {
            //TODO: handle exception
            responseBean.setCode(Messages.ERROR_CODE);
            responseBean.setDescription(Messages.error);
            responseBean.setObject(null);
            e.printStackTrace();
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
    public ResponseEntity<Object> update(@PathVariable String uuid, @RequestBody InnerCourse c) {
        ResponseBean responseBean = new ResponseBean();
        // TODO: process POST request
        try {
             Course course=courseService.findByUuid(uuid);

            if (c != null) {
                Course upc=new Course();
                upc.setTeacherId(c.teacher);
                upc.setCourseName(c.courseName);
                upc.setCourseGroup(c.courseGroup);
                upc.setCourseCode(c.courseCode);
                upc.setId(course.getId());
                Course nu = courseService.create(upc);
                responseBean.setCode(Messages.SUCCESS_CODE);
                responseBean.setDescription("");
                responseBean.setObject(upc);
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
     * Find BY Course Code
     * @param
     * @return
     */
    @RequestMapping(value = "/course/{code}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findByUserId(@PathVariable("code") String code) {
        ResponseBean responseBean = new ResponseBean();
        // TODO: process POST request
        try {

            responseBean.setCode(Messages.SUCCESS_CODE);
            responseBean.setDescription("FOUND");
            responseBean.setObject(courseService.findByCourseCode(code));

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
            responseBean.setObject(courseService.findByUuid(uuid));

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
            responseBean.setObject(courseService.findAll());

        } catch (Exception e) {
            // TODO: handle exception
            responseBean.setCode(Messages.ERROR_CODE);
            responseBean.setDescription(Messages.error);
            responseBean.setObject(null);
        }
        return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
    }

    @RequestMapping(value = "/teacher/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findByTeacher(@PathVariable("id")String id) {
        ResponseBean responseBean = new ResponseBean();
        // TODO: process POST request
        try {
            System.out.println(id);
            if(id!=null){

                responseBean.setCode(Messages.SUCCESS_CODE);
                responseBean.setDescription("");
                responseBean.setObject(courseService.findByTeacherId(Integer.parseInt(id)));

            }else{

            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            responseBean.setCode(Messages.ERROR_CODE);
            responseBean.setDescription(Messages.error);
            responseBean.setObject(null);
        }
        return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
    }


    public  static class InnerCourse{
        private String courseCode;
        private String courseName;
        private String courseGroup;
        private int teacher;
        private String academicYear;
        private String semester;

        public String getCourseCode() {
            return courseCode;
        }

        public String getAcademicYear() {
            return academicYear;
        }

        public void setAcademicYear(String academicYear) {
            this.academicYear = academicYear;
        }

        public String getSemester() {
            return semester;
        }

        public void setSemester(String semester) {
            this.semester = semester;
        }

        public void setCourseCode(String courseCode) {
            this.courseCode = courseCode;
        }

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public String getCourseGroup() {
            return courseGroup;
        }

        public void setCourseGroup(String courseGroup) {
            this.courseGroup = courseGroup;
        }

        public int getTeacher() {
            return teacher;
        }

        public void setTeacher(int teacher) {
            this.teacher = teacher;
        }
    }


}
