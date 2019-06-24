package com.example.demo.Domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Comments {
@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
private int comId;
@ManyToOne
private String Description;


public int getComId() {
	return comId;
}
public void setComId(int comId) {
	this.comId = comId;
}
public String getDescription() {
	return Description;
}
public void setDescription(String description) {
	Description = description;
}




}
