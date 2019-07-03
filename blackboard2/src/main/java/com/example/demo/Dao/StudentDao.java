package com.example.demo.Dao;

import com.example.demo.Domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDao extends JpaRepository<Student, Long> {

    public Student findByStudentId(String id);
    public Student findByUuid(String uuid);

}
