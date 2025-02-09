package com.zumba.service;

import com.zumba.resource.DatabaseResource;
import com.zumba.bean.Batches;
import com.zumba.dao.BatchesDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BatchesService {
	private BatchesDAO batchesDAO;
	private Connection conn;

	public BatchesService() {
		this.conn = DatabaseResource.getDbConnection();
		this.batchesDAO = new BatchesDAO(conn);
	}

	public List<Batches> getAllBatches() {
		try {
			return batchesDAO.getAllBatches();
		} catch (SQLException e) {
			e.printStackTrace(); // Log error for debugging
			return null; // Return null or handle the error in another way
		}
	}

	public void addBatch(Batches batch) {
		try {
			batchesDAO.addBatch(batch);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteBatch(int batchId) {
		try {
			batchesDAO.deleteBatch(batchId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
