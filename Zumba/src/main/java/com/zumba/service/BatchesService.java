package com.zumba.service;

import java.util.List;
import com.zumba.bean.Batches;
import com.zumba.dao.BatchesDAO;

public class BatchesService {
	private BatchesDAO batchesDAO;

	public BatchesService() {
		this.batchesDAO = new BatchesDAO();
	}

	// Add a new batch
	public boolean addBatch(Batches batch) {
		return batchesDAO.addBatch(batch);
	}

	// Retrieve all batches
	public List<Batches> getAllBatches() {
		return batchesDAO.getAllBatches();
	}

	// Retrieve batch by ID
	public Batches getBatchById(int batchId) {
		return batchesDAO.getBatchById(batchId);
	}

	// Update batch details
	public boolean updateBatch(Batches batch) {
		return batchesDAO.updateBatch(batch);
	}

	// Delete batch
	public boolean deleteBatch(int batchId) {
		return batchesDAO.deleteBatch(batchId);
	}
}
