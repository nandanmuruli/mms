package com.medicalManagement.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import com.google.gson.Gson;
import com.medicalManagement.dao.PatientDAO;
import com.medicalManagement.dao.PatientDAOImpl;
import com.medicalManagement.dao.StaffDAO;
import com.medicalManagement.dao.StaffDAOImpl;
import com.medicalManagement.model.Patient;
import com.medicalManagement.model.Staff;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson = new Gson();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setHeader("Access-Control-Allow-Origin", "*"); // For development only
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");

		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String userType = req.getParameter("user_type");

		try (PrintWriter out = res.getWriter()) {
			if ("Patient".equalsIgnoreCase(userType)) {
				PatientDAO patientDao = new PatientDAOImpl();
				Patient patient = patientDao.getPatientByEmail(email);

				if (patient != null && patient.getPatientPassword().equals(password)) {
					HttpSession session = req.getSession();
					session.setAttribute("user", patient);
					session.setAttribute("userRole", "Patient");
					out.print("{\"status\":\"success\", \"role\":\"Patient\"}");
				} else {
					res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					out.print("{\"status\":\"fail\", \"message\":\"Invalid credentials\"}");
				}
			} else if ("Staff".equalsIgnoreCase(userType)) {
				StaffDAO staffDao = new StaffDAOImpl();
				Staff staff = staffDao.getStaffByEmail(email);

				if (staff != null && staff.getPassword().equals(password)) {
					HttpSession session = req.getSession();
					session.setAttribute("user", staff);
					session.setAttribute("userRole", staff.getDesignation()); // e.g., "Doctor"
					out.print("{\"status\":\"success\", \"role\":\"" + staff.getDesignation() + "\"}");
				} else {
					res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					out.print("{\"status\":\"fail\", \"message\":\"Invalid credentials\"}");
				}
			} else {
				res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				out.print("{\"status\":\"fail\", \"message\":\"Invalid user type\"}");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			res.getWriter().print("{\"status\":\"fail\", \"message\":\"Database error\"}");
		}
	}
}