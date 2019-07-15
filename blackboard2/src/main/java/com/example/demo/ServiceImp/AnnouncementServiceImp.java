package com.example.demo.ServiceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Dao.AnnouncementDao;
import com.example.demo.Domain.Announcement;
import com.example.demo.Domain.CourseMaterial;
import com.example.demo.Domain.CourseMaterialType;
import com.example.demo.Service.AnnouncementService;

@Service
public class AnnouncementServiceImp implements AnnouncementService {
    @Autowired
    private AnnouncementDao dao;

    @Override
    public Announcement create(Announcement a) {
        return dao.save(a);
    }

    @Override
    public Announcement update(Announcement a) {
        return dao.save(a);
    }

    @Override
    public void delete(Announcement a) {
        dao.delete(a);
    }

    @Override
    public Optional<Announcement> findByid(long id) {
        return dao.findById(id);
    }

    @Override
    public Optional<Announcement> findByUuid(String uuid) {
        return dao.findByUuid(uuid);
    }

    @Override
    public CourseMaterialType getType(String type) {
        CourseMaterialType[] vals = CourseMaterialType.values();
        for (CourseMaterialType cm : vals) {
            if(cm.toString().equalsIgnoreCase(type)){
                return cm;
            }
        }
        return null;
    }

    

    @Override
    public List<Announcement> findCourseMaterialsByCourseId(long id) {
        return dao.findByCourseIdAndIsCourseMaterial(id, true);
    }

    @Override
    public List<Announcement> findAnnouncementsByCourseId(long id) {
        return dao.findByCourseIdAndIsCourseMaterial(id, false);
    }

    @Override
    public List<Announcement> findAnnouncementsByUserId(String id) {
        return dao.findByUserReferenceIdAndIsGeneral(id, true);
    }

    @Override
    public List<Announcement> findAllGeneralAnnouncements() {
        return dao.findByIsGeneral(true);
    }

}
