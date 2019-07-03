package com.example.demo.Dao;

import java.util.List;
import java.util.Optional;

import com.example.demo.Domain.AnnouncementDocument;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementDocumentDao extends JpaRepository<AnnouncementDocument,Long> {
    public Optional<AnnouncementDocument> findByUuid(String uuid);
    public List<AnnouncementDocument> findByAnnouncementId(long id);
}