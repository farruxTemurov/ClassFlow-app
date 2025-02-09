package com.zumba.controller;

import com.zumba.bean.Batches;
import com.zumba.service.BatchesService;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Define servlet URL pattern
@WebServlet("/batches")
public class BatchesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BatchesService batchesService;

	// Initialize Service
	public void init() {
		batchesService = new BatchesService();
	}

	// Handle GET requests (Retrieve all batches)
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Batches> batchList = batchesService.getAllBatches();
		request.setAttribute("batches", batchList);
		request.getRequestDispatcher("batches.jsp").forward(request, response);
	}

	// Handle POST requests (Add a new batch)
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String batchName = request.getParameter("batchName");
			int scheduleId = Integer.parseInt(request.getParameter("scheduleId"));
			int instructorId = Integer.parseInt(request.getParameter("instructorId"));

			Batches batch = new Batches(0, batchName, scheduleId, instructorId);
			batchesService.addBatch(batch);

			response.sendRedirect("batches");
		} catch (Exception e) {
			throw new ServletException("Error adding batch", e);
		}
	}

	// Handle DELETE requests (Remove a batch)
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int batchId = Integer.parseInt(request.getParameter("id"));
			batchesService.deleteBatch(batchId);
			response.sendRedirect("batches");
		} catch (Exception e) {
			throw new ServletException("Error deleting batch", e);
		}
	}
}
