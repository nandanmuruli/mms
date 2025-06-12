package com.medicalManagement.dao;

import java.sql.SQLException;
import java.util.List;
import com.medicalManagement.model.Patient;

public interface PatientDAO {
    boolean addPatient(Patient patient) throws SQLException;
    Patient getPatientByEmail(String email) throws SQLException;
    Patient getPatientById(int patientId) throws SQLException;
    List<Patient> getAllPatients() throws SQLException;
    boolean updatePatient(Patient patient) throws SQLException;
    boolean deletePatient(int patientId) throws SQLException;
}