package com.zumba.bean;

public class Batches {
    private int batchId;
    private String batchType;
    private String batchTime;

    public Batches() {}

    public Batches(int batchId, String batchType, String batchTime) {
        this.batchId = batchId;
        this.batchType = batchType;
        this.batchTime = batchTime;
    }

    public int getBatchId() {
        return batchId;
    }

    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }

    public String getBatchType() {
        return batchType;
    }

    public void setBatchType(String batchType) {
        this.batchType = batchType;
    }

    public String getBatchTime() {
        return batchTime;
    }

    public void setBatchTime(String batchTime) {
        this.batchTime = batchTime;
    }

    @Override
    public String toString() {
        return batchType + " " + batchTime;
    }
}
