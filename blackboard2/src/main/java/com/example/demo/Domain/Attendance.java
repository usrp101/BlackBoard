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
private int atId;
private Date attendanceDate;
private Boolean present;
@ManyToOne
private Student_course studentCourse;

}
