package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import com.example.demo.Domain.Announcement;
import com.example.demo.Domain.CourseMaterialType;

public interface AnnouncementService {
    public abstract Announcement create(Announcement a);
    public abstract Announcement update(Announcement a);
    public  void delete(Announcement a);
    public abstract Optional<Announcement> findByid(long id);
    public abstract Optional<Announcement> findByUuid(String uuid);
    public abstract CourseMaterialType getType(String type);
    List<Announcement> findCourseMaterialsByCourseId(long id);
    List<Announcement> findAnnouncementsByCourseId(long id);
	public abstract List<Announcement> findAnnouncementsByUserId(String id);
	public abstract List<Announcement> findAllGeneralAnnouncements();
}
