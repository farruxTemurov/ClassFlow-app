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

	// ✅ Ensure getAllBatches runs on GET request
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getAllBatches(request, response);
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
		default:
			response.sendRedirect("batches.jsp");
			break;
		}
	}

	// ✅ Add a new batch
	private void addBatch(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String batchType = request.getParameter("batchType"); // Fixed parameter name
		String batchTime = request.getParameter("batchTime"); // Fixed parameter name

		if (batchType == null || batchType.trim().isEmpty() || batchTime == null || batchTime.trim().isEmpty()) {
			request.setAttribute("error", "Batch Type and Time cannot be empty.");
			getAllBatches(request, response);
			return;
		}

		Batches batch = new Batches(0, batchType, batchTime);
		boolean isAdded = batchesService.addBatch(batch);

		if (isAdded) {
			request.setAttribute("message", "Batch added successfully!");
		} else {
			request.setAttribute("error", "Failed to add batch!");
		}
		getAllBatches(request, response);
	}

	// ✅ Retrieve all batches (Updated to run on GET requests)
	private void getAllBatches(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Batches> batchList = batchesService.getAllBatches();
		request.setAttribute("batches", batchList);
		request.getRequestDispatcher("batches.jsp").forward(request, response);
	}

	// ✅ Update a batch
	private void updateBatch(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int batchId = Integer.parseInt(request.getParameter("batchId")); // Fixed parameter name
			String batchType = request.getParameter("batchType"); // Fixed parameter name
			String batchTime = request.getParameter("batchTime"); // Fixed parameter name

			if (batchType == null || batchType.trim().isEmpty() || batchTime == null || batchTime.trim().isEmpty()) {
				request.setAttribute("error", "Batch Type and Time cannot be empty.");
				getAllBatches(request, response);
				return;
			}

			Batches batch = new Batches(batchId, batchType, batchTime);
			boolean isUpdated = batchesService.updateBatch(batch);

			if (isUpdated) {
				request.setAttribute("message", "Batch updated successfully!");
			} else {
				request.setAttribute("error", "Failed to update batch!");
			}
		} catch (NumberFormatException e) {
			request.setAttribute("error", "Invalid batch ID.");
		}
		getAllBatches(request, response);
	}

	// ✅ Delete a batch (Prevents NumberFormatException)
	private void deleteBatch(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String batchIdStr = request.getParameter("batchId"); // Fixed parameter name

		if (batchIdStr == null || batchIdStr.trim().isEmpty()) {
			request.setAttribute("error", "Batch ID is missing.");
			getAllBatches(request, response);
			return;
		}

		try {
			int batchId = Integer.parseInt(batchIdStr);
			boolean isDeleted = batchesService.deleteBatch(batchId);

			if (isDeleted) {
				request.setAttribute("message", "Batch deleted successfully!");
			} else {
				request.setAttribute("error", "Failed to delete batch!");
			}
		} catch (NumberFormatException e) {
			request.setAttribute("error", "Invalid batch ID format.");
		}
		getAllBatches(request, response);
	}
}
