package com.example.demo.Dao;

import com.example.demo.Domain.Announcement;

import org.springframework.data.jpa.repository.JpaRepository;


public interface AnnouncementDao extends JpaRepository<Announcement, Long> {

}
