package com.example.demo.Domain;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
public class CourseWorkStudent {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String uuid= UUID.randomUUID().toString();

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @ManyToOne
    private Student student;

    @ManyToOne
    private CourseWork courseWork;

    public CourseWork getCourseWork() {
        return courseWork;
    }

    public void setCourseWork(CourseWork courseWork) {
        this.courseWork = courseWork;
    }

    private double marks;

    @Column(updatable = false)
    private Date doneAt = new Date();

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Date getDoneAt() {
        return doneAt;
    }

    public void setDoneAt(Date doneAt) {
        this.doneAt = doneAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }




}
