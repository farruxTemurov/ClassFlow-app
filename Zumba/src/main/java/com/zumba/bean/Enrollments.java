package com.zumba.bean;

import java.time.LocalDate;

public class Enrollments {
	private int enrollmentId;
	private int studentId;
	private int batchId;
	private LocalDate enrollmentDate; // Use LocalDate instead of String

	public Enrollments() {
		super();
	}

	public Enrollments(int enrollmentId, int studentId, int batchId, LocalDate enrollmentDate) {
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

	public LocalDate getEnrollmentDate() {
		return enrollmentDate;
	}

	public void setEnrollmentDate(LocalDate enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}
}
