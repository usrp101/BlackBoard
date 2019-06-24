package com.example.demo.Domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Student_course {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private int stcsId;
@OneToMany(mappedBy="student_course")
private String marks;


public int getStcsId() {
	return stcsId;
}
public void setStcsId(int stcsId) {
	this.stcsId = stcsId;
}
public String getMarks() {
	return marks;
}
public void setMarks(String marks) {
	this.marks = marks;
}



}
