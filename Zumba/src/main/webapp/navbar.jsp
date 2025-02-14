<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<style>
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}

body {
    font-family: Arial, sans-serif;
}

.navbar {
    display: flex;
    justify-content: space-between;
    align-items: center;
    background: #ff6600;
    padding: 15px 30px;
}

.navbar .logo {
    font-size: 24px;
    font-weight: bold;
    color: white;
    text-decoration: none;
}

.nav-links {
    display: flex;
    gap: 15px;
}

.nav-links a {
    text-decoration: none;
    color: white;
    font-size: 18px;
    padding: 8px 12px;
    border-radius: 5px;
}

.nav-links a:hover {
    background: rgba(255, 255, 255, 0.3);
}
</style>

<!-- Navbar -->
<nav class="navbar">
    <a href="index.jsp" class="logo">ZUMBA</a>
    <div class="nav-links">
        <a href="batches.jsp">Batches</a>
        <a href="students.jsp">Students</a>
    </div>
</nav>
