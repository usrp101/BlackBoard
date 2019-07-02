package com.example.demo.ServiceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Dao.AnnouncementDao;
import com.example.demo.Domain.Announcement;
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

}
