package com.example.demo.Domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Attendance {
@Id
@GeneratedValue(strategy =GenerationType.AUTO )
private long id;
private Boolean present;
@ManyToOne
private Student_course studentCourse;
@ManyToOne
private CourseAttendance courseAttendance;

    /**
     * @return long return the id
     */
    public long getId() {
        return id;
    }

    public CourseAttendance getCourseAttendance() {
        return courseAttendance;
    }

    public void setCourseAttendance(CourseAttendance courseAttendance) {
        this.courseAttendance = courseAttendance;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return Boolean return the present
     */
    public Boolean isPresent() {
        return present;
    }

    /**
     * @param present the present to set
     */
    public void setPresent(Boolean present) {
        this.present = present;
    }

    /**
     * @return Student_course return the studentCourse
     */
    public Student_course getStudentCourse() {
        return studentCourse;
    }

    /**
     * @param studentCourse the studentCourse to set
     */
    public void setStudentCourse(Student_course studentCourse) {
        this.studentCourse = studentCourse;
    }

}
