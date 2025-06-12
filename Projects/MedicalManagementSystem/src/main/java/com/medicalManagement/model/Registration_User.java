package com.medicalManagement.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.medicalManagement.utils.DBConnection;

public class Registration_User {
	private int patientName;
	private String gender;
	private String contactNumber;
	private Date DateOfBirth;
	private String Address;
	private String  email;
	private String password;
	
	public Registration_User login(String email, String password) {
        Registration_User ru = null;

        String sql = "SELECT * FROM registration WHERE email = ? AND password = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, password); // Optionally hash before this

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                ru = new Registration_User();
                ru.setPatientName(rs.getInt("patient_name"));
                ru.setEmail(rs.getString("email"));
//                patient.setDateOfBirth(rs.getInt());  
                ru.setGender(rs.getString("gender"));
                // Do not expose or store password after login
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ru;
    }
	
	public int getPatientName() {
		return patientName;
	}

	public void setPatientName(int patientName) {
		this.patientName = patientName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public Date getDateOfBirth() {
		return DateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		DateOfBirth = dateOfBirth;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

//	@Override
//	public String toString() {
//		return "User [id=" + id + ", fullName=" + fullName + ", email=" + email + ", password=" + password + "]";
//	}
	
	
	
	
	
	
	
	
}
