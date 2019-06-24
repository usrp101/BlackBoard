package com.example.demo.Domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Annoncement {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int anId;
	@OneToMany(mappedBy="announcement")
	private String title;
	private String type;
	private Boolean isGeneral;
	
	
	public int getAnId() {
		return anId;
	}
	public void setAnId(int anId) {
		this.anId = anId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Boolean getIsGeneral() {
		return isGeneral;
	}
	public void setIsGeneral(Boolean isGeneral) {
		this.isGeneral = isGeneral;
	}
	
	
	
	

}
