package com.zumba.bean;

import java.time.LocalDate;

public class Students {
	private int studentId;
	private String name;
	private String email;
	private String phoneNumber;
	private LocalDate registrationDate; // Changed from String to LocalDate

	public Students() {
		super();
	}

	public Students(int studentId, String name, String email, String phoneNumber, LocalDate registrationDate) {
		this.studentId = studentId;
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.registrationDate = registrationDate;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public LocalDate getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDate registrationDate) {
		this.registrationDate = registrationDate;
	}
}
