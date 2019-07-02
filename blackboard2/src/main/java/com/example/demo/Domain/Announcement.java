package com.example.demo.Domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Announcement {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int anId;
	private String title;
	private String type;
	private Boolean isGeneral;
	@ManyToOne
	private Course course;

}
