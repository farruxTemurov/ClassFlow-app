<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="com.zumba.bean.Students, com.zumba.bean.Batches, com.zumba.service.BatchesService, java.util.List"%>

<%
BatchesService batchesService = new BatchesService();
List<Batches> batchList = batchesService.getAllBatches();
%>

<!DOCTYPE html>
<html>
<head>
<title>Student Registration</title>
</head>
<body>
	<jsp:include page="navbar.jsp" />
	<h2>Register Student</h2>
	<form action="StudentsController" method="post">
		<input type="hidden" name="action" value="register"> <label>Name:</label>
		<input type="text" name="name" required><br>
		<br> <label>Telephone:</label> <input type="text"
			name="telephone"><br>
		<br> <label>Email:</label> <input type="email" name="email"
			required><br>
		<br> <label>Batch:</label> <select name="batchId">
			<option value="">Select Batch</option>
			<%
			for (Batches batch : batchList) {
			%>
			<option value="<%=batch.getBatchId()%>"><%=batch.toString()%></option>
			<%
			}
			%>
		</select><br>
		<br> <input type="submit" value="Register">
	</form>

	<h2>Search Student</h2>
	<form action="StudentsController" method="get">
		<input type="hidden" name="action" value="search"> <label>Name:</label>
		<input type="text" name="searchName" required><br>
		<br> <label>Email:</label> <input type="email" name="searchEmail"
			required><br>
		<br> <input type="submit" value="Search">
	</form>

	<%
	Students student = (Students) request.getAttribute("student");
	if (student != null) {
	%>
	<h3>Student Details</h3>
	<p>
		ID:
		<%=student.getStudentId()%></p>
	<p>
		Name:
		<%=student.getName()%></p>
	<p>
		Telephone:
		<%=student.getTelephone()%></p>
	<p>
		Email:
		<%=student.getEmail()%></p>
	<p>
		Batch:
		<%=student.getBatchType() != null ? student.getBatchType() + " " + student.getBatchTime() : "Not Assigned"%></p>

	<%
	}
	%>

</body>
</html>
