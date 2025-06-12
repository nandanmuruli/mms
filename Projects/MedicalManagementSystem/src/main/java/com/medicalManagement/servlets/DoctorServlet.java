package com.medicalManagement.servlets;

import com.medicalManagement.dao.PatientDAO;
import com.medicalManagement.model.Patient;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.*;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/api/doctors")
public class DoctorServlet extends HttpServlet {
    private PatientDAO dao = new PatientDAO();

    public void init() {
        
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        try {
            List<Patient> list = dao.getAllPatients();
            out.println("<html><head><title>Patient List</title></head><body>");
            out.println("<h2>Patient List</h2>");
            out.println("<table border='1' cellpadding='5'>");
            out.println("<tr><th>ID</th><th>Name</th><th>Age</th><th>Gender</th></tr>");

            for (Patient p :list) {
                out.printf("<tr><td>%d</td><td>%s</td><td>%d</td><td>%s</td></tr>",
                           p.getId(), p.getName(), p.getAge(), p.getGender());
            }

            out.println("</table>");
            out.println("</body></html>");
            
        } catch (SQLException e) {
            res.setStatus(500);
            out.print("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        Patient p = new Patient();
        p.setName(req.getParameter("name"));
        p.setAge(Integer.parseInt(req.getParameter("age")));
        p.setGender(req.getParameter("gender"));
        try {
            dao.insertPatient(p);
            res.getWriter().write("{\"status\":\"success\"}");
        } catch (SQLException e) {
            res.setStatus(500);
            res.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    protected void doPut(HttpServletRequest req, HttpServletResponse res) throws IOException {
        Patient p = new Patient(1, "Prajwal", 30, "Male");
        try {
            dao.updatePatient(p);
            res.getWriter().write("{\"status\":\"updated\"}");
        } catch (SQLException e) {
            res.setStatus(500);
            res.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            dao.deletePatient(id);
            res.getWriter().write("{\"status\":\"deleted\"}");
        } catch (SQLException e) {
            res.setStatus(500);
            res.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
}
