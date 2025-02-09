package com.zumba.service;

import com.zumba.bean.Enrollments;
import com.zumba.dao.EnrollmentsDAO;
import com.zumba.resource.DatabaseResource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class EnrollmentsService {
	private EnrollmentsDAO enrollmentsDAO;

	public EnrollmentsService() {
		Connection connection = DatabaseResource.getDbConnection();
		this.enrollmentsDAO = new EnrollmentsDAO(connection);
	}

	// Add an enrollment
	public boolean addEnrollment(Enrollments enrollment) {
		try {
			if (enrollment.getStudentId() <= 0 || enrollment.getBatchId() <= 0) {
				return false;
			}
			enrollmentsDAO.addEnrollment(enrollment);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// Get an enrollment by ID
	public Enrollments getEnrollmentById(int enrollmentId) {
		try {
			return enrollmentsDAO.getEnrollmentById(enrollmentId);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	// Get all enrollments
	public List<Enrollments> getAllEnrollments() {
		try {
			return enrollmentsDAO.getAllEnrollments();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	// Delete an enrollment
	public boolean deleteEnrollment(int enrollmentId) {
		try {
			enrollmentsDAO.deleteEnrollment(enrollmentId);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
