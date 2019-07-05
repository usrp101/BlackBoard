package com.example.demo.Domain;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Announcement implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String title;
	@Column(updatable = false)
	private String uuid = UUID.randomUUID().toString();
	private String description;
	private Boolean isGeneral;
	private Boolean isCourseMaterial;
	private CourseMaterialType courseMaterialType;
	private String userReferenceId;
	@JsonIgnore
	@ManyToOne
	private Course course;

	public String getTitle() {
		return title;
	}

	public CourseMaterialType getCourseMaterialType() {
		return courseMaterialType;
	}

	public void setCourseMaterialType(CourseMaterialType courseMaterialType) {
		this.courseMaterialType = courseMaterialType;
	}

	public Boolean getIsCourseMaterial() {
		return isCourseMaterial;
	}

	public void setIsCourseMaterial(Boolean isCourseMaterial) {
		this.isCourseMaterial = isCourseMaterial;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsGeneral() {
		return isGeneral;
	}

	public void setIsGeneral(Boolean isGeneral) {
		this.isGeneral = isGeneral;
	}


	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUserReferenceId() {
		return userReferenceId;
	}

	public void setUserReferenceId(String userReferenceId) {
		this.userReferenceId = userReferenceId;
	}

}
