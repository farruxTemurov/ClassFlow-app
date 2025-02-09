<%@ page import="java.util.List"%>
<%@ page import="com.zumba.bean.Batches"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page import="javax.servlet.http.HttpServletResponse"%>
<!DOCTYPE html>
<html>
<head>
<title>Batches Management</title>
<script>
	function showAddBatchForm() {
		document.getElementById("addBatchForm").style.display = "block";
	}
</script>
</head>
<body>
	<h2>Batches Management</h2>

	<!-- Button to fetch batches -->
	<form action="batches" method="get">
		<button type="submit">View All Batches</button>
	</form>

	<!-- Table for displaying batches -->
	<table border="1">
		<tr>
			<th>Batch ID</th>
			<th>Batch Name</th>
			<th>Schedule ID</th>
			<th>Instructor ID</th>
		</tr>
		<%
		List<Batches> batches = (List<Batches>) request.getAttribute("batches");
		if (batches != null) {
			for (Batches batch : batches) {
		%>
		<tr>
			<td><%=batch.getBatchId()%></td>
			<td><%=batch.getBatchName()%></td>
			<td><%=batch.getScheduleId()%></td>
			<td><%=batch.getInstructorId()%></td>
		</tr>
		<%
		}
		}
		%>
	</table>

	<!-- Button to show add batch form -->
	<button onclick="showAddBatchForm()">Add New Batch</button>

	<!-- Add Batch Form -->
	<div id="addBatchForm" style="display: none;">
		<h3>Add New Batch</h3>
		<form action="batches" method="post">
			<label>Batch Name:</label> <input type="text" name="batchName"
				required><br> <label>Schedule ID:</label> <input
				type="number" name="scheduleId" required><br> <label>Instructor
				ID:</label> <input type="number" name="instructorId" required><br>
			<button type="submit">Add Batch</button>
		</form>
	</div>
</body>
</html>
