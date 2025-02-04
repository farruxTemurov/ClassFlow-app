package com.zumba.bean;

public class Students {
	private int studentId;
	private String name;
	private String email;
	private String phoneNumber;
	private String registrationDate;

	public Students() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Students(int studentId, String name, String email, String phoneNumber, String registrationDate) {
		super();
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

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

}
