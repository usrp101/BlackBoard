package com.example.demo.ServiceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.Dao.UsersDao;
import com.example.demo.Domain.Student_course;
import com.example.demo.Domain.Users;
import com.example.demo.Service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
	
	@Autowired
	public UsersDao userDao;
	

	@Override
	public Users findone(long id) {
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

	@Override
	public Users findByUserId(String userId) {
		Users users = null;
		Optional<Users> opusers =Optional.ofNullable( userDao.findByUserId(userId));
		if (opusers.isPresent()) {
			users = opusers.get();
		}
		return (users != null) ? users : null;
	}

	@Override
	public Users findByUuid(String uuid) {
		Users users = null;
		Optional<Users> opusers = Optional.ofNullable(userDao.findByUuid(uuid));
		if (opusers.isPresent()) {
			users = opusers.get();
		}
		return (users != null) ? users : null;
	}

}
