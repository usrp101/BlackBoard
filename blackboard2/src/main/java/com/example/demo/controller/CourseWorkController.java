package com.example.demo.controller;

import com.example.demo.Dao.CourseWorkDao;
import com.example.demo.Domain.Course;
import com.example.demo.Domain.CourseWork;
import com.example.demo.Domain.WorkType;
import com.example.demo.Service.CourseService;
import com.example.demo.util.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("courseworks")
public class CourseWorkController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseWorkDao dao;


    @PostMapping(value = "/save")
    public ResponseEntity<Object> createCw(@RequestBody InnerCourse c) {
        ResponseBean rs = new ResponseBean();
        try {
            Optional<Course> a = Optional.ofNullable(courseService.findByUuid(c.courseUuid));
            if (a.isPresent()) {
                CourseWork cw=new CourseWork();
                cw.setCourse(a.get());
                cw.setName(c.name);
                cw.setWorkType(WorkType.valueOf(c.type));
                cw.setDescription(c.description);
                cw.setOutOf(c.getOutOf());
                dao.save(cw);
                rs.setCode(200);
                rs.setObject(cw);
                rs.setDescription("COURSE WORK IS CREATED SUCCESSFULLY");
            } else {
                rs.setCode(404);
                rs.setDescription("COURSE NOT FOUND");
            }
        } catch (Exception e) {
            rs.setCode(300);
            rs.setDescription("Error Occured, try again");
        }
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }


    @PutMapping(value = "/update/{uuid}")
    public ResponseEntity<Object> updateCw(@RequestBody InnerCourse c, @PathVariable("uuid")String uuid) {
        ResponseBean rs = new ResponseBean();
        try {
            Optional<CourseWork> ocw = Optional.ofNullable(dao.findByUuid(uuid));
            Optional<Course> co=Optional.ofNullable(courseService.findByUuid(c.courseUuid));
             if (ocw.isPresent()) {
                 if(co.isPresent()){
                     CourseWork cw=new CourseWork();
                     cw.setCourse(co.get());
                     cw.setName(c.name);
                     cw.setDescription(c.description);
                     cw.setWorkType(WorkType.valueOf(c.type));
                     cw.setId(ocw.get().getId());
                     dao.save(cw);
                     rs.setCode(200);
                     rs.setObject(cw);
                     rs.setDescription("COURSE WORK IS UPDATED SUCCESSFULLY");
                 }else{
                     rs.setCode(404);
                     rs.setDescription("COURSE NOT FOUND");
                 }

            } else {
                rs.setCode(404);
                rs.setDescription("COURSE WORK NOT FOUND");
            }
        } catch (Exception e) {
            // TODO: handle exception
            rs.setCode(300);
            rs.setDescription("Error Occured, try again");
        }
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }





    @GetMapping(value = "/courses/{uuid}")
    public ResponseEntity<Object> findByCourse(@PathVariable String uuid){
        ResponseBean rs = new ResponseBean();
        try{
            Optional<Course> c=Optional.ofNullable(courseService.findByUuid(uuid));
            if(c.isPresent()){
                rs.setObject(dao.findByCourseId(c.get().getId()));
                rs.setCode(200);

            }else{
//                rs.setObject();
                rs.setCode(300);
                rs.setDescription("COURSE NOT FOUND");
            }


        }catch (Exception ex){
            // TODO: handle exception
            rs.setCode(300);
            rs.setDescription("Error Occured, try again");
        }
        return  new ResponseEntity<>(rs,HttpStatus.OK);
    }


    @GetMapping(value = "/{uuid}")
    public ResponseEntity<Object> findByUuid(@PathVariable String uuid){
        ResponseBean rs = new ResponseBean();
        try{
            Optional<CourseWork> c=Optional.ofNullable(dao.findByUuid(uuid));
            if(c.isPresent()){
                rs.setObject(dao.findByCourseId(c.get().getId()));
                rs.setCode(300);

            }else{
//                rs.setObject();
                rs.setCode(300);
                rs.setDescription("COURSE NOT FOUND");
            }


        }catch (Exception ex){
            // TODO: handle exception
            rs.setCode(300);
            rs.setDescription("Error Occured, try again");
        }
        return  new ResponseEntity<>(rs,HttpStatus.OK);
    }


    public  static class InnerCourse{

        private String name;
        private int outOf;
        private String type;
        private String courseUuid;
        private String description;

        public String getDescription() {
            return description;
        }

        public int getOutOf() {
            return outOf;
        }

        public void setOutOf(int outOf) {
            this.outOf = outOf;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCourseUuid() {
            return courseUuid;
        }

        public void setCourseUuid(String courseUuid) {
            this.courseUuid = courseUuid;
        }
    }
}
