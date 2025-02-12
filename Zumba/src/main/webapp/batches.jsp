<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.util.List, com.zumba.bean.Batches, com.zumba.service.BatchesService"%>
<%
List<Batches> batches = (List<Batches>) request.getAttribute("batches");
if (batches == null) {
	BatchesService batchesService = new BatchesService();
	batches = batchesService.getAllBatches();
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Batches</title>
<style>
body {
	font-family: Arial, sans-serif;
}

.container {
	width: 50%;
	margin: 0 auto;
	padding: 20px;
}

input, button {
	display: block;
	width: 100%;
	margin: 10px 0;
	padding: 10px;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
}

th, td {
	border: 1px solid #ddd;
	padding: 10px;
	text-align: left;
}

th {
	background-color: #f4f4f4;
}
</style>
</head>
<body>
	<div class="container">
		<h2>Add New Batch</h2>
		<form action="<%=request.getContextPath()%>/BatchesController"
			method="post">
			<input type="text" name="batchType"
				placeholder="Batch Type (e.g., Beginner, Advanced)" required>
			<input type="text" name="batchTime"
				placeholder="Batch Time (e.g., 10:00 AM - 11:00 AM)" required>
			<button type="submit" name="action" value="add">Add Batch</button>
		</form>

		<h2>Existing Batches</h2>
		<table>
			<tr>
				<th>Batch ID</th>
				<th>Batch Type</th>
				<th>Batch Time</th>
				<th>Action</th>
			</tr>
			<%
			for (Batches batch : batches) {
			%>
			<tr>
				<td><%=batch.getBatchId()%></td>
				<td><%=batch.getBatchType()%></td>
				<td><%=batch.getBatchTime()%></td>
				<td>
					<form action="BatchesController" method="post">
						<input type="hidden" name="batchId"
							value="<%=batch.getBatchId()%>">
						<button type="submit" name="action" value="delete"
							onclick="return confirm('Are you sure you want to delete this batch?')">
							Delete</button>
					</form>

				</td>
			</tr>
			<%
			}
			%>
		</table>
	</div>
</body>
</html>
