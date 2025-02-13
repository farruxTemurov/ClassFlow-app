package com.zumba.dao;

import com.zumba.bean.Students;
import com.zumba.bean.Batches;
import com.zumba.resource.DatabaseResource;

import java.sql.*;

public class StudentsDAO {
    private Connection con;

    public StudentsDAO() {
        this.con = DatabaseResource.getDbConnection();
    }

    public boolean addStudent(Students student) {
        String query = "INSERT INTO students (name, telephone, email, batch_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, student.getName());
            stmt.setString(2, student.getTelephone());
            stmt.setString(3, student.getEmail());
            if (student.getBatchId() > 0) {
                stmt.setInt(4, student.getBatchId());
            } else {
                stmt.setNull(4, Types.INTEGER);
            }
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Students searchStudent(String name, String email) {
        String query = "SELECT s.student_id, s.name, s.telephone, s.email, b.batch_id, b.batch_type, b.batch_time " +
                "FROM students s LEFT JOIN batches b ON s.batch_id = b.batch_id " +
                "WHERE s.name = ? AND s.email = ?";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int batchId = rs.getInt("batch_id");
                String batchType = rs.getString("batch_type");
                String batchTime = rs.getString("batch_time");
                return new Students(rs.getInt("student_id"), rs.getString("name"), rs.getString("telephone"),
                        rs.getString("email"), batchId, batchType, batchTime);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
