package com.zumba.service;

import java.util.List;
import com.zumba.bean.Students;
import com.zumba.dao.StudentsDAO;

public class StudentsService {
	private StudentsDAO studentsDAO;

	public StudentsService() {
		this.studentsDAO = new StudentsDAO();
	}

	// Register a new student
	public boolean registerStudent(Students student) {
		return studentsDAO.addStudent(student);
	}

	// Search student by name and email
	public Students searchStudent(String name, String email) {
		return studentsDAO.searchStudent(name, email);
	}

	// Update student details
	public boolean updateStudent(Students student) {
		return studentsDAO.updateStudent(student);
	}

	// Delete a student
	public boolean deleteStudent(int studentId) {
		return studentsDAO.deleteStudent(studentId);
	}
}
