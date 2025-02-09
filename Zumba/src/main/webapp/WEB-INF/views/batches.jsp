<%@ page import="java.util.List"%>
<%@ page import="com.zumba.bean.Batches"%>
<%@ page import="com.zumba.bean.Schedules"%>
<%@ page import="com.zumba.bean.Instructors"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<html>
<head>
<title>Batch Management</title>
<script>
	function toggleForm() {
		var form = document.getElementById("addBatchForm");
		form.style.display = (form.style.display === "none") ? "block" : "none";
	}
</script>
</head>
<body>
	<h2>Batch Management</h2>

	<!-- View All Batches -->
	<button
		onclick="document.getElementById('batchTable').style.display='block'">View
		All Batches</button>

	<table id="batchTable" border="1"
		style="display: none; margin-top: 10px;">
		<tr>
			<th>Batch ID</th>
			<th>Batch Name</th>
			<th>Schedule Name</th>
			<th>Instructor Name</th>
		</tr>
		<%
		List<Batches> batches = (List<Batches>) request.getAttribute("batches");
		if (batches != null) {
			for (Batches batch : batches) {
		%>
		<tr>
			<td><%=batch.getBatchId()%></td>
			<td><%=batch.getBatchName()%></td>
			<td><%=batch.getScheduleName()%></td>
			<!-- Use schedule name instead of ID -->
			<td><%=batch.getInstructorName()%></td>
			<!-- Use instructor name instead of ID -->
		</tr>
		<%
		}
		}
		%>
	</table>

	<!-- Add New Batch -->
	<button onclick="toggleForm()">Add New Batch</button>

	<div id="addBatchForm" style="display: none; margin-top: 10px;">
		<h3>Add New Batch</h3>
		<form action="batches" method="post">
			<label>Batch Name:</label> <input type="text" name="batchName"
				required><br> <label>Schedule:</label> <select
				name="scheduleId" required>
				<option value="">-- Select Schedule --</option>
				<%
				List<Schedules> schedules = (List<Schedules>) request.getAttribute("schedules");
				if (schedules != null) {
					for (Schedules schedule : schedules) {
				%>
				<option value="<%=schedule.getScheduleId()%>"><%=schedule.getScheduleName()%></option>
				<%
				}
				}
				%>
			</select><br> <label>Instructor:</label> <select name="instructorId"
				required>
				<option value="">-- Select Instructor --</option>
				<%
				List<Instructors> instructors = (List<Instructors>) request.getAttribute("instructors");
				if (instructors != null) {
					for (Instructors instructor : instructors) {
				%>
				<option value="<%=instructor.getInstructorId()%>"><%=instructor.getInstructorName()%></option>
				<%
				}
				}
				%>
			</select><br>

			<button type="submit">Add Batch</button>
		</form>
	</div>
</body>
</html>
