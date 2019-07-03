package com.example.demo.Domain;


import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Course {
	   @Id
	   @GeneratedValue(strategy = GenerationType.AUTO)
	   private long id;
	   
	   @Column(unique = true)
	   private String courseCode;

	   @Column(updatable = false)
	   private String uuid=UUID.randomUUID().toString();
	   private String courseName;
	   private String courseGroup;

	   @ManyToOne
	   private Users teacher;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return String return the courseCode
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * @param courseCode the courseCode to set
     */
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    /**
     * @return String return the uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid the uuid to set
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * @return String return the courseName
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * @param courseName the courseName to set
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * @return String return the courseGroup
     */
    public String getCourseGroup() {
        return courseGroup;
    }

    /**
     * @param courseGroup the courseGroup to set
     */
    public void setCourseGroup(String courseGroup) {
        this.courseGroup = courseGroup;
    }

    /**
     * @return Users return the teacher
     */
    public Users getTeacher() {
        return teacher;
    }

    /**
     * @param teacher the teacher to set
     */
    public void setTeacher(Users teacher) {
        this.teacher = teacher;
    }

}
