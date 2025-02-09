package com.zumba.controller;

import java.sql.SQLException;

import com.zumba.bean.Batches;
import com.zumba.bean.Schedules;
import com.zumba.bean.Instructors;
import com.zumba.service.BatchesService;
import com.zumba.service.SchedulesService;
import com.zumba.service.InstructorsService;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/batches")
public class BatchesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BatchesService batchesService;
	private SchedulesService schedulesService;
	private InstructorsService instructorsService;

	public void init() {
		batchesService = new BatchesService();
		schedulesService = new SchedulesService();
		instructorsService = new InstructorsService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<Batches> batchList = batchesService.getAllBatches();
			List<Schedules> scheduleList = schedulesService.getAllSchedules(); // May throw SQLException
			List<Instructors> instructorList = instructorsService.getAllInstructors(); // May throw SQLException

			request.setAttribute("batches", batchList);
			request.setAttribute("schedules", scheduleList);
			request.setAttribute("instructors", instructorList);

			request.getRequestDispatcher("batches.jsp").forward(request, response);
		} catch (SQLException e) {
			throw new ServletException("Error retrieving batches, schedules, or instructors", e);
		}
	}

}
