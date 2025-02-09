package com.zumba.dao;

import com.zumba.bean.Batches;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BatchesDAO {
	private Connection conn;

	public BatchesDAO(Connection conn) {
		this.conn = conn;
	}

	// ✅ Get All Batches (Including Schedule and Instructor Names)
	public List<Batches> getAllBatches() throws SQLException {
		List<Batches> batchList = new ArrayList<>();
		String query = "SELECT b.batch_id, b.batch_name, s.schedule_id, s.schedule_name, i.instructor_id, i.instructor_name "
				+ "FROM batches b " + "JOIN schedules s ON b.schedule_id = s.schedule_id "
				+ "JOIN instructors i ON b.instructor_id = i.instructor_id";

		try (PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				Batches batch = new Batches(rs.getInt("batch_id"), rs.getString("batch_name"), rs.getInt("schedule_id"),
						rs.getString("schedule_name"), rs.getInt("instructor_id"), rs.getString("instructor_name"));
				batchList.add(batch);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return batchList;
	}

	// ✅ Add a New Batch
	public void addBatch(Batches batch) throws SQLException {
		String query = "INSERT INTO batches (batch_name, schedule_id, instructor_id) VALUES (?, ?, ?)";
		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, batch.getBatchName());
			ps.setInt(2, batch.getScheduleId());
			ps.setInt(3, batch.getInstructorId());
			ps.executeUpdate();
		}
	}

	// ✅ Delete a Batch
	public void deleteBatch(int batchId) throws SQLException {
		String query = "DELETE FROM batches WHERE batch_id = ?";
		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, batchId);
			ps.executeUpdate();
		}
	}
}
