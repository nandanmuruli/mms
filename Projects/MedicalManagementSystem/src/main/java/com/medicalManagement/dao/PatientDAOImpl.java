package com.medicalManagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.medicalManagement.model.Patient;

public class PatientDAOImpl implements PatientDAO {

    @Override
    public boolean addPatient(Patient patient) throws SQLException {
        String sql = "INSERT INTO patient (patient_name, gender, date_of_birth, contact_number, address, insurance_type, patient_history, email, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, patient.getPatientName());
            ps.setString(2, patient.getGender());
            ps.setDate(3, new java.sql.Date(patient.getDateOfBirth().getTime()));
            ps.setString(4, patient.getContactNumber());
            ps.setString(5, patient.getAddress());
            ps.setString(6, patient.getInsuranceType());
            ps.setString(7, patient.getPatientHistory());
            ps.setString(8, patient.getPatientEmail());
            ps.setString(9, patient.getPatientPassword());
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public Patient getPatientByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM patient WHERE email = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapRowToPatient(rs);
            }
        }
        return null;
    }

    // You can add the other methods here if needed (getById, getAll, update, delete)...

    private Patient mapRowToPatient(ResultSet rs) throws SQLException {
        Patient patient = new Patient();
        patient.setPatientId(rs.getInt("patient_id"));
        patient.setPatientName(rs.getString("patient_name"));
        patient.setGender(rs.getString("gender"));
        patient.setDateOfBirth(rs.getDate("date_of_birth"));
        patient.setContactNumber(rs.getString("contact_number"));
        patient.setAddress(rs.getString("address"));
        patient.setPatientEmail(rs.getString("email"));
        patient.setPatientPassword(rs.getString("password"));
        patient.setInsuranceType(rs.getString("insurance_type"));
        patient.setPatientHistory(rs.getString("patient_history"));
        return patient;
    }

    // Stubs for other interface methods
    @Override
    public Patient getPatientById(int patientId) throws SQLException { return null; }
    @Override
    public List<Patient> getAllPatients() throws SQLException { return new ArrayList<>(); }
    @Override
    public boolean updatePatient(Patient patient) throws SQLException { return false; }
    @Override
    public boolean deletePatient(int patientId) throws SQLException { return false; }
}