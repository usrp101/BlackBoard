package com.example.demo.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Domain.Users;

public interface UsersDao extends JpaRepository<Users, Long>  {

}
