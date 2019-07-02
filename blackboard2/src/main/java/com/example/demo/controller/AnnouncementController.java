package com.example.demo.controller;

import com.example.demo.Service.AnnouncementService;
import com.example.demo.Service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class AnnouncementController {
    @Autowired
    private AnnouncementService aService;
    private CommentService cService;
    
    // @PostMapping(value="/announcement/save")
    // public SomeEnityData postMethodName(@RequestBody SomeEnityData entity) {
    //     //TODO: process POST request
        
    //     return entity;
    // }
    
}