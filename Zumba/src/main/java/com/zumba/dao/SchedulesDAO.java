package com.zumba.dao;

import com.zumba.bean.Schedules;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SchedulesDAO {
	private Connection connection;

	public SchedulesDAO(Connection connection) {
		this.connection = connection;
	}

	// Get all schedules
	public List<Schedules> getAllSchedules() throws SQLException {
		List<Schedules> schedulesList = new ArrayList<>();
		String query = "SELECT * FROM schedules";
		try (PreparedStatement pstmt = connection.prepareStatement(query); ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				schedulesList.add(new Schedules(rs.getInt("schedule_id"), rs.getString("schedule_name")));
			}
		}
		return schedulesList;
	}

	// Get schedule by ID
	public Schedules getScheduleById(int scheduleId) throws SQLException {
		String query = "SELECT * FROM schedules WHERE schedule_id = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(query)) {
			pstmt.setInt(1, scheduleId);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return new Schedules(rs.getInt("schedule_id"), rs.getString("schedule_name"));
				}
			}
		}
		return null;
	}
}
