package com.zumba.controller;

import com.zumba.bean.Enrollments;
import com.zumba.service.EnrollmentsService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Define servlet URL pattern
@WebServlet("/enrollments")
public class EnrollmentsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EnrollmentsService enrollmentsService;

	// Initialize Service
	public void init() {
		enrollmentsService = new EnrollmentsService();
	}

	// Handle GET requests (Retrieve all enrollments)
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Enrollments> enrollmentList = enrollmentsService.getAllEnrollments();
		request.setAttribute("enrollments", enrollmentList);
		request.getRequestDispatcher("enrollments.jsp").forward(request, response);
	}

	// Handle POST requests (Add a new enrollment)
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int studentId = Integer.parseInt(request.getParameter("studentId"));
			int batchId = Integer.parseInt(request.getParameter("batchId"));
			LocalDate enrollmentDate = LocalDate.parse(request.getParameter("enrollmentDate"));

			Enrollments enrollment = new Enrollments(0, studentId, batchId, enrollmentDate);
			enrollmentsService.addEnrollment(enrollment);

			response.sendRedirect("enrollments");
		} catch (Exception e) {
			throw new ServletException("Error adding enrollment", e);
		}
	}

	// Handle DELETE requests (Remove an enrollment)
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int enrollmentId = Integer.parseInt(request.getParameter("id"));
			enrollmentsService.deleteEnrollment(enrollmentId);
			response.sendRedirect("enrollments");
		} catch (Exception e) {
			throw new ServletException("Error deleting enrollment", e);
		}
	}
}
