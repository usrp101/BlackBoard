package com.example.demo.ServiceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.Dao.UsersDao;
import com.example.demo.Domain.Student_course;
import com.example.demo.Domain.Users;
import com.example.demo.Service.UserService;

public class UserServiceImp implements UserService {
	
	@Autowired
	public UsersDao userDao;
	

	@Override
	public Users findone(String id) {
		Users users = null;
		Optional<Users> opusers =userDao.findById(id); 
		if(opusers.isPresent()) {
			users = opusers.get();
		}
		return (users != null) ? users : null;
	}

	@Override
	public Users create(Users users) {
		userDao.save(users);
		return (users != null) ? users : null;
	}

	@Override
	public List<Users> findAll() {
		List<Users> list = new ArrayList<Users>();
		list =   (List<Users>) userDao.findAll();
		return list;
	}

}
