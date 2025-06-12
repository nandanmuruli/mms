package com.medicalManagement.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.medicalManagement.utils.DBConnection;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.medicalManagement.dao.PatientDAO;
import com.medicalManagement.dao.PatientDAOImpl;
import com.medicalManagement.model.Patient;
import com.medicalManagement.model.Registration_User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSession;

@WebServlet("/patient/login")
public class PatientLoginServlet extends HttpServlet {
    private PatientDAO patientDAO;

    @Override
    public void init() {
        try {
			patientDAO = new PatientDAOImpl(DBConnection.getConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        
    	resp.setHeader("Access-Control-Allow-Origin", "http://localhost:5173");
    	resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
    	resp.setHeader("Access-Control-Allow-Headers", "Content-Type");

    	
    	try {
            BufferedReader reader = req.getReader();
            Gson gson = new Gson();
            Map<String, String> loginData = gson.fromJson(reader, new TypeToken<Map<String, String>>(){}.getType());

            String email = loginData.get("email");
            String password = loginData.get("password");

            if (email == null || password == null) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Email and password are required.");
                return;
            }

            Patient patient = patientDAO.getPatientByEmail(email);
            if (patient != null && patient.getPatientPassword().equals(password)) {
                HttpSession session = req.getSession(true);
                session.setAttribute("patientId", patient.getPatientId());
                resp.setStatus(HttpServletResponse.SC_OK);
                writeJsonResponse(resp, Map.of("message", "Login successful", "patientId", patient.getPatientId()));
            } else {
                resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid credentials.");
            }
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    private void writeJsonResponse(HttpServletResponse resp, Object obj) throws IOException {
        resp.setContentType("application/json");
        resp.getWriter().write(new Gson().toJson(obj));
    }
}

