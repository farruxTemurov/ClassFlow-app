package com.zumba.controller;

import com.zumba.bean.Batches;
import com.zumba.bean.Students;
import com.zumba.service.BatchesService;
import com.zumba.service.StudentsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/StudentsController")
public class StudentsController extends HttpServlet {
    private StudentsService studentsService;
    private BatchesService batchesService;

    public void init() {
        studentsService = new StudentsService();
        batchesService = new BatchesService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("DEBUG: StudentsController - Handling GET request...");

        // Fetch batches
        List<Batches> batchesList = batchesService.getAllBatches();
        request.setAttribute("batchesList", batchesList);

        System.out.println("DEBUG: Number of batches retrieved: " + (batchesList != null ? batchesList.size() : "null"));

        // Forward to students.jsp
        request.getRequestDispatcher("students.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("DEBUG: StudentsController POST request received");

        String action = request.getParameter("action");
        System.out.println("DEBUG: Action = " + action);

        if ("register".equals(action)) {
            handleRegistration(request, response);
        } else if ("search".equals(action)) {
            handleSearch(request, response);
        }
    }

    private void handleRegistration(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name").trim();
        String email = request.getParameter("email").trim().toLowerCase();
        String telephone = request.getParameter("telephone").trim();
        String batchIdStr = request.getParameter("batch_id");

        Integer batchId = (batchIdStr == null || batchIdStr.isEmpty()) ? null : Integer.parseInt(batchIdStr);

        System.out.println("DEBUG: Name = " + name + ", Email = " + email + ", Telephone = " + telephone + ", Batch ID = " + batchId);

        Students student = new Students(name, telephone, email, batchId);
        boolean isAdded = studentsService.addStudent(student);

        if (isAdded) {
            System.out.println("DEBUG: Student registration successful!");
            response.sendRedirect("StudentsController");
        } else {
            System.out.println("DEBUG: Student registration failed!");
            response.sendRedirect("students.jsp?error=true");
        }
    }

    private void handleSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("search_name").trim();
        String email = request.getParameter("search_email").trim().toLowerCase();

        System.out.println("DEBUG: Searching for Name: " + name + ", Email: " + email);

        Students student = studentsService.getStudent(name, email);
        List<Batches> batchesList = batchesService.getAllBatches();
        request.setAttribute("batchesList", batchesList);

        if (student == null) {
            request.setAttribute("message", "No student found.");
        } else {
            request.setAttribute("student", student);
        }

        request.getRequestDispatcher("students.jsp").forward(request, response);
    }
}
