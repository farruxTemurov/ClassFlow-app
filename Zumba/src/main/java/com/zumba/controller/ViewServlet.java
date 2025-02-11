package com.zumba.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/view")
public class ViewServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String page = request.getParameter("page");

		if (page != null) {
			String viewPath = "/WEB-INF/views/" + page + ".jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect("index.html"); // Redirect to homepage if no page is specified
		}
	}
}
