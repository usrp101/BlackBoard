package com.example.demo.Service;

import java.util.Optional;

import com.example.demo.Domain.AnnouncementDocument;

public interface AnnouncementDocumentService {
    public abstract AnnouncementDocument create(AnnouncementDocument a);
    public abstract AnnouncementDocument update(AnnouncementDocument a);
    public  void delete(Long id);
    public abstract Optional<AnnouncementDocument> findByid(long id);
}