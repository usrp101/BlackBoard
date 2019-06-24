package com.example.demo.Dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.Domain.Comments;

public interface CommentDao extends CrudRepository<Comments, Integer> {

}
