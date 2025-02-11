package com.zumba.controller;

import com.zumba.bean.Enrollments;
import com.zumba.bean.Students;
import com.zumba.bean.Batches;
import com.zumba.service.EnrollmentsService;
import com.zumba.service.StudentsService;
import com.zumba.service.BatchesService;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/enrollments")
public class EnrollmentsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EnrollmentsService enrollmentsService;
	private StudentsService studentsService;
	private BatchesService batchesService;

	public void init() {
		enrollmentsService = new EnrollmentsService();
		studentsService = new StudentsService();
		batchesService = new BatchesService();
	}

	// Handle GET requests (Retrieve enrollments + students + batches)
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Students> studentsList = studentsService.getAllStudents();
		List<Batches> batchList = batchesService.getAllBatches();
		List<Enrollments> enrollmentList = enrollmentsService.getAllEnrollments();

		request.setAttribute("students", studentsList);
		request.setAttribute("batches", batchList);
		request.setAttribute("enrollments", enrollmentList);

		request.getRequestDispatcher("enrollments.jsp").forward(request, response);
	}

	// Handle POST requests (Add a new enrollment)
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int studentId = Integer.parseInt(request.getParameter("studentId"));
			int batchId = Integer.parseInt(request.getParameter("batchId"));

			// Set today's date as enrollment date (since form doesn't have it)
			LocalDate enrollmentDate = LocalDate.now();

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
