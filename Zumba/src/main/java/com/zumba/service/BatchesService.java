package com.zumba.service;

import com.zumba.bean.Batches;
import com.zumba.dao.BatchesDAO;

import java.util.List;

public class BatchesService {

    private BatchesDAO batchesDAO;

    public BatchesService() {
        this.batchesDAO = new BatchesDAO();
    }

    public boolean addBatch(Batches batch) {
        return batchesDAO.addBatch(batch);
    }

    public boolean updateBatch(Batches batch) {
        return batchesDAO.updateBatch(batch);
    }

    public boolean deleteBatch(int batchId) {
        return batchesDAO.deleteBatch(batchId);
    }

    public List<Batches> getAllBatches() {
        return batchesDAO.getAllBatches();
    }
}
