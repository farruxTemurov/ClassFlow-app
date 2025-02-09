<%@ page import="com.zumba.bean.Students, com.zumba.bean.Enrollments"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<title>Enrollments</title>
</head>
<body>
	<h2>Student Enrollment</h2>

	<!-- Form to assign student to a batch -->
	<form action="enrollments" method="post">
		<label>Student ID:</label> <input type="number" name="studentId"
			required><br> <label>Batch ID:</label> <input
			type="number" name="batchId" required><br> <label>Enrollment
			Date:</label> <input type="date" name="enrollmentDate" required><br>
		<button type="submit">Enroll Student</button>
	</form>

	<h2>Search Student Enrollment</h2>

	<!-- Form to search for student details -->
	<form action="enrollments" method="get">
		<label>Student ID:</label> <input type="number" name="studentId"
			required>
		<button type="submit">Search</button>
	</form>

	<!-- Display student and batch details -->
	<%
	Students student = (Students) request.getAttribute("student");
	Enrollments enrollment = (Enrollments) request.getAttribute("enrollment");
	if (student != null && enrollment != null) {
	%>
	<h3>Student Details</h3>
	<p>
		Name:
		<%=student.getName()%></p>
	<p>
		Email:
		<%=student.getEmail()%></p>
	<p>
		Phone:
		<%=student.getPhoneNumber()%></p>
	<h3>Enrolled Batch</h3>
	<p>
		Batch ID:
		<%=enrollment.getBatchId()%></p>
	<p>
		Enrollment Date:
		<%=enrollment.getEnrollmentDate()%></p>
	<%
	}
	%>
</body>
</html>
