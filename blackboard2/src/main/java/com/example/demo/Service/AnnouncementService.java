package com.example.demo.Service;

import java.util.Optional;

import com.example.demo.Domain.Announcement;

public interface AnnouncementService {
    public abstract Announcement create(Announcement a);
    public abstract Announcement update(Announcement a);
    public  void delete(Announcement a);
    public abstract Optional<Announcement> findByid(long id);
    public abstract Optional<Announcement> findByUuid(String uuid);
}
