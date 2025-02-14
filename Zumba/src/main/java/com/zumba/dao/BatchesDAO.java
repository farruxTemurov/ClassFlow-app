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
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, batch.getBatchType());
            ps.setString(2, batch.getBatchTime());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update a batch
    public boolean updateBatch(Batches batch) {
        String query = "UPDATE batches SET batch_type = ?, batch_time = ? WHERE batch_id = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, batch.getBatchType());
            ps.setString(2, batch.getBatchTime());
            ps.setInt(3, batch.getBatchId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete a batch
    public boolean deleteBatch(int batchId) {
        String query = "DELETE FROM batches WHERE batch_id = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, batchId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get all batches
    public List<Batches> getAllBatches() {
        List<Batches> batchesList = new ArrayList<>();
        String query = "SELECT * FROM batches";
        try (PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Batches batch = new Batches(
                        rs.getInt("batch_id"),
                        rs.getString("batch_type"),
                        rs.getString("batch_time")
                );
                batchesList.add(batch);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return batchesList;
    }
}
