package com.medicalManagement.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.medicalManagement.dao.PatientDAO;
import com.medicalManagement.dao.PatientDAOImpl;
import com.medicalManagement.model.Patient;

@WebServlet("/patient/register")
public class PatientRegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*"); // For development only
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String contactNumber = request.getParameter("contact_number");
            String gender = request.getParameter("gender");
            String dobStr = request.getParameter("dob"); // Expected format: yyyy-MM-dd

            Patient patient = new Patient();
            patient.setPatientName(name);
            patient.setPatientEmail(email);
            patient.setPatientPassword(password);
            patient.setContactNumber(contactNumber);
            patient.setGender(gender);

            // Safely parse the date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date dob = sdf.parse(dobStr);
            patient.setDateOfBirth(dob);

            // Create DAO and add patient
            PatientDAO patientDao = new PatientDAOImpl();
            boolean success = patientDao.addPatient(patient);

            if (success) {
                response.setStatus(HttpServletResponse.SC_CREATED);
                response.getWriter().write("{\"status\":\"success\", \"message\":\"Patient registered successfully.\"}");
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("{\"status\":\"fail\", \"message\":\"Registration failed.\"}");
            }

        } catch (ParseException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"status\":\"fail\", \"message\":\"Invalid date format. Please use yyyy-MM-dd.\"}");
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"status\":\"fail\", \"message\":\"Database error during registration.\"}");
        }
    }
}