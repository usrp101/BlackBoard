package com.example.demo.Domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Attendance {
@Id
@GeneratedValue(strategy =GenerationType.AUTO )
private long id;
private Date attendanceDate;
private Boolean present;

@ManyToOne
private Student_course studentCourse;



    /**
     * @return long return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return Date return the attendanceDate
     */
    public Date getAttendanceDate() {
        return attendanceDate;
    }

    /**
     * @param attendanceDate the attendanceDate to set
     */
    public void setAttendanceDate(Date attendanceDate) {
        this.attendanceDate = attendanceDate;
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
