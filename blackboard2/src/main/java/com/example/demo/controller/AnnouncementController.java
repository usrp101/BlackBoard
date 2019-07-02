package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import com.example.demo.Service.AnnouncementService;
import com.example.demo.Service.CommentService;
import com.example.demo.util.ResponseBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class AnnouncementController {
    @Autowired
    private AnnouncementService aService;
    private CommentService cService;
    
    // @PostMapping(value="/announcement/save")
    // public ResponseEntity create(@RequestBody Map<String,String> announcement, List<MultipartFile> files) {
    //             ResponseBean rs = new ResponseBean();
    //         try {
    //             // Announce
    //         } catch (Exception e) {
    //             //TODO: handle exception
    //         }
    // }
    
}