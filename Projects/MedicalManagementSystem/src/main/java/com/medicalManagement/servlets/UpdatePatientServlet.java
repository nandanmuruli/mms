package com.medicalManagement.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.medicalManagement.dao.PatientDAO;
import com.medicalManagement.model.Patient;

/**
 * Servlet implementation class UpdatePatientServlet
 */
@WebServlet("/UpdatePatients")
public class UpdatePatientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Connection conn;
	private PatientDAO dao;
	
	public void init() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/doctor_management", "root", "admin");
            dao = new PatientDAO(conn);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get parameters from form submission
        String idStr = request.getParameter("id");
        System.out.println("I guess this is valid "+ idStr);
        
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");

//        if (idStr != null && !idStr.isEmpty()) {
            try {
                int id = Integer.parseInt(idStr);

                Patient updatedPatient = new Patient();
                updatedPatient.setId(id);
                updatedPatient.setFullName(fullName);
                updatedPatient.setEmail(email);

                boolean updated = dao.updatePatient(updatedPatient);

                if (updated) {
                    response.sendRedirect("ListPatients"); // or another success page
                } else {
                    response.getWriter().println("Update failed. Please check the patient ID.");
                }
            } catch (NumberFormatException | SQLException e) {
                response.getWriter().println("Invalid patient ID.");
            }
//        } else {
//            response.getWriter().println("Patient ID is missing.");
//        }
    }

}
