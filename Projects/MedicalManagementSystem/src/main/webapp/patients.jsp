<%@ page import="com.medicalManagement.model.Patient" %>
<%@ page import="com.medicalManagement.dao.PatientDAO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Manage Patients</title>
  <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<%@ include file="header.jsp" %>
<main>
  <h2>Patient Management</h2>

  <h3>Add New Patient</h3>
  <form action="PatientServlet" method="post">
    <input type="hidden" name="action" value="add"/>
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required>

    <label for="dob">Date of Birth:</label>
    <input type="date" id="dob" name="dob" required>

    <label for="gender">Gender:</label>
    <input type="text" id="gender" name="gender">

    <button type="submit">Add Patient</button>
  </form>

  <hr>

  <h3>Existing Patients</h3>
  <table>
    <thead>
    <tr>
      <th>ID</th>
      <th>Name</th>
      <th>Date of Birth</th>
      <th>Gender</th>
      <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <%-- Assuming your PatientDAO has a method getAllPatients() --%>
    <%
      PatientDAO patientDao = new PatientDAO();
      List<Patient> patientList = patientDao.getAllPatients();
      for(Patient patient : patientList) {
    %>
    <tr>
      <td><%= patient.getId() %></td>
      <td><%= patient.getName() %></td>
      <td><%= patient.getDob() %></td>
      <td><%= patient.getGender() %></td>
      <td>
        <a href="PatientServlet?action=delete&id=<%= patient.getId() %>">Delete</a>
      </td>
    </tr>
    <% } %>
    </tbody>
  </table>
</main>
<%@ include file="footer.jsp" %>
</body>
</html>