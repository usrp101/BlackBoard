package com.example.demo.ServiceImp;

import com.example.demo.Dao.StudentDao;
import com.example.demo.Domain.Student;
import com.example.demo.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImp implements StudentService {

    @Autowired
    private StudentDao dao;


    @Override
    public Student create(Student st) {

        dao.save(st);
        return (st != null) ? st : null;
    }

    @Override
    public Student findByUuid(String uuid) {
        Student st = null;
        Optional<Student> opst =Optional.ofNullable(dao.findByUuid(uuid));
        if(opst.isPresent()) {
            st = opst.get();
        }
        return (st != null) ? st : null;
    }

    @Override
    public List<Student> findAll() {
        List<Student> list = new ArrayList<>();
        list =   (List<Student>) dao.findAll();
        return list;
    }

    @Override
    public Student findByStudentId(String id) {
        Student st = null;
        Optional<Student> opst =Optional.ofNullable(dao.findByStudentId(id));
        if(opst.isPresent()) {
            st = opst.get();
        }
        return (st != null) ? st : null;
    }

    @Override
    public Student findOne(long id) {
        Student st = null;
        Optional<Student> opst =dao.findById(id);
        if(opst.isPresent()) {
            st = opst.get();
        }
        return (st != null) ? st : null;
    }
}
