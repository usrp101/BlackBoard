package com.example.demo.ServiceImp;

import java.util.Optional;

import com.example.demo.Dao.AnnouncementDocumentDao;
import com.example.demo.Domain.AnnouncementDocument;
import com.example.demo.Service.AnnouncementDocumentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnnouncementDocumentServiceImpl implements AnnouncementDocumentService {
    @Autowired
    private AnnouncementDocumentDao dao;

    @Override
    public AnnouncementDocument create(AnnouncementDocument a) {
        return dao.save(a);
    }

    @Override
    public AnnouncementDocument update(AnnouncementDocument a) {
        return dao.save(a);
    }

    @Override
    public void delete(Long id) {
        dao.deleteById(id);
    }

    @Override
    public Optional<AnnouncementDocument> findByid(long id) {
        return dao.findById(id);
    }
    
}