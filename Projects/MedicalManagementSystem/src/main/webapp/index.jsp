<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Medical Management System</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<%@ include file="header.jsp" %>
<main>
    <h2>Welcome to the Dashboard</h2>
    <p>Select a section to manage.</p>
    <div class="dashboard-links">
        <a href="patients.jsp" class="dashboard-link">Manage Patients</a>
        <a href="doctors.jsp" class="dashboard-link">Manage Doctors</a>
        <a href="appointments.jsp" class="dashboard-link">Manage Appointments</a>
    </div>
</main>
<%@ include file="footer.jsp" %>
</body>
</html>