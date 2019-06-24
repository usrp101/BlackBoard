package com.example.demo.Dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.Domain.Users;

public interface UsersDao extends CrudRepository<Users, String>   {

}
