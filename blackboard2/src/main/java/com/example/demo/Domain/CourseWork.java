package com.example.demo.Domain;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
public class CourseWork {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String uuid= UUID.randomUUID().toString();
    private String name;
    private int outOf;
    @ManyToOne
    private Course course;
    @Enumerated(EnumType.STRING)
    private WorkType workType;

    @Column(updatable = false)
    private Date doneAt = new Date();

    private String description;

    public String getDescription() {
        return description;
    }

    public int getOutOf() {
        return outOf;
    }

    public void setOutOf(int outOf) {
        this.outOf = outOf;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public WorkType getWorkType() {
        return workType;
    }

    public void setWorkType(WorkType workType) {
        this.workType = workType;
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
}
