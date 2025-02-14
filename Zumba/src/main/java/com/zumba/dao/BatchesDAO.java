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
        try (PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, batch.getBatchType());
            ps.setString(2, batch.getBatchTime());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("DEBUG: Batch added successfully.");
                return true;
            } else {
                System.out.println("DEBUG: Batch insertion failed.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("DEBUG: SQL Exception while adding batch: " + e.getMessage());
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

            int rowsUpdated = ps.executeUpdate();
            System.out.println("DEBUG: Rows updated = " + rowsUpdated);
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.out.println("DEBUG: SQL Exception while updating batch: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Delete a batch
    public boolean deleteBatch(int batchId) {
        String query = "DELETE FROM batches WHERE batch_id = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, batchId);
            int rowsDeleted = ps.executeUpdate();
            System.out.println("DEBUG: Rows deleted = " + rowsDeleted);
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.out.println("DEBUG: SQL Exception while deleting batch: " + e.getMessage());
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

            System.out.println("DEBUG: Fetching all batches from database...");

            while (rs.next()) {
                Batches batch = new Batches(
                        rs.getInt("batch_id"),
                        rs.getString("batch_type"),
                        rs.getString("batch_time")
                );
                batchesList.add(batch);
            }

            System.out.println("DEBUG: Number of batches fetched: " + batchesList.size());

        } catch (SQLException e) {
            System.out.println("DEBUG: SQL Exception while fetching batches: " + e.getMessage());
            e.printStackTrace();
        }

        if (batchesList.isEmpty()) {
            System.out.println("DEBUG: No batches found in database.");
        }

        return batchesList;
    }
}
