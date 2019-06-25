package com.example.demo.Domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Users {
	@Id
	private String id;
	private String firstname;
	private String lastname;
	private String email;
	private String role;
	private String passcode;

}
