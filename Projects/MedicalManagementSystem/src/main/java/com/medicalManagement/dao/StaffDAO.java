package com.medicalManagement.dao;

import java.sql.SQLException;
import java.util.List;
import com.medicalManagement.model.Staff;

public interface StaffDAO {
    Staff getStaffByEmail(String email) throws SQLException;
    List<Staff> getAllStaff() throws SQLException;
    // You can add other methods like getById, addStaff, etc.
}