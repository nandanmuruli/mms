package com.medicalManagement.model;

public class Doctor {
	private int doctorId;
	private String name;
	private String gender;
	private String specialization;
	private String contactNumber;
	private String availability;

	public Doctor() {}

	public Doctor(int doctorId, String name, String gender, String specialization, String contactNumber, String availability) {
		this.doctorId = doctorId;
		this.name = name;
		this.gender = gender;
		this.specialization = specialization;
		this.contactNumber = contactNumber;
		this.availability = availability;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}
} // FIX: Removed extra closing brace from the end