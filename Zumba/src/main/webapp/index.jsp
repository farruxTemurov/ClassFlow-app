<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Zumba Home</title>
    <style>
        .container {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            height: 10vh; /* Adjust based on need */
            text-align: center;
        }
    </style>
</head>
<body>

    <!-- Include Navbar -->
    <jsp:include page="navbar.jsp" />

    <div class="container">
        <h1>Welcome to Zumba App</h1>
        <p>Organize your classes and get students' information</p>
    </div>

</body>
</html>
