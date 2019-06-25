package com.example.demo.Domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Course {
	@Id
	 private String courseCode;
	   private String courseName;
	   private String courseGroup;
	   @ManyToOne
	   private Users teacher;


}
