package com.example.demo.ServiceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.Dao.CommentDao;
import com.example.demo.Domain.Comments;
import com.example.demo.Service.CommentService;

public class CommentServiceImp implements CommentService {
	
	@Autowired
	public CommentDao commentDao;

	@Override
	public Comments findone(Integer comId) {
		Comments comments = null;
		Optional<Comments> opcomments =commentDao.findById(comId); 
		if(opcomments.isPresent()) {
			comments = opcomments.get();
		}
		return (comments != null) ? comments : null;
	}

	@Override
	public Comments create(Comments comments) {
		commentDao.save(comments);
		return (comments != null) ? comments : null;
	}

	@Override
	public List<Comments> findAll() {
		List<Comments> list = new ArrayList<Comments>();
		list =   (List<Comments>) commentDao.findAll();
		return list;
	}

}
