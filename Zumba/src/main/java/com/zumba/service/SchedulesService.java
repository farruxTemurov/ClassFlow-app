package com.zumba.service;

import com.zumba.bean.Schedules;
import com.zumba.dao.SchedulesDAO;
import com.zumba.resource.DatabaseResource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SchedulesService {
	private SchedulesDAO schedulesDAO;

	public SchedulesService() {
		Connection connection = DatabaseResource.getDbConnection();
		this.schedulesDAO = new SchedulesDAO(connection);
	}

	public List<Schedules> getAllSchedules() throws SQLException {
		return schedulesDAO.getAllSchedules();
	}

	public Schedules getScheduleById(int scheduleId) throws SQLException {
		return schedulesDAO.getScheduleById(scheduleId);
	}
}
