package com.example.demo.ServiceImp;

import java.util.List;
import java.util.Optional;

import com.example.demo.Dao.CommentDao;
import com.example.demo.Domain.Comment;
import com.example.demo.Service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImp implements CommentService {
    @Autowired
    private CommentDao dao;

    @Override
    public Comment create(Comment a) {
        return dao.save(a);
    }

    @Override
    public Comment update(Comment a) {
        return dao.save(a);
    }

    @Override
    public void delete(Comment a) {
        dao.delete(a);
    }

    @Override
    public Optional<Comment> findByid(long id) {
        return dao.findById(id);
    }

    @Override
    public List<Comment> findByAnnouncement(long id) {
        return dao.findByReferenceNameAndReferenceId("comment",id);
    }

}
