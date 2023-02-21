package com.ibolit.ibolitadmin.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "appointment")
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_appointment")
	private Integer id_appointment;
	@Column(name = "speciality", length = 50, nullable = false, unique = false)
	private String speciality;
	@Column (name = "dates", length = 50, nullable = false, unique = false)
	private String dates;
	@Column (name = "comment_user", length = 50, nullable = true, unique = false)
	private String comment_user;
	@JoinColumn (name = "fk_user", nullable = false, unique = false)
	@ManyToOne
	private SimpleUser simpleUser;
	public Integer getId_appointment() {
		return id_appointment;
	}
	public void setId_appointment(Integer id_appointment) {
		this.id_appointment = id_appointment;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public String getDates() {
		return dates;
	}
	public void setDates(String dates) {
		this.dates = dates;
	}

	public String getComment_user() {
		return comment_user;
	}
	public void setComment_user(String comment_user) {
		this.comment_user = comment_user;
	}
	public SimpleUser getSimpleUser() {
		return simpleUser;
	}
	public void setSimpleUser(SimpleUser simpleUser) {
		this.simpleUser = simpleUser;
	}
	
}
	