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
	private long id;
 
	@Column(updatable = false)
	private String uuid=UUID.randomUUID().toString();

	private double marks;
	 @ManyToOne
	 private Student student;

	@ManyToOne
	private Course course;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
