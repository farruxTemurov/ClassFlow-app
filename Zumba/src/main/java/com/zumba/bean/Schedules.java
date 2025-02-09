package com.zumba.bean;

public class Schedules {
	private int scheduleId;
	private String scheduleName;

	public Schedules() {
	}

	public Schedules(int scheduleId, String scheduleName) {
		this.scheduleId = scheduleId;
		this.scheduleName = scheduleName;
	}

	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	public String getScheduleName() {
		return scheduleName;
	}

	public void setScheduleName(String scheduleName) {
		this.scheduleName = scheduleName;
	}
}
