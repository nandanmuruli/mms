package com.medicalManagement.dao;

import com.medicalManagement.model.Staff;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StaffDAOImpl implements StaffDAO {

	@Override
	public Staff getStaffByEmail(String email) throws SQLException {
		String sql = "SELECT * FROM staff WHERE email = ?";
		try (Connection con = DBConnection.getConnection();
			 PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, email);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return mapRowToStaff(rs);
				}
			}
		}
		return null;
	}

	@Override
	public List<Staff> getAllStaff() throws SQLException {
		List<Staff> staffList = new ArrayList<>();
		String sql = "SELECT * FROM staff";
		try (Connection con = DBConnection.getConnection();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				staffList.add(mapRowToStaff(rs));
			}
		}
		return staffList;
	}

	private Staff mapRowToStaff(ResultSet rs) throws SQLException {
		Staff staff = new Staff();
		staff.setStaffId(rs.getInt("staff_id"));
		staff.setName(rs.getString("name"));
		staff.setDesignation(rs.getString("designation"));
		staff.setGender(rs.getString("gender"));
		staff.setContactNumber(rs.getString("contact_number"));
		staff.setSpecialist(rs.getString("specialist"));
		staff.setStaff_availability(rs.getString("staff_availability"));
		staff.setEmail(rs.getString("email"));
		staff.setPassword(rs.getString("password"));
		return staff;
	}
}