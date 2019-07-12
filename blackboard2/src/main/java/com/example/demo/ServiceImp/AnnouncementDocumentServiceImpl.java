package com.example.demo.ServiceImp;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.Dao.AnnouncementDocumentDao;
import com.example.demo.Domain.AnnouncementDocument;
import com.example.demo.Service.AnnouncementDocumentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    @Override
    public Object upload(List<MultipartFile> files, String aId) {
        return uploadDoc(files, aId);
    }

    public static Object uploadDoc(List<MultipartFile> files, String aId) {
        try {
            String parent = "BLACKBOARD_FILES";
            File f = new File(parent);
            List<String> paths = new ArrayList<>();
            for (MultipartFile file : files) {
                String filename = aId + "___" + file.getOriginalFilename();
                if (!f.exists())
                    f.mkdir();
                File sub = new File(f, "AnnouncementDocs");
                if (!sub.exists())
                    sub.mkdir();

                byte[] bytes = file.getBytes();
                Path path = Paths.get(sub.getPath() + "/" + filename);
                Files.write(path, bytes);
                paths.add(path.toString());

            }
            return paths;
        } catch (Exception e) {
            return "error";
        }

    }

    @Override
    public Optional<AnnouncementDocument> findByUuid(String uuid) {
        return dao.findByUuid(uuid);
    }

    @Override
    public List<AnnouncementDocument> findAllByAnnouncement(Long id) {
        return dao.findByAnnouncementId(id);
    }
}