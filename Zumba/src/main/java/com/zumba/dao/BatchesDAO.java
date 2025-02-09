package com.zumba.dao;

import com.zumba.bean.Batches;
import com.zumba.resource.DatabaseResource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BatchesDAO {
	private Connection connection = DatabaseResource.getDbConnection();

	public BatchesDAO(Connection connection) {
		this.connection = connection;
	}

	// Add a new batch
	public void addBatch(Batches batch) throws SQLException {
		String sql = "INSERT INTO batches (batch_name, schedule_id, instructor_id) VALUES (?, ?, ?)";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, batch.getBatchName());
			stmt.setInt(2, batch.getScheduleId());
			stmt.setInt(3, batch.getInstructorId());
			stmt.executeUpdate();
		}
	}

	// Get a batch by ID
	public Batches getBatchById(int batchId) throws SQLException {
		String sql = "SELECT * FROM batches WHERE batch_id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, batchId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new Batches(rs.getInt("batch_id"), rs.getString("batch_name"), rs.getInt("schedule_id"),
						rs.getInt("instructor_id"));
			}
		}
		return null;
	}

	// Get all batches
	public List<Batches> getAllBatches() throws SQLException {
		List<Batches> batches = new ArrayList<>();
		String sql = "SELECT * FROM batches";
		try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				batches.add(new Batches(rs.getInt("batch_id"), rs.getString("batch_name"), rs.getInt("schedule_id"),
						rs.getInt("instructor_id")));
			}
		}
		return batches;
	}

	// Delete batch by ID
	public void deleteBatch(int batchId) throws SQLException {
		String sql = "DELETE FROM batches WHERE batch_id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, batchId);
			stmt.executeUpdate();
		}
	}
}
