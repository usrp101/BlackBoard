package com.example.demo.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.demo.Domain.Announcement;
import com.example.demo.Domain.AnnouncementDocument;
import com.example.demo.Domain.Comment;
import com.example.demo.Domain.Course;
import com.example.demo.Domain.CourseMaterialType;
import com.example.demo.Service.AnnouncementDocumentService;
import com.example.demo.Service.AnnouncementService;
import com.example.demo.Service.CommentService;
import com.example.demo.Service.CourseService;
import com.example.demo.util.Messages;
import com.example.demo.util.ResponseBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/announcements")
public class AnnouncementController {
    @Autowired
    private AnnouncementService aService;
    @Autowired
    private CommentService cService;
    @Autowired
    private AnnouncementDocumentService adService;
    @Autowired
    private CourseService courseService;

    @PostMapping(value = "/save")
    public ResponseEntity<Object> create(@RequestParam Map<String, String> announcement,
            @RequestParam("files") MultipartFile[] files) {
        ResponseBean rs = new ResponseBean();
        try {
            Announcement a = new Announcement();
            List<MultipartFile> fil = Arrays.asList(files);
            if (fil != null) {
                Object o = adService.upload(fil, a.getUuid());
                if (!o.equals("error")) {
                    a.setTitle(announcement.get("title"));
                    Boolean isG = Boolean.parseBoolean(announcement.get("isGeneral"));
                    if (isG == true) {
                        a.setIsGeneral(isG);
                    } else {
                        a.setIsGeneral(false);
                        Course course = courseService.findByUuid(announcement.get("courseUuid"));
                        if (course != null) {
                            a.setCourse(course);
                            if (announcement.get("isCourseMaterial") != null
                                    || !announcement.get("isCourseMaterial").equalsIgnoreCase("false")) {
                                a.setIsCourseMaterial(true);
                                CourseMaterialType ctype = aService.getType(announcement.get("courseMaterialType"));
                                if (ctype != null) {
                                    a.setCourseMaterialType(ctype);
                                } else {
                                    rs.setCode(400);
                                    rs.setDescription("invalid course material type");
                                    return new ResponseEntity<>(rs, HttpStatus.OK);
                                }
                            }
                        } else {
                            rs.setCode(404);
                            rs.setDescription("course not found");
                            return new ResponseEntity<>(rs, HttpStatus.OK);
                        }
                    }

                    a.setDescription(announcement.get("description"));
                    a.setUserReferenceId(announcement.get("userId"));
                    aService.create(a);
                    List<String> paths = (List<String>) o;
                    for (String p : paths) {
                        AnnouncementDocument ad = new AnnouncementDocument();
                        ad.setAnnouncement(a);
                        ad.setPath(p);
                        String filename = p.split("___")[1];
                        ad.setName(filename);
                        adService.create(ad);
                    }
                    rs.setCode(Messages.SUCCESS_CODE);
                    rs.setDescription("Success");
                    rs.setObject(a);
                }
            } else {
                rs.setCode(300);
                rs.setDescription("null files");
                rs.setObject(a.getUuid());
            }
        } catch (Exception e) {
            e.printStackTrace();
            rs.setCode(Messages.ERROR_CODE);
            rs.setDescription("Error Occured, please try again");
        }
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }

    @PostMapping(value = "/{uuid}/comments/save")
    public ResponseEntity<Object> createComment(@RequestBody Comment c, @PathVariable String uuid) {
        ResponseBean rs = new ResponseBean();
        try {
            Optional<Announcement> a = aService.findByUuid(uuid);
            if (a.isPresent()) {
                c.setReferenceId(a.get().getId());
                c.setReferenceName("comment");
                cService.create(c);
                rs.setCode(200);
                rs.setDescription("success");
                rs.setObject(c);
            } else {
                rs.setCode(404);
                rs.setDescription("announcement Not found");
            }
        } catch (Exception e) {
            // TODO: handle exception
            rs.setCode(300);
            rs.setDescription("Error Occured");
        }
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }

    @GetMapping(value = "/details/{uuid}")
    public ResponseEntity<Object> getAnnouncementDetails(@PathVariable String uuid) {
        ResponseBean rs = new ResponseBean();
        try {
            Optional<Announcement> a = aService.findByUuid(uuid);
            if (a.isPresent()) {
                List<AnnouncementDocument> documents = adService.findAllByAnnouncement(a.get().getId());
                List<Comment> comments = cService.findByAnnouncement(a.get().getId());
                Map<String, Object> map = new HashMap<>();
                map.put("announcement", a);
                map.put("documents", documents);
                map.put("comments", comments);
                rs.setCode(200);
                rs.setDescription("success");
                rs.setObject(map);
            } else {
                rs.setCode(404);
                rs.setDescription("announcement not found");
            }
        } catch (Exception e) {
            rs.setCode(300);
            rs.setDescription("Error Occured");
        }
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }

    @GetMapping(value = "/{uuid}")
    public ResponseEntity<Object> findByuuid(@PathVariable String uuid) {
        ResponseBean rs = new ResponseBean();
        try {
            Optional<Announcement> a = aService.findByUuid(uuid);
            if (a.isPresent()) {
                rs.setCode(200);
                rs.setDescription("success");
                rs.setObject(a.get());
            } else {
                rs.setCode(404);
                rs.setDescription("Not found");
            }
        } catch (Exception e) {
            // TODO: handle exception
            rs.setCode(300);
            rs.setDescription("Error Occured");
        }
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }

}