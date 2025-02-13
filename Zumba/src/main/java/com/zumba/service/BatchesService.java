package com.zumba.service;

import com.zumba.bean.Batches;
import com.zumba.dao.BatchesDAO;

import java.util.List;

public class BatchesService {
    private BatchesDAO batchesDAO;

    public BatchesService() {
        this.batchesDAO = new BatchesDAO();
    }

    public List<Batches> getAllBatches() {
        return batchesDAO.getAllBatches();
    }
}
