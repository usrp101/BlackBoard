package com.example.demo.Dao;

import java.util.List;
import java.util.Optional;

import com.example.demo.Domain.Announcement;

import org.springframework.data.jpa.repository.JpaRepository;


public interface AnnouncementDao extends JpaRepository<Announcement, Long> {
    public Optional<Announcement> findByUuid(String uuid);
    public List<Announcement> findByCourseIdAndIsCourseMaterial(long id,boolean isCm);
    public List<Announcement> findByUserReferenceIdAndIsGeneral(String id,boolean isG);
	public List<Announcement> findByIsGeneral(boolean isG);
}
