package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import com.example.demo.Domain.AnnouncementDocument;

import org.springframework.web.multipart.MultipartFile;

public interface AnnouncementDocumentService {
    public abstract AnnouncementDocument create(AnnouncementDocument a);
    public abstract AnnouncementDocument update(AnnouncementDocument a);
    public  void delete(Long id);
    public abstract Optional<AnnouncementDocument> findByid(long id);
    public abstract Object upload(List<MultipartFile> files, String aId);
    public abstract Optional<AnnouncementDocument> findByUuid(String uuid);
    public abstract List<AnnouncementDocument> findAllByAnnouncement(Long id);
}