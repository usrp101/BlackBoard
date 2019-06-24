package com.example.demo.Domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Attendance {
@Id
@GeneratedValue(strategy =GenerationType.AUTO )
private int atId;
@OneToMany(mappedBy="attendance")
private Date attendanceDate;
private Boolean present;


public int getAtId() {
	return atId;
}
public void setAtId(int atId) {
	this.atId = atId;
}
public Date getAttendanceDate() {
	return attendanceDate;
}
public void setAttendanceDate(Date attendanceDate) {
	this.attendanceDate = attendanceDate;
}
public Boolean getPresent() {
	return present;
}
public void setPresent(Boolean present) {
	this.present = present;
}




}
