package com.zumba.controller;

import com.zumba.bean.Batches;
import com.zumba.bean.Students;
import com.zumba.service.BatchesService;
import com.zumba.service.StudentsService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Define servlet URL pattern
@WebServlet("/students")
public class StudentsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentsService studentsService;

	// Initialize Service
	public void init() {
		studentsService = new StudentsService();
	}

	// Handle GET requests (Retrieve all students)
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Students> studentsList = studentsService.getAllStudents();

		// Debugging: Print student list in console
		System.out.println("Students List: " + studentsList);

		request.setAttribute("students", studentsList);
		request.getRequestDispatcher("enrollments.jsp").forward(request, response);
	}

	// Handle POST requests (Add a new student)
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String phoneNumber = request.getParameter("phoneNumber");
			LocalDate registrationDate = LocalDate.parse(request.getParameter("registrationDate"));

			Students student = new Students(0, name, email, phoneNumber, registrationDate);
			studentsService.addStudent(student);

			response.sendRedirect("students");
		} catch (Exception e) {
			throw new ServletException("Error adding student", e);
		}
	}

	// Handle DELETE requests (Remove a student)
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int studentId = Integer.parseInt(request.getParameter("id"));
			studentsService.deleteStudent(studentId);
			response.sendRedirect("students");
		} catch (Exception e) {
			throw new ServletException("Error deleting student", e);
		}
	}
}
