package com.medicalManagement.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import com.medicalManagement.dao.PatientDAO;
import com.medicalManagement.model.Patient;

/**
 * Servlet implementation class EditPatientServlet
 */
@WebServlet("/EditPatients")
public class EditPatientServlet extends HttpServlet {
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String idParam = request.getParameter("patient_id");
        if (idParam == null || idParam.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing patient_id parameter");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid patient_id parameter");
            return;
        }

        Patient patient = dao.getPatientById(id);

        if (patient == null) {
            out.println("<h3>No patient found with id " + id + "</h3>");
            return;
        }

        out.println("<!DOCTYPE html><html><head><title>Edit Patient Details</title></head><body>");
        out.println("<h2>Edit Patient</h2>");
        out.println("<form method='POST' action='UpdatePatients'>");

        out.println("<input type='hidden' name='id' value='" + patient.getId() + "'/>");
        out.println("Full Name: <input type='text' name='fullName' value='" + patient.getFullName() + "'/><br/><br/>");
        out.println("Email: <input type='email' name='email' value='" + patient.getEmail() + "'/><br/><br/>");

        out.println("<button type='submit'>Update</button>");
        out.println("</form>");
        out.println("</body></html>");
    }
}
