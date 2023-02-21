package com.ibolit.ibolitadmin.model;

public class AppointmentMapping {
	
	private Integer id_appointment;
	public Integer getId_appointment() {
		return id_appointment;
	}

	public void setId_appointment(Integer id_appointment) {
		this.id_appointment = id_appointment;
	}

	private String speciality;
	private String dates;
	private String comment_user;
	private Integer simpleUser;


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

	public Integer getSimpleUser() {
		return simpleUser;
	}

	public void setSimpleUser(Integer simpleUser) {
		this.simpleUser = simpleUser;
	}
	
}
