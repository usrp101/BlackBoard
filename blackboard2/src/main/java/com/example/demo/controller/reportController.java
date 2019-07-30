package com.example.demo.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.demo.Dao.*;
import com.example.demo.Domain.Course;
import com.example.demo.Domain.CourseWork;
import com.example.demo.Domain.CourseWorkStudent;
import com.example.demo.Domain.Student;
import com.example.demo.Domain.Student_course;
import com.example.demo.util.ResponseBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/report")
public class reportController {
    @Autowired
    private StudentDao sDao;
    @Autowired
    private CourseWorkDao cwDao;
    @Autowired
    private CourseDao cDao;
    @Autowired
    private Student_courseDao scDao;
    @Autowired
    private CourseWorkStudentDao cwsDao;


    @GetMapping(value="/marks/course/{uuid}")
    public ResponseEntity<Object> reportMarksByCourse(@PathVariable String uuid) {
        ResponseBean rs = new ResponseBean();
        try {
            Course course = cDao.findByUuid(uuid);
            List<Student_course> scs = scDao.findByCourseId(course.getId());
            List<CourseWork> works = cwDao.findByCourseId(course.getId());
            List<Student> students = new ArrayList<>();
            for(Student_course sc : scs){
                students.add(sc.getStudent());
            }

            List<Map<String,String>> result = new ArrayList<>();
            for(Student st : students){
                double total = 0;
                int cwsTotal = 0;
                Map<String,String> map = new HashMap<>();
                map.put("studentId", st.getStudentId()+"");
                // map.put("studentNames", st.getFirstname());
                for(CourseWork cw : works){
                    cwsTotal+=cw.getOutOf();
                    Optional<CourseWorkStudent> cws = cwsDao.findByStudentIdAndCourseWorkId(st.getId(), cw.getId());
                    if(cws.isPresent()){
                        total+=cws.get().getMarks();
                        map.put(cw.getName()+" /"+cw.getOutOf(), cws.get().getMarks()+"");
                    }else{
                        map.put(cw.getName()+" /"+cw.getOutOf(), "");
                    }
                }
                map.put("total /"+cwsTotal, total+"");
                double grTotal = (total * 20)/cwsTotal;
                map.put("grand total /20", Math.round(grTotal * 10) / 10.0+"");
                result.add(map);
            }
            rs.setCode(200);
            rs.setDescription("success");
            rs.setObject(result);
        } catch (Exception e) {
            e.printStackTrace();
            rs.setCode(300);
            rs.setDescription("error occured");
        }
        return new ResponseEntity<>(rs,HttpStatus.OK);
    }
    

}