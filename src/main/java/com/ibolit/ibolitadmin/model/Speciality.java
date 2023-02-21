package com.ibolit.ibolitadmin.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "specialities")
public class Speciality {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_speciality")
	private Integer id_speciality;
	@Column(name = "speciality_name")
	private String specialityName;
	public Integer getId_speciality() {
		return id_speciality;
	}
	public void setId_speciality(Integer id_speciality) {
		this.id_speciality = id_speciality;
	}
	public String getSpeciality_name() {
		return specialityName;
	}
	public void setSpeciality_name(String speciality_name) {
		this.specialityName = speciality_name;
	}
	
}
