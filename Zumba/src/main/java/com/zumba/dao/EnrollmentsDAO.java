package com.zumba.dao;

import com.zumba.bean.Enrollments;
import com.zumba.resource.DatabaseResource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentsDAO {
	private Connection connection = DatabaseResource.getDbConnection();

	public EnrollmentsDAO(Connection connection) {
		this.connection = connection;
	}

	// Enroll a student in a batch
	public void addEnrollment(Enrollments enrollment) throws SQLException {
		String sql = "INSERT INTO enrollments (student_id, batch_id, enrollment_date) VALUES (?, ?, ?)";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, enrollment.getStudentId());
			stmt.setInt(2, enrollment.getBatchId());
			stmt.setDate(3, Date.valueOf(enrollment.getEnrollmentDate())); // Convert LocalDate to SQL Date
			stmt.executeUpdate();
		}
	}

	// Get an enrollment by ID
	public Enrollments getEnrollmentById(int enrollmentId) throws SQLException {
		String sql = "SELECT * FROM enrollments WHERE enrollment_id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, enrollmentId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new Enrollments(rs.getInt("enrollment_id"), rs.getInt("student_id"), rs.getInt("batch_id"),
						rs.getDate("enrollment_date").toLocalDate());
			}
		}
		return null;
	}

	// Get all enrollments
	public List<Enrollments> getAllEnrollments() throws SQLException {
		List<Enrollments> enrollments = new ArrayList<>();
		String sql = "SELECT * FROM enrollments";
		try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				enrollments.add(new Enrollments(rs.getInt("enrollment_id"), rs.getInt("student_id"),
						rs.getInt("batch_id"), rs.getDate("enrollment_date").toLocalDate()));
			}
		}
		return enrollments;
	}

	// Delete an enrollment by ID
	public void deleteEnrollment(int enrollmentId) throws SQLException {
		String sql = "DELETE FROM enrollments WHERE enrollment_id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, enrollmentId);
			stmt.executeUpdate();
		}
	}
}
