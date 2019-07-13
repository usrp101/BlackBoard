package com.example.demo.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
                        System.out.println(p);
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


    /**
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/user/{id}/course/{cid}")
    public ResponseEntity<Object> findByUser(@PathVariable("id") String id,@PathVariable("cid")String cid) {
        ResponseBean rs = new ResponseBean();
        try {
            Course c=courseService.findByUuid(cid);
                    rs.setCode(200);
                    rs.setDescription("success");
                    rs.setObject(aService.findByUserReferenceId(id,c.getId()));
        } catch (Exception e) {
            // TODO: handle exception
            rs.setCode(300);
            rs.setDescription("Error Occured");
        }
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }


    // download Loan Document file
    @RequestMapping(path = "/documents/download/{uuid}", method = RequestMethod.GET)
    public ResponseEntity<Resource> download( @PathVariable("uuid") String uuid) throws IOException {

        AnnouncementDocument ad=adService.findByUuid(uuid).get();

        String filePath = ad.getPath();
        File file = new File(filePath);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Content-Disposition", "inline; filename=" + file.getName());
        Path path = Paths.get(filePath);
        byte[] b = Files.readAllBytes((path));
        ByteArrayInputStream bis = new ByteArrayInputStream(b);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        // InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok().headers(headers).contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream")).body(new InputStreamResource(bis));
    }


    @GetMapping(value = "/files/{uuid}")
    public ResponseEntity<Object> file(@PathVariable("uuid") String uuid) {
        ResponseBean rs = new ResponseBean();
        try {
            Announcement a=aService.findByUuid(uuid).get();
            rs.setCode(200);
            rs.setDescription("success");
            rs.setObject(adService.findAllByAnnouncement(a.getId()  ));
        } catch (Exception e) {
            // TODO: handle exception
            rs.setCode(300);
            rs.setDescription("Error Occured");
        }
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }


}