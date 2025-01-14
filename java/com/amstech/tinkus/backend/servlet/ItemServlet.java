package com.amstech.tinkus.backend.servlet;

import java.io.IOException;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ItemServlet")
public class ItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public ItemServlet() {
       
    	System.out.println("ItemServlet :Object create");
    }

	
	public void init(ServletConfig config) throws ServletException {
	
		System.out.println("ItemServlet :Init Method");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("ItemServlet :Get Method");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
		System.out.println("ItemServlet :Post Method");
	}

	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("ItemServlet :Put Method");
	}

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("ItemServlet :Delete Method");
	}

	
	protected void doHead(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("ItemServlet :Head Method");
	}

	protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("ItemServlet :option Method");
	}

	public void destroy() {
		
		System.out.println("ItemServlet :Destroy Method");
	}
}
