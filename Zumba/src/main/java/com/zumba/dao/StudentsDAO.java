package com.zumba.dao;

import com.zumba.bean.Students;
import com.zumba.resource.DatabaseResource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentsDAO {

	private Connection con;

	public StudentsDAO() {
		this.con = DatabaseResource.getDbConnection();
	}

	// Add a new student
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

	// ✅ Search student by Name & Email (returns batch info if available)
	public Students searchStudent(String name, String email) {
		String query = "SELECT s.student_id, s.name, s.telephone, s.email, " + "b.batch_id, b.batch_type, b.batch_time "
				+ "FROM students s " + "LEFT JOIN batches b ON s.batch_id = b.batch_id "
				+ "WHERE s.name = ? AND s.email = ?";
		try (PreparedStatement stmt = con.prepareStatement(query)) {
			stmt.setString(1, name);
			stmt.setString(2, email);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				Students student = new Students(rs.getInt("student_id"), rs.getString("name"),
						rs.getString("telephone"), rs.getString("email"), rs.getInt("batch_id"));
				return student;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Update student
	public boolean updateStudent(Students student) {
		String query = "UPDATE students SET name = ?, telephone = ?, email = ?, batch_id = ? WHERE student_id = ?";
		try (PreparedStatement stmt = con.prepareStatement(query)) {
			stmt.setString(1, student.getName());
			stmt.setString(2, student.getTelephone());
			stmt.setString(3, student.getEmail());
			if (student.getBatchId() > 0) {
				stmt.setInt(4, student.getBatchId());
			} else {
				stmt.setNull(4, Types.INTEGER);
			}
			stmt.setInt(5, student.getStudentId());
			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// Delete student
	public boolean deleteStudent(int id) {
		String query = "DELETE FROM students WHERE student_id = ?";
		try (PreparedStatement stmt = con.prepareStatement(query)) {
			stmt.setInt(1, id);
			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
