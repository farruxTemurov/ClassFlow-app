package com.zumba.controller;

import com.zumba.bean.Students;
import com.zumba.service.StudentsService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/StudentsController")
public class StudentsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentsService studentsService;

	public StudentsController() {
		this.studentsService = new StudentsService();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action == null) {
			response.sendRedirect("students.jsp");
			return;
		}

		switch (action) {
		case "register":
			registerStudent(request, response);
			break;
		case "search":
			searchStudent(request, response);
			break;
		default:
			response.sendRedirect("students.jsp");
			break;
		}
	}

	// ✅ Register a new student
	private void registerStudent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String telephone = request.getParameter("telephone");
		String email = request.getParameter("email");
		String batchIdStr = request.getParameter("batch_id");

		int batchId = (batchIdStr != null && !batchIdStr.isEmpty()) ? Integer.parseInt(batchIdStr) : 0;

		Students student = new Students(0, name, telephone, email, batchId);
		boolean isRegistered = studentsService.registerStudent(student);

		if (isRegistered) {
			request.setAttribute("message", "Student registered successfully!");
		} else {
			request.setAttribute("error", "Failed to register student!");
		}
		request.getRequestDispatcher("students.jsp").forward(request, response);
	}

	// ✅ Search student by Name & Email
	private void searchStudent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");

		Students student = studentsService.searchStudent(name, email);

		if (student != null) {
			request.setAttribute("student", student);
		} else {
			request.setAttribute("error", "Student not found!");
		}
		request.getRequestDispatcher("students.jsp").forward(request, response);
	}
}
