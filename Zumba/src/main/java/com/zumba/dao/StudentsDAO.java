package com.zumba.dao;

import com.zumba.bean.Students;
import com.zumba.resource.DatabaseResource;

import java.sql.*;

public class StudentsDAO {
    // Add Student
    public boolean addStudent(Students student) {
        String query = "INSERT INTO students (name, telephone, email, batch_id) VALUES (?, ?, ?, ?)";
        try (Connection con = DatabaseResource.getDbConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, student.getName());
            ps.setString(2, student.getTelephone());
            ps.setString(3, student.getEmail());

            if (student.getBatchId() != null) {
                ps.setInt(4, student.getBatchId());
                System.out.println("DEBUG: batch_id before insertion = " + student.getBatchId());
            } else {
                ps.setNull(4, Types.INTEGER);
                System.out.println("DEBUG: batch_id is NULL before insertion.");
            }

            int rowsInserted = ps.executeUpdate();
            System.out.println("DEBUG: Rows inserted = " + rowsInserted);
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get Student by Name and Email
    public Students getStudent(String name, String email) {
        String query = "SELECT s.*, b.batch_type, b.batch_time FROM students s " +
                       "LEFT JOIN batches b ON s.batch_id = b.batch_id " +
                       "WHERE LOWER(s.name) = LOWER(?) AND LOWER(s.email) = LOWER(?)";

        try (Connection con = DatabaseResource.getDbConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, name.trim().toLowerCase());
            ps.setString(2, email.trim().toLowerCase());

            System.out.println("DEBUG: Executing SQL Query: " + ps.toString());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Students student = new Students(
                        rs.getInt("student_id"),
                        rs.getString("name"),
                        rs.getString("telephone"),
                        rs.getString("email"),
                        rs.getObject("batch_id") != null ? rs.getInt("batch_id") : null,
                        rs.getString("batch_type"),
                        rs.getString("batch_time")
                );
                System.out.println("DEBUG: Student found: " + student.getName());
                return student;
            } else {
                System.out.println("DEBUG: No student found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
