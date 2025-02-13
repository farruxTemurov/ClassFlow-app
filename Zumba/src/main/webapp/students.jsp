<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.util.List, com.zumba.bean.Batches, com.zumba.bean.Students, com.zumba.service.BatchesService, com.zumba.service.StudentsService"%>

<%
BatchesService batchesService = new BatchesService();
List<Batches> batches = batchesService.getAllBatches();

StudentsService studentsService = new StudentsService();
Students student = (Students) request.getAttribute("student");

String message = (String) session.getAttribute("message");
String error = (String) session.getAttribute("error");

session.removeAttribute("message");
session.removeAttribute("error");
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

.message {
	color: green;
}

.error {
	color: red;
}
</style>
</head>
<body>
	<div class="container">
		<h2>Register Student</h2>

		<%
		if (message != null) {
		%>
		<p class="message"><%=message%></p>
		<%
		}
		%>

		<%
		if (error != null) {
		%>
		<p class="error"><%=error%></p>
		<%
		}
		%>

		<form action="StudentsController" method="post">
			<input type="text" name="name" placeholder="Full Name" required>
			<input type="text" name="telephone" placeholder="Telephone" required>
			<input type="email" name="email" placeholder="Email" required>
			<label for="batch">Assign to Batch:</label> <select name="batchId">
				<option value="">Select a batch</option>
				<%
				for (Batches batch : batches) {
				%>
				<option value="<%=batch.getBatchId()%>"><%=batch.getBatchType()%>
					-
					<%=batch.getBatchTime()%></option>
				<%
				}
				%>
			</select>
			<button type="submit" name="action" value="register">Register</button>
		</form>

		<h2>Search Student</h2>
		<form action="StudentsController" method="get">
			<input type="text" name="searchName" placeholder="Enter Name"
				required> <input type="email" name="searchEmail"
				placeholder="Enter Email" required>
			<button type="submit" name="action" value="search">Search</button>
		</form>

		<%
		if (student != null) {
		%>
		<h3>Student Details:</h3>
		<form action="StudentsController" method="post">
			<input type="hidden" name="studentId"
				value="<%=student.getStudentId()%>">
			<p>
				<strong>Name:</strong> <input type="text" name="name"
					value="<%=student.getName()%>" required>
			</p>
			<p>
				<strong>Telephone:</strong> <input type="text" name="telephone"
					value="<%=student.getTelephone()%>" required>
			</p>
			<p>
				<strong>Email:</strong> <input type="email" name="email"
					value="<%=student.getEmail()%>" required>
			</p>
			<p>
				<strong>Batch ID:</strong> <select name="batchId">
					<option value="">Select a batch</option>
					<%
					for (Batches batch : batches) {
					%>
					<option value="<%=batch.getBatchId()%>"
						<%=(batch.getBatchId() == student.getBatchId()) ? "selected" : ""%>>
						<%=batch.getBatchType()%> -
						<%=batch.getBatchTime()%>
					</option>
					<%
					}
					%>
				</select>
			</p>
			<button type="submit" name="action" value="update">Update</button>
			<button type="submit" name="action" value="delete">Delete</button>
		</form>
		<%
		}
		%>
	</div>
</body>
</html>
