package com.zumba.bean;

public class Batches {
	private int batchId;
	private String batchName;
	private String schedule;
	private String instructor;

	public Batches() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Batches(int batchId, String batchName, String schedule, String instructor) {
		super();
		this.batchId = batchId;
		this.batchName = batchName;
		this.schedule = schedule;
		this.instructor = instructor;
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

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

}
