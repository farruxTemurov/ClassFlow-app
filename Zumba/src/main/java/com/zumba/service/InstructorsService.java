package com.zumba.service;

import com.zumba.bean.Instructors;
import com.zumba.dao.InstructorsDAO;
import com.zumba.resource.DatabaseResource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class InstructorsService {
	private InstructorsDAO instructorsDAO;

	public InstructorsService() {
		Connection connection = DatabaseResource.getDbConnection();
		this.instructorsDAO = new InstructorsDAO(connection);
	}

	public List<Instructors> getAllInstructors() throws SQLException {
		return instructorsDAO.getAllInstructors();
	}

	public Instructors getInstructorById(int instructorId) throws SQLException {
		return instructorsDAO.getInstructorById(instructorId);
	}
}
