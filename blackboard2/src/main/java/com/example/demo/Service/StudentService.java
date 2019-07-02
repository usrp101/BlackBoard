package com.example.demo.Service;

import com.example.demo.Domain.Student;

import java.util.List;

public interface StudentService {

    Student create(Student st);
    Student findByUuid(String uuid);
    List<Student> findAll();
    Student findByStudentId(String id);
    Student findOne(long id);
}
