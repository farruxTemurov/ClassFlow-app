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
    private BatchesService batchesService = new BatchesService();

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
        }
    }

    private void addBatch(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String batchType = request.getParameter("batchType");
        String batchTime = request.getParameter("batchTime");

        if (batchType.isEmpty() || batchTime.isEmpty()) {
            request.setAttribute("error", "Batch Type and Time cannot be empty.");
        } else {
            Batches batch = new Batches(0, batchType, batchTime);
            if (batchesService.addBatch(batch)) {
                request.setAttribute("message", "Batch added successfully!");
            } else {
                request.setAttribute("error", "Failed to add batch!");
            }
        }
        getAllBatches(request, response);
    }

    private void updateBatch(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int batchId = Integer.parseInt(request.getParameter("batchId"));
            String batchType = request.getParameter("batchType");
            String batchTime = request.getParameter("batchTime");

            if (batchType.isEmpty() || batchTime.isEmpty()) {
                request.setAttribute("error", "Batch Type and Time cannot be empty.");
            } else {
                Batches batch = new Batches(batchId, batchType, batchTime);
                if (batchesService.updateBatch(batch)) {
                    request.setAttribute("message", "Batch updated successfully!");
                } else {
                    request.setAttribute("error", "Failed to update batch!");
                }
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid batch ID.");
        }
        getAllBatches(request, response);
    }

    private void deleteBatch(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int batchId = Integer.parseInt(request.getParameter("batchId"));
            if (batchesService.deleteBatch(batchId)) {
                request.setAttribute("message", "Batch deleted successfully!");
            } else {
                request.setAttribute("error", "Failed to delete batch!");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid batch ID format.");
        }
        getAllBatches(request, response);
    }

    private void getAllBatches(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("batches", batchesService.getAllBatches());
        request.getRequestDispatcher("batches.jsp").forward(request, response);
    }
}
