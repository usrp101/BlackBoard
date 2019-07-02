package com.example.demo.Domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	 @Column(unique = true)
	 
	private String userId;
	@Column(updatable = false)
	private String uuid=UUID.randomUUID().toString();
	private String firstname;
	private String lastname;
	private String email;
	private String role;
	private String passcode;

}
