package com.example.demo.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Domain.Comments;

public interface CommentDao extends JpaRepository<Comments, Integer> {

}
