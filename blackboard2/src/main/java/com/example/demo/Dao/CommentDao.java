package com.example.demo.Dao;

import java.util.List;

import com.example.demo.Domain.Comment;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentDao extends JpaRepository<Comment, Long> {
    public List<Comment> findByReferenceNameAndReferenceId(String name,long id);
}
