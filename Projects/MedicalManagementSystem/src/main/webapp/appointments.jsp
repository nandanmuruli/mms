<%@ page import="com.medicalManagement.model.Appointment" %>
<%@ page import="com.medicalManagement.dao.AppointmentDAO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Manage Appointments</title>
  <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<%@ include file="header.jsp" %>
<main>
  <h2>Appointment Management</h2>

  <h3>Schedule New Appointment</h3>
  <form action="AppointmentServlet" method="post">
    <input type="hidden" name="action" value="add"/>
    <label for="patientId">Patient ID:</label>
    <input type="number" id="patientId" name="patientId" required>

    <label for="doctorId">Doctor ID:</label>
    <input type="number" id="doctorId" name="doctorId" required>

    <label for="appointmentDate">Appointment Date:</label>
    <input type="datetime-local" id="appointmentDate" name="appointmentDate" required>

    <label for="reason">Reason:</label>
    <input type="text" id="reason" name="reason">

    <button type="submit">Schedule</button>
  </form>

  <hr>

  <h3>Upcoming Appointments</h3>
  <table>
    <thead>
    <tr>
      <th>ID</th>
      <th>Patient ID</th>
      <th>Doctor ID</th>
      <th>Date</th>
      <th>Reason</th>
      <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <%-- Assuming your AppointmentDAO has a method getAllAppointments() --%>
    <%
      AppointmentDAO appointmentDao = new AppointmentDAO();
      List<Appointment> appointmentList = appointmentDao.getAllAppointments();
      for(Appointment appt : appointmentList) {
    %>
    <tr>
      <td><%= appt.getId() %></td>
      <td><%= appt.getPatientId() %></td>
      <td><%= appt.getDoctorId() %></td>
      <td><%= appt.getAppointmentDate() %></td>
      <td><%= appt.getReason() %></td>
      <td>
        <a href="AppointmentServlet?action=delete&id=<%= appt.getId() %>">Cancel</a>
      </td>
    </tr>
    <% } %>
    </tbody>
  </table>
</main>
<%@ include file="footer.jsp" %>
</body>
</html>