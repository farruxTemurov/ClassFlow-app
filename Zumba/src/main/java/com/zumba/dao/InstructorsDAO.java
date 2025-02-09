package com.zumba.dao;

import com.zumba.bean.Instructors;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InstructorsDAO {
	private Connection connection;

	public InstructorsDAO(Connection connection) {
		this.connection = connection;
	}

	// Get all instructors
	public List<Instructors> getAllInstructors() throws SQLException {
		List<Instructors> instructorList = new ArrayList<>();
		String query = "SELECT * FROM instructors";
		try (PreparedStatement pstmt = connection.prepareStatement(query); ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				instructorList.add(new Instructors(rs.getInt("instructor_id"), rs.getString("instructor_name")));
			}
		}
		return instructorList;
	}

	// Get instructor by ID
	public Instructors getInstructorById(int instructorId) throws SQLException {
		String query = "SELECT * FROM instructors WHERE instructor_id = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(query)) {
			pstmt.setInt(1, instructorId);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return new Instructors(rs.getInt("instructor_id"), rs.getString("instructor_name"));
				}
			}
		}
		return null;
	}
}
