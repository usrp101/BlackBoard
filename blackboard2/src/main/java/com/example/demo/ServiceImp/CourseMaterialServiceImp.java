package com.example.demo.ServiceImp;

import java.util.List;
import java.util.Optional;

import com.example.demo.Dao.CourseMaterialDao;
import com.example.demo.Domain.CourseMaterial;
import com.example.demo.Service.CourseMaterialService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseMaterialServiceImp implements CourseMaterialService {
    @Autowired
    private CourseMaterialDao dao;

    @Override
    public CourseMaterial create(CourseMaterial cm) {
        return dao.save(cm);
    }

    @Override
    public CourseMaterial update(CourseMaterial cm) {
        return dao.save(cm);
    }

    @Override
    public void delete(CourseMaterial cm) {
        dao.delete(cm);
    }

    @Override
    public Optional<CourseMaterial> findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public Optional<CourseMaterial> findByUuid(String uuid) {
        return dao.findByUuid(uuid);
    }

    @Override
    public List<CourseMaterial> findAll() {
        return dao.findAll();
    }

    @Override
    public List<CourseMaterial> findAllByCourse(long courseId) {
        return dao.findAll();
    }
}