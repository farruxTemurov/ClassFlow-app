package com.zumba.resource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseResource {
    private static final String URL = "jdbc:mysql://localhost:3306/zumba_app";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // type your MySQL password here

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("DEBUG: MySQL Driver not found: " + e.getMessage());
        }
    }

    public static Connection getDbConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("DEBUG: Failed to establish database connection: " + e.getMessage());
            return null;
        }
    }
}
