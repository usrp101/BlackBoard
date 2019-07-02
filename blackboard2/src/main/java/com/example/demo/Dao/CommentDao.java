package com.example.demo.Dao;

import com.example.demo.Domain.Comment;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentDao extends JpaRepository<Comment, Long> {

}
