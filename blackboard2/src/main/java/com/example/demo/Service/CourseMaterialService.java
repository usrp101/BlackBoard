package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import com.example.demo.Domain.CourseMaterial;

public interface CourseMaterialService {
    public abstract CourseMaterial create(CourseMaterial cm);
    public abstract CourseMaterial update(CourseMaterial cm);
    public abstract void delete(CourseMaterial cm);
    public abstract Optional<CourseMaterial> findById(Long id);
    public abstract Optional<CourseMaterial> findByUuid(String uuid);
    public abstract List<CourseMaterial> findAll();
    public abstract List<CourseMaterial> findAllByCourse(long courseId);
}