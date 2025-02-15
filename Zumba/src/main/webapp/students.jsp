<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.zumba.bean.Batches, com.zumba.bean.Students, java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Registration</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: Arial, sans-serif;
        }

        .container {
            width: 50%;
            margin: auto;
            padding: 20px;
            text-align: center;
        }

        form {
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin: 10px 0 5px;
        }

        select, input {
            width: 100%;
            padding: 8px;
        }

        /* Styled buttons */
        .form-btn {
            margin-top: 10px;
            padding: 10px 15px;
            background-color: #ff6600; /* Orange background */
            color: white;
            border: none;
            cursor: pointer;
            font-size: 16px;
            border-radius: 5px;
            transition: background 0.3s ease-in-out;
        }

        .form-btn:hover {
            background-color: #cc5200; /* Darker orange for hover */
        }

        .result {
            margin-top: 20px;
            padding: 10px;
            border: 1px solid #ddd;
            background-color: #f9f9f9;
            display: inline-block;
            text-align: left;
        }
    </style>
</head>
<body>

    <jsp:include page="navbar.jsp" />
    
    <div class="container">
        <h2>Register Student</h2>
        <form action="StudentsController" method="post">
            <label for="studentName">Name:</label>
            <input type="text" id="studentName" name="name" required>

            <label for="studentEmail">Email:</label>
            <input type="email" id="studentEmail" name="email" required>

            <label for="studentPhone">Phone:</label>
            <input type="text" id="studentPhone" name="telephone" required>

            <label for="batch">Select Batch:</label>
            <select id="batch" name="batch_id" required>
                <option value="">-- Select Batch --</option>
                <%
                    List<Batches> batchesList = (List<Batches>) request.getAttribute("batchesList");

                    if (batchesList == null || batchesList.isEmpty()) {
                %>
                    <option value="" disabled>No batches available</option>
                <%
                    } else {
                        for (Batches batch : batchesList) {
                %>
                    <option value="<%= batch.getBatchId() %>">
                        <%= batch.getBatchType() %> - <%= batch.getBatchTime() %>
                    </option>
                <%
                        }
                    }
                %>
            </select>

            <button type="submit" name="action" value="register" class="form-btn">Register</button>
        </form>

        <h2>Find Student</h2>
        <form action="StudentsController" method="post">
            <label for="search_name">Name:</label>
            <input type="text" name="search_name" required>

            <label for="search_email">Email:</label>
            <input type="email" name="search_email" required>

            <button type="submit" name="action" value="search" class="form-btn">Search</button>
        </form>

        <%-- Display Search Results --%>
        <%
            Students student = (Students) request.getAttribute("student");
            String message = (String) request.getAttribute("message");
        %>

        <% if (student != null) { %>
            <div class="result">
                <h3>Search Results:</h3>
                <p><strong>Name:</strong> <%= student.getName() %></p>
                <p><strong>Email:</strong> <%= student.getEmail() %></p>
                <p><strong>Telephone:</strong> <%= student.getTelephone() %></p>
                <% if (student.getBatchType() != null) { %>
                    <p><strong>Batch:</strong> <%= student.getBatchType() %> - <%= student.getBatchTime() %></p>
                <% } %>
            </div>
        <% } else if (message != null) { %>
            <div class="result">
                <p><%= message %></p>
            </div>
        <% } %>
    </div>

</body>
</html>
