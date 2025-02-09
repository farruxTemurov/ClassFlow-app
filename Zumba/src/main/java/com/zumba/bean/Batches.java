package com.zumba.bean;

public class Batches {
	private int batchId;
	private String batchName;
	private int scheduleId;
	private String scheduleName; // Add this
	private int instructorId;
	private String instructorName; // Add this

	// Constructor
	public Batches(int batchId, String batchName, int scheduleId, String scheduleName, int instructorId,
			String instructorName) {
		this.batchId = batchId;
		this.batchName = batchName;
		this.scheduleId = scheduleId;
		this.scheduleName = scheduleName;
		this.instructorId = instructorId;
		this.instructorName = instructorName;
	}

	// Getters and Setters
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

	public String getScheduleName() {
		return scheduleName;
	} // Add Getter

	public void setScheduleName(String scheduleName) {
		this.scheduleName = scheduleName;
	} // Add Setter

	public int getInstructorId() {
		return instructorId;
	}

	public void setInstructorId(int instructorId) {
		this.instructorId = instructorId;
	}

	public String getInstructorName() {
		return instructorName;
	} // Add Getter

	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	} // Add Setter
}
