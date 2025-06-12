package com.medicalManagement.servlets;

import jakarta.servlet.Servlet;
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

/**
 * Servlet implementation class DeletePatientServlet
 */
@WebServlet("/DeletePatients")
public class DeletePatientServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	private PatientDAO dao;
	private Connection conn;
	
	public void init() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/doctor_management", "root", "admin");
            dao = new PatientDAO(conn);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
        try {
			dao.deletePatient(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
        response.sendRedirect("PatientList");
	}

}
