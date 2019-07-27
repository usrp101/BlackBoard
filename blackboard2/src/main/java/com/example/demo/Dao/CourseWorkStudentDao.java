package com.example.demo.Dao;

import com.example.demo.Domain.CourseWorkStudent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseWorkStudentDao extends JpaRepository<CourseWorkStudent,Long> {

    public List<CourseWorkStudent> findByStudentId(long id);


    public List<CourseWorkStudent> findByCourseWorkId(long id);


    public CourseWorkStudent findByUuid(String uuid);

    public CourseWorkStudent findByCourseWorkIdAndStudentId(long id,long sid);


}
