package com.zumba.bean;

public class Batches {
	private int batchId;
	private String batchName;
	private int scheduleId; // Foreign Key Reference
	private int instructorId; // Foreign Key Reference

	public Batches() {
		super();
	}

	public Batches(int batchId, String batchName, int scheduleId, int instructorId) {
		super();
		this.batchId = batchId;
		this.batchName = batchName;
		this.scheduleId = scheduleId;
		this.instructorId = instructorId;
	}

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	public int getInstructorId() {
		return instructorId;
	}

	public void setInstructorId(int instructorId) {
		this.instructorId = instructorId;
	}
}
