<%@ page import="java.util.List"%>
<%@ page import="com.zumba.bean.Students"%>
<%@ page import="com.zumba.bean.Batches"%>
<%@ page import="com.zumba.bean.Enrollments"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Enrollments</title>
</head>
<body>
	<h2>Register as a Student</h2>
	<form action="enrollments" method="post">
		<label>Name:</label> <input type="text" name="name" required>
		<br> <label>Email:</label> <input type="email" name="email"
			required> <br> <label>Phone Number:</label> <input
			type="text" name="phone" required> <br> <label>Registration
			Date:</label> <input type="date" name="registrationDate" required> <br>

		<button type="submit">Register</button>
	</form>

	<hr>

	<h2>Enroll in a Batch</h2>
	<form action="enrollments" method="post">
		<label>Student:</label> <select name="studentId" required>
			<option value="" disabled selected>Select a Student</option>
			<%
			List<Students> students = (List<Students>) request.getAttribute("students");
			if (students != null && !students.isEmpty()) {
				for (Students student : students) {
			%>
			<option value="<%=student.getStudentId()%>"><%=student.getName()%></option>
			<%
			}
			}
			%>
		</select> <br> <label>Batch:</label> <select name="batchId" required>
			<option value="" disabled selected>Select a Batch</option>
			<%
			List<Batches> batches = (List<Batches>) request.getAttribute("batches");
			if (batches != null && !batches.isEmpty()) {
				for (Batches batch : batches) {
			%>
			<option value="<%=batch.getBatchId()%>"><%=batch.getBatchName()%></option>
			<%
			}
			}
			%>
		</select> <br> <label>Time Slot:</label> <select name="batchTime" required>
			<option value="" disabled selected>Select a Time</option>
			<option value="06:00 AM">06:00 AM</option>
			<option value="08:00 AM">08:00 AM</option>
			<option value="06:00 PM">06:00 PM</option>
			<option value="08:00 PM">08:00 PM</option>
		</select> <br>

		<button type="submit">Enroll</button>
	</form>

	<hr>

	<h2>Check Enrollment</h2>
	<form action="enrollments" method="get">
		<label>Student Name:</label> <input type="text" name="studentName"
			required><br> <label>Student ID:</label> <input
			type="number" name="studentId" required><br>

		<button type="submit">Search</button>
	</form>

	<!-- Enrollment Table -->
	<h3>Enrollment Details</h3>
	<table border="1">
		<tr>
			<th>Enrollment ID</th>
			<th>Student Name</th>
			<th>Batch Name</th>
			<th>Enrollment Date</th>
		</tr>
		<%
		List<Enrollments> enrollments = (List<Enrollments>) request.getAttribute("enrollments");
		if (enrollments != null && !enrollments.isEmpty()) {
			for (Enrollments enrollment : enrollments) {
		%>
		<tr>
			<td><%=enrollment.getEnrollmentId()%></td>
			<td><%=enrollment.getStudentName()%></td>
			<td><%=enrollment.getBatchName()%></td>
			<td><%=enrollment.getEnrollmentDate()%></td>
		</tr>
		<%
		}
		} else {
		%>
		<tr>
			<td colspan="4" style="text-align: center;">No enrollments found</td>
		</tr>
		<%
		}
		%>
	</table>
</body>
</html>
