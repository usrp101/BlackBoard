package com.example.demo.Service;

import java.util.List;

import com.example.demo.Domain.Comments;

public interface CommentService {
	Comments findone(Integer comId);
	Comments create(Comments comments);
	List<Comments>findAll();
}
