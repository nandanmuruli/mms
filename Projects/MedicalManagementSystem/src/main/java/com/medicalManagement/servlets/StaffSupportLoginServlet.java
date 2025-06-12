package com.medicalManagement.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.medicalManagement.dao.StaffDAO;
import com.medicalManagement.dao.StaffDAOImpl;
import com.medicalManagement.model.Staff;
import com.medicalManagement.utils.DBConnection;

/**
 * Servlet implementation class StaffLoginServlet
 */
@WebServlet("/staffSupport/Login")
public class StaffSupportLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StaffDAO staffDAO;

	@Override
	public void init() {
		try {
			staffDAO = new StaffDAOImpl(DBConnection.getConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setHeader("Access-Control-Allow-Origin", "http://localhost:5173");
    	resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
    	resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
		
		try {
			BufferedReader reader = req.getReader();
			Gson gson = new Gson();
			Map<String, String> loginData = gson.fromJson(reader, new TypeToken<Map<String, String>>() {
			}.getType());

			String email = loginData.get("email");
			String password = loginData.get("password");

			if (email == null || password == null) {
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Email and password required");
				return;
			}

			Staff staff = StaffDAO.getStaffByEmail(email);
			if (staff != null && staff.getPassword().equals(password)) { // Ideally use hashed passwords!
				HttpSession session = req.getSession(true);
				session.setAttribute("staffId", staff.getStaffId());
				session.setAttribute("role", staff.getDesignation()); // e.g. Doctor, Receptionist
				resp.setStatus(HttpServletResponse.SC_OK);
				writeJsonResponse(resp, Map.of("message", "Login successful"));
			} else {
				resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid credentials");
			}
		} catch (Exception e) {
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
		}
		
		String type = req.getParameter("type"); // ?type=doctor or ?type=receptionist
	    List<Staff> staffList;

	    try {
	        if ("doctor".equalsIgnoreCase(type)) {
	            staffList = staffDAO.getAllDoctors();
	        } else if ("receptionist".equalsIgnoreCase(type)) {
	            staffList = staffDAO.getAllReceptionists();
	        } else {
	            staffList = staffDAO.getAllStaff(); // all generic staff
	        }

	        String json = new Gson().toJson(staffList);
	        resp.setContentType("application/json");
	        resp.getWriter().write(json);
	    } catch (SQLException e) {
	        e.printStackTrace();
	        resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	    }
	}

	private void writeJsonResponse(HttpServletResponse resp, Object obj) throws IOException {
		resp.setContentType("application/json");
		Gson gson = new Gson();
		resp.getWriter().write(gson.toJson(obj));
	}

}
