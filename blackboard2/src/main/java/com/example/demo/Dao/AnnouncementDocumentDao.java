package com.example.demo.Dao;

import com.example.demo.Domain.AnnouncementDocument;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementDocumentDao extends JpaRepository<AnnouncementDocument,Long> {
    
}