package com.zumba.service;

import com.zumba.bean.Batches;
import com.zumba.dao.BatchesDAO;
import com.zumba.resource.DatabaseResource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BatchesService {
	private BatchesDAO batchesDAO;

	public BatchesService() {
		Connection connection = DatabaseResource.getDbConnection();
		this.batchesDAO = new BatchesDAO(connection);
	}

	// Add a new batch
	public boolean addBatch(Batches batch) {
		try {
			if (batch.getBatchName() == null || batch.getBatchName().trim().isEmpty()) {
				return false;
			}
			batchesDAO.addBatch(batch);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// Get a batch by ID
	public Batches getBatchById(int batchId) {
		try {
			return batchesDAO.getBatchById(batchId);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	// Get all batches
	public List<Batches> getAllBatches() {
		try {
			return batchesDAO.getAllBatches();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	// Delete a batch
	public boolean deleteBatch(int batchId) {
		try {
			batchesDAO.deleteBatch(batchId);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
