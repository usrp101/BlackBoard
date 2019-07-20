package com.example.demo.Dao;

import com.example.demo.Domain.Course;
import com.example.demo.Domain.CourseWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CourseWorkDao extends JpaRepository<CourseWork,Long> {

    public List<CourseWork> findByCourseId(long id);

    public List<CourseWork> findByDoneAt(Date dt);


    public CourseWork findByUuid(String uuid);
}
