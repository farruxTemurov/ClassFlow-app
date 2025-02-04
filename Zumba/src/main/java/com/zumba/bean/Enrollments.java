package com.zumba.bean;

public class Enrollments {
	private int enrollmentId;
	private int studentId;
	private int batchId;
	private String enrollmentDate;

	public Enrollments() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Enrollments(int enrollmentId, int studentId, int batchId, String enrollmentDate) {
		super();
		this.enrollmentId = enrollmentId;
		this.studentId = studentId;
		this.batchId = batchId;
		this.enrollmentDate = enrollmentDate;
	}

	public int getEnrollmentId() {
		return enrollmentId;
	}

	public void setEnrollmentId(int enrollmentId) {
		this.enrollmentId = enrollmentId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public String getEnrollmentDate() {
		return enrollmentDate;
	}

	public void setEnrollmentDate(String enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}

}
