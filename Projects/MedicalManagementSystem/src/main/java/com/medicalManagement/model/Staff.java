package com.medicalManagement.model;

import java.sql.Time;

public class Staff {
	private int staffId;
	private String name;
	private String designation;
	private String gender;
	private String contactNumber;
	private String specialist;
	private String staff_availability;
	private Time shiftStart;
	private Time shiftEnd;
	private String email;
	private String password;

	public Staff() {}

	// Getters and Setters
	public int getStaffId() { return staffId; }
	public void setStaffId(int staffId) { this.staffId = staffId; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public String getDesignation() { return designation; }
	public void setDesignation(String designation) { this.designation = designation; }
	public String getGender() { return gender; }
	public void setGender(String gender) { this.gender = gender; }
	public String getContactNumber() { return contactNumber; }
	public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
	public String getSpecialist() { return specialist; }
	public void setSpecialist(String specialist) { this.specialist = specialist; }
	public String getStaff_availability() { return staff_availability; }
	public void setStaff_availability(String staff_availability) { this.staff_availability = staff_availability; }
	public Time getShiftStart() { return shiftStart; }
	public void setShiftStart(Time shiftStart) { this.shiftStart = shiftStart; }
	public Time getShiftEnd() { return shiftEnd; }
	public void setShiftEnd(Time shiftEnd) { this.shiftEnd = shiftEnd; }
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
}