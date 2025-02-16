<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.zumba.bean.Batches" %>
<%
List<Batches> batches = (List<Batches>) request.getAttribute("batches");
String message = (String) request.getAttribute("message");
String error = (String) request.getAttribute("error");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manage Batches</title>
    <style>
        body { font-family: Arial, sans-serif; }
        .container { width: 50%; margin: 0 auto; padding: 20px; }
        input, button { display: block; width: 100%; margin: 10px 0; padding: 10px; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 10px; text-align: left; }
        th { background-color: #f4f4f4; }
        .success { color: green; }
        .error { color: red; }
    </style>
</head>
<body>
	<jsp:include page="navbar.jsp" />
    <div class="container">
        <h2>Add New Batch</h2>
        
        <% if (message != null) { %>
            <p class="success"><%= message %></p>
        <% } %>
        <% if (error != null) { %>
            <p class="error"><%= error %></p>
        <% } %>

        <form action="<%=request.getContextPath()%>/BatchesController" method="post">
            <input type="text" name="batchType" placeholder="Batch Type (e.g., Morning Evening" required>
            <input type="text" name="batchTime" placeholder="Batch Time (e.g., 10:00 AM - 11:00 AM)" required>
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
            <% if (batches != null) { 
                for (Batches batch : batches) { %>
                <tr>
                    <td><%= batch.getBatchId() %></td>
                    <td><%= batch.getBatchType() %></td>
                    <td><%= batch.getBatchTime() %></td>
                    <td>
                        <!-- Delete Form -->
                        <form action="BatchesController" method="post" style="display:inline;">
                            <input type="hidden" name="batchId" value="<%= batch.getBatchId() %>">
                            <button type="submit" name="action" value="delete" 
                                onclick="return confirm('Are you sure you want to delete this batch?')">Delete</button>
                        </form>

                        <!-- Update Form -->
                        <form action="BatchesController" method="post" style="display:inline;">
                            <input type="hidden" name="batchId" value="<%= batch.getBatchId() %>">
                            <input type="text" name="batchType" placeholder="New Type" required>
                            <input type="text" name="batchTime" placeholder="New Time" required>
                            <button type="submit" name="action" value="update">Update</button>
                        </form>
                    </td>
                </tr>
            <% } } %>
        </table>
    </div>
</body>
</html>
