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

@WebServlet("/BatchesController")
public class BatchesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BatchesService batchesService;

	public BatchesController() {
		this.batchesService = new BatchesService();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action == null) {
			response.sendRedirect("batches.jsp");
			return;
		}

		switch (action) {
		case "add":
			addBatch(request, response);
			break;
		case "update":
			updateBatch(request, response);
			break;
		case "delete":
			deleteBatch(request, response);
			break;
		case "view":
			getAllBatches(request, response);
			break;
		default:
			response.sendRedirect("batches.jsp");
			break;
		}
	}

	// ✅ Add a new batch
	private void addBatch(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String batchType = request.getParameter("batch_type");
		String batchTime = request.getParameter("batch_time");

		Batches batch = new Batches(0, batchType, batchTime);
		boolean isAdded = batchesService.addBatch(batch);

		if (isAdded) {
			request.setAttribute("message", "Batch added successfully!");
		} else {
			request.setAttribute("error", "Failed to add batch!");
		}
		request.getRequestDispatcher("batches.jsp").forward(request, response);
	}

	// ✅ Retrieve all batches
	private void getAllBatches(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Batches> batchList = batchesService.getAllBatches();
		request.setAttribute("batches", batchList);
		request.getRequestDispatcher("batches.jsp").forward(request, response);
	}

	// ✅ Update a batch
	private void updateBatch(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int batchId = Integer.parseInt(request.getParameter("batch_id"));
		String batchType = request.getParameter("batch_type");
		String batchTime = request.getParameter("batch_time");

		Batches batch = new Batches(batchId, batchType, batchTime);
		boolean isUpdated = batchesService.updateBatch(batch);

		if (isUpdated) {
			request.setAttribute("message", "Batch updated successfully!");
		} else {
			request.setAttribute("error", "Failed to update batch!");
		}
		request.getRequestDispatcher("batches.jsp").forward(request, response);
	}

	// ✅ Delete a batch
	private void deleteBatch(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int batchId = Integer.parseInt(request.getParameter("batch_id"));
		boolean isDeleted = batchesService.deleteBatch(batchId);

		if (isDeleted) {
			request.setAttribute("message", "Batch deleted successfully!");
		} else {
			request.setAttribute("error", "Failed to delete batch!");
		}
		request.getRequestDispatcher("batches.jsp").forward(request, response);
	}
}
