package com.example.demo.Domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.apache.catalina.User;

@Entity
public class Student_course {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int stcsId;
	private double marks;
	@ManyToOne
	private Users student;
	@ManyToOne
	private Course courseId;

}
