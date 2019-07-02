package com.example.demo.Domain;

import java.util.UUID;

import javax.persistence.Column;
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
	private long Id;
 
	@Column(updatable = false)
	private String uuid=UUID.randomUUID().toString();

	private double marks;
	@ManyToOne
	private Student student;

	@ManyToOne
	private Course courseId;
	


    /**
     * @return long return the Id
     */
    public long getId() {
        return Id;
    }

    /**
     * @param Id the Id to set
     */
    public void setId(long Id) {
        this.Id = Id;
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
     * @return double return the marks
     */
    public double getMarks() {
        return marks;
    }

    /**
     * @param marks the marks to set
     */
    public void setMarks(double marks) {
        this.marks = marks;
    }

    /**
     * @return Student return the student
     */
    public Student getStudent() {
        return student;
    }

    /**
     * @param student the student to set
     */
    public void setStudent(Student student) {
        this.student = student;
    }

    /**
     * @return Course return the courseId
     */
    public Course getCourseId() {
        return courseId;
    }

    /**
     * @param courseId the courseId to set
     */
    public void setCourseId(Course courseId) {
        this.courseId = courseId;
    }

}
