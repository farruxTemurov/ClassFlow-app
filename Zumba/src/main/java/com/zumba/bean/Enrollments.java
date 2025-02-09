package com.zumba.bean;

import java.time.LocalDate;

public class Enrollments {
	private int enrollmentId;
	private int studentId;
	private String studentName; // ✅ Added for JSP
	private int batchId;
	private String batchName; // ✅ Added for JSP
	private LocalDate enrollmentDate;

	// ✅ Constructor without names (for inserting new enrollments)
	public Enrollments(int enrollmentId, int studentId, int batchId, LocalDate enrollmentDate) {
		this.enrollmentId = enrollmentId;
		this.studentId = studentId;
		this.batchId = batchId;
		this.enrollmentDate = enrollmentDate;
	}

	// ✅ Constructor with names (for displaying in JSP)
	public Enrollments(int enrollmentId, int studentId, String studentName, int batchId, String batchName,
			LocalDate enrollmentDate) {
		this.enrollmentId = enrollmentId;
		this.studentId = studentId;
		this.studentName = studentName;
		this.batchId = batchId;
		this.batchName = batchName;
		this.enrollmentDate = enrollmentDate;
	}

	// ✅ Getters and Setters
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

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
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

	public LocalDate getEnrollmentDate() {
		return enrollmentDate;
	}

	public void setEnrollmentDate(LocalDate enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}
}
