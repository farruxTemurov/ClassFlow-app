<%@ page import="java.util.List"%>
<%@ page import="com.zumba.bean.Batches"%>
<%@ page import="com.zumba.bean.Schedules"%>
<%@ page import="com.zumba.bean.Instructors"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Batches</title>
<script>
	function toggleForm() {
		var form = document.getElementById("addBatchForm");
		form.style.display = form.style.display === "none" ? "block" : "none";
	}
</script>
</head>
<body>
	<h2>Batches</h2>
	<button onclick="toggleForm()">Add New Batch</button>

	<!-- Batch Table -->
	<table border="1">
		<tr>
			<th>Batch ID</th>
			<th>Batch Name</th>
			<th>Schedule</th>
			<th>Instructor</th>
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
			<td><%=batch.getInstructorName()%></td>
		</tr>
		<%
		}
		}
		%>
	</table>

	<!-- Add Batch Form -->
	<div id="addBatchForm" style="display: none;">
		<h3>Add New Batch</h3>
		<form action="batches" method="post">
			<label>Batch Name:</label> <input type="text" name="batchName"
				required><br> <label>Schedule:</label> <select
				name="scheduleId" required>
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
