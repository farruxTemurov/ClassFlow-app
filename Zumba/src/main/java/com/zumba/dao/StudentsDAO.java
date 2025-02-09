package com.zumba.dao;

import com.zumba.bean.Students;
import com.zumba.resource.DatabaseResource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentsDAO {
	private Connection connection = DatabaseResource.getDbConnection();

	public StudentsDAO(Connection connection) {
		this.connection = connection;
	}

	// Add a new student
	public void addStudent(Students student) throws SQLException {
		String sql = "INSERT INTO students (name, email, phone_number, registration_date) VALUES (?, ?, ?, ?)";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, student.getName());
			stmt.setString(2, student.getEmail());
			stmt.setString(3, student.getPhoneNumber());
			stmt.setDate(4, Date.valueOf(student.getRegistrationDate())); // Convert LocalDate to SQL Date
			stmt.executeUpdate();
		}
	}

	// Get a student by ID
	public Students getStudentById(int studentId) throws SQLException {
		String sql = "SELECT * FROM students WHERE student_id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, studentId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new Students(rs.getInt("student_id"), rs.getString("name"), rs.getString("email"),
						rs.getString("phone_number"), rs.getDate("registration_date").toLocalDate());
			}
		}
		return null;
	}

	// Get all students
	public List<Students> getAllStudents() throws SQLException {
		List<Students> students = new ArrayList<>();
		String sql = "SELECT * FROM students";
		try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				students.add(new Students(rs.getInt("student_id"), rs.getString("name"), rs.getString("email"),
						rs.getString("phone_number"), rs.getDate("registration_date").toLocalDate()));
			}
		}
		return students;
	}

	// Delete student by ID
	public void deleteStudent(int studentId) throws SQLException {
		String sql = "DELETE FROM students WHERE student_id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, studentId);
			stmt.executeUpdate();
		}
	}
}
