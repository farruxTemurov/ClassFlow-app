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

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("search".equals(action)) {
            searchStudent(request, response);
        } else {
            response.sendRedirect("students.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("register".equals(action)) {
            registerStudent(request, response);
        } else {
            response.sendRedirect("students.jsp");
        }
    }

    private void registerStudent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Register request received");

        String name = request.getParameter("name");
        String telephone = request.getParameter("telephone");
        String email = request.getParameter("email");
        String batchIdStr = request.getParameter("batchId");

        int batchId = (batchIdStr != null && !batchIdStr.isEmpty()) ? Integer.parseInt(batchIdStr) : 0;
        Students student = new Students(0, name, telephone, email, batchId);

        boolean isRegistered = studentsService.registerStudent(student);

        if (isRegistered) {
            request.getSession().setAttribute("message", "Student registered successfully!");
        } else {
            request.getSession().setAttribute("error", "Failed to register student!");
        }
        response.sendRedirect("students.jsp");
    }

    private void searchStudent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Search request received");

        String name = request.getParameter("searchName");
        String email = request.getParameter("searchEmail");

        Students student = studentsService.searchStudent(name, email);
        if (student != null) {
            request.setAttribute("student", student);
        } else {
            request.setAttribute("error", "Student not found!");
        }
        request.getRequestDispatcher("students.jsp").forward(request, response);
    }
}
