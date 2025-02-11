<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.util.List, com.zumba.bean.Batches, com.zumba.bean.Students, com.zumba.service.BatchesService, com.zumba.service.StudentsService"%>
<%
BatchesService batchesService = new BatchesService();
List<Batches> batches = batchesService.getAllBatches();

StudentsService studentsService = new StudentsService();
Students studentResult = null;
String searchName = request.getParameter("searchName");
String searchEmail = request.getParameter("searchEmail");

if (searchName != null && searchEmail != null) {
	studentResult = studentsService.searchStudent(searchName, searchEmail);
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Registration</title>
<style>
body {
	font-family: Arial, sans-serif;
}

.container {
	width: 50%;
	margin: 0 auto;
	padding: 20px;
}

input, select, button {
	display: block;
	width: 100%;
	margin: 10px 0;
	padding: 10px;
}
</style>
</head>
<body>
	<div class="container">
		<h2>Register Student</h2>
		<form action="StudentsController" method="post">
			<input type="text" name="name" placeholder="Full Name" required>
			<input type="text" name="telephone" placeholder="Telephone" required>
			<input type="email" name="email" placeholder="Email" required>
			<label for="batch">Assign to Batch:</label> <select name="batchId">
				<option value="">Select a batch</option>
				<%
				for (Batches batch : batches) {
				%>
				<option value="<%=batch.getBatchId()%>">
					<%=batch.getBatchType()%> -
					<%=batch.getBatchTime()%>
				</option>
				<%
				}
				%>
			</select>
			<button type="submit" name="action" value="register">Register</button>
		</form>

		<h2>Search Student</h2>
		<form method="get">
			<input type="text" name="searchName" placeholder="Enter Name"
				required> <input type="email" name="searchEmail"
				placeholder="Enter Email" required>
			<button type="submit">Search</button>
		</form>

		<%
		if (studentResult != null) {
		%>
		<h3>Student Details:</h3>
		<p>
			<strong>Name:</strong>
			<%=studentResult.getName()%></p>
		<p>
			<strong>Telephone:</strong>
			<%=studentResult.getTelephone()%></p>
		<p>
			<strong>Email:</strong>
			<%=studentResult.getEmail()%></p>
		<%
		if (studentResult.getBatchId() > 0) {
		%>
		<p>
			<strong>Batch ID:</strong>
			<%=studentResult.getBatchId()%></p>
		<%
		} else {
		%>
		<p>
			<strong>Batch:</strong> Not assigned
		</p>
		<%
		}
		%>
		<%
		}
		%>
	</div>
</body>
</html>
