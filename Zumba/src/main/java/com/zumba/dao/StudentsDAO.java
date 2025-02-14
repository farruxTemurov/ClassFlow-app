package com.zumba.dao;

import com.zumba.bean.Students;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentsDAO {
    private Connection connection;

    public StudentsDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/zumba_db", "root", "your_password");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ✅ Fetch all students with batch details
    public List<Students> getAllStudents() {
        List<Students> students = new ArrayList<>();
        String sql = "SELECT s.student_id, s.name, s.telephone, s.email, s.batch_id, " +
                     "b.batch_type, b.batch_time FROM students s " +
                     "LEFT JOIN batches b ON s.batch_id = b.batch_id";  // JOIN batches table

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                students.add(new Students(
                        rs.getInt("student_id"),
                        rs.getString("name"),
                        rs.getString("telephone"),
                        rs.getString("email"),
                        rs.getInt("batch_id"),
                        rs.getString("batch_type"),  // Get batchType
                        rs.getString("batch_time")   // Get batchTime
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
    
 // ✅ Update student's batch assignment
    public boolean updateStudentBatch(int studentId, int batchId) {
        String sql = "UPDATE students SET batch_id = ? WHERE student_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, batchId);
            pstmt.setInt(2, studentId);
            return pstmt.executeUpdate() > 0; // Returns true if updated successfully
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
 // ✅ Add a new student to the database
    public boolean addStudent(Students student) {
        String sql = "INSERT INTO students (name, telephone, email, batch_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getTelephone());
            pstmt.setString(3, student.getEmail());
            pstmt.setObject(4, student.getBatchId() > 0 ? student.getBatchId() : null); // Allow null batch_id
            
            return pstmt.executeUpdate() > 0; // Returns true if student was added
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

 // ✅ Search for a student by name and email
    public Students searchStudent(String name, String email) {
        String sql = "SELECT student_id, name, telephone, email, batch_id FROM students WHERE name = ? AND email = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Students(
                        rs.getInt("student_id"),
                        rs.getString("name"),
                        rs.getString("telephone"),
                        rs.getString("email"),
                        rs.getInt("batch_id")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if no student is found
    }


}
