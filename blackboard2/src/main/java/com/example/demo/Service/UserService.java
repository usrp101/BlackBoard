package com.example.demo.Service;

import java.util.List;

import com.example.demo.Domain.Users;

public interface UserService {
	Users findone(long id);
	Users  create(Users users);
	List<Users> findAll();
}
