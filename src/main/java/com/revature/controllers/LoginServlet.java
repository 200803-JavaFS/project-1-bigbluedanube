package com.revature.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.models.User;

public class LoginServlet extends HttpServlet {			// This whole thing creates a login page.

	private static final long serialVersionUID = 1L;	// can't hurt program, but the warning under-lines annoyed me.

	public LoginServlet() {
		super();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		User u = new User();
		
		u.setUsername(req.getParameter("UserId"));
		u.setPassword(req.getParameter("password"));
		
		RequestDispatcher rd = null;
		PrintWriter out = res.getWriter();
		out.print("<h1> Hello from your doPost() method. </h1>");

		
		if(u.getUsername().equals("tom") && u.getPassword().equals("jerry")) {
			rd = req.getRequestDispatcher("success");
			rd.forward(req, res);
		} else {
			rd = req.getRequestDispatcher("index.html");
			rd.include(req, res);
			out.print("<span style='color:red; text-align:center'> Invalid Username/Password</span>");
		}
	}

}
