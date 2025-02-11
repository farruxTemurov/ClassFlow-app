package com.zumba.dao;

import com.zumba.bean.Batches;
import com.zumba.resource.DatabaseResource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BatchesDAO {

	private Connection con;

	public BatchesDAO() {
		this.con = DatabaseResource.getDbConnection();
	}

	// Add a new batch
	public boolean addBatch(Batches batch) {
		String query = "INSERT INTO batches (batch_type, batch_time) VALUES (?, ?)";
		try (PreparedStatement stmt = con.prepareStatement(query)) {
			stmt.setString(1, batch.getBatchType());
			stmt.setString(2, batch.getBatchTime());
			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// Get all batches
	public List<Batches> getAllBatches() {
		List<Batches> batchList = new ArrayList<>();
		String query = "SELECT * FROM batches";
		try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				batchList.add(
						new Batches(rs.getInt("batch_id"), rs.getString("batch_type"), rs.getString("batch_time")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return batchList;
	}

	// Get batch by ID
	public Batches getBatchById(int id) {
		String query = "SELECT * FROM batches WHERE batch_id = ?";
		try (PreparedStatement stmt = con.prepareStatement(query)) {
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new Batches(rs.getInt("batch_id"), rs.getString("batch_type"), rs.getString("batch_time"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Update batch
	public boolean updateBatch(Batches batch) {
		String query = "UPDATE batches SET batch_type = ?, batch_time = ? WHERE batch_id = ?";
		try (PreparedStatement stmt = con.prepareStatement(query)) {
			stmt.setString(1, batch.getBatchType());
			stmt.setString(2, batch.getBatchTime());
			stmt.setInt(3, batch.getBatchId());
			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// Delete batch
	public boolean deleteBatch(int id) {
		String query = "DELETE FROM batches WHERE batch_id = ?";
		try (PreparedStatement stmt = con.prepareStatement(query)) {
			stmt.setInt(1, id);
			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
