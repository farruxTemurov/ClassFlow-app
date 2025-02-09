package com.zumba.service;

import com.zumba.bean.Students;
import com.zumba.dao.StudentsDAO;
import com.zumba.resource.DatabaseResource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class StudentsService {
	private StudentsDAO studentsDAO;

	public StudentsService() {
		Connection connection = DatabaseResource.getDbConnection();
		this.studentsDAO = new StudentsDAO(connection);
	}

	// Add a new student with validation
	public boolean addStudent(Students student) {
		try {
			if (student.getName() == null || student.getName().trim().isEmpty()) {
				return false; // Invalid input
			}
			studentsDAO.addStudent(student);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// Get a student by ID
	public Students getStudentById(int studentId) {
		try {
			return studentsDAO.getStudentById(studentId);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	// Get all students
	public List<Students> getAllStudents() {
		try {
			return studentsDAO.getAllStudents();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	// Delete a student
	public boolean deleteStudent(int studentId) {
		try {
			studentsDAO.deleteStudent(studentId);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
