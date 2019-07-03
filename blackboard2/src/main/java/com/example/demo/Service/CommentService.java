package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import com.example.demo.Domain.Comment;


public interface CommentService {
    public abstract Comment create(Comment a);
    public abstract Comment update(Comment a);
    public abstract void delete(Comment a);
    public abstract Optional<Comment> findByid(long id);
    public abstract List<Comment> findByAnnouncement(long id);
}
