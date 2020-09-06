package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.LoginDTO;
import com.revature.models.User;
import com.revature.services.LoginService;
import com.revature.services.UserService;

// req.getParameterMap().containsKey("username") returns a Boolean.
// req.getParameter("username") returns a String/an actual Object.

public class LoginController {
	
	private static LoginDTO lDTO = new LoginDTO();
	private static UserService us = new UserService();
	private static LoginService ls = new LoginService();
	private static ObjectMapper om = new ObjectMapper();
	
	

	public void login(HttpServletRequest req, HttpServletResponse res) throws IOException {

		if (req.getMethod().equals("GET")) {

			if (req.getParameterMap().containsKey("username") && req.getParameterMap().containsKey("password")) {
				User u = new User();
				
				u.setUsername(req.getParameter("username"));	// takes the input username/password and sets that to the Object.
				u.setPassword(req.getParameter("password"));
				
				//now we need to check if it matches user we want to create (business logic)
				//thus, need a service login

				if (ls.login(u)) {
					HttpSession ses = req.getSession();
					ses.setAttribute("user", u);
					ses.setAttribute("loggedin", true);
					res.setStatus(200);
					res.getWriter().println("Login Successful. Welcome to the Iron Bank of Braavos.");
				} else {
					HttpSession ses = req.getSession(false);
					if (ses != null) {
						ses.invalidate();
					}
					res.setStatus(401); // Status Code 401 = Unauthorized.
					res.getWriter().println("Login failed.");
				}
			} 
			
		} else if (req.getMethod().equals("POST")) {
				// this is how a login SHOULD be handled; send the credentials in the body of a POST request.
				BufferedReader reader = req.getReader();
				
				StringBuilder sb = new StringBuilder();
				
				String line = reader.readLine();
				
				while(line != null) {
					sb.append(line);
					line = reader.readLine();
				}
				
				String body = new String(sb);
				
				User u = om.readValue(body, User.class);
				
				if (ls.login(u)) {
					HttpSession ses = req.getSession();
					ses.setAttribute("user", u);
					ses.setAttribute("loggedin", true);
					res.setStatus(200);
					res.getWriter().println("Login Successful");
				} else {
					HttpSession ses = req.getSession(false);
					if (ses != null) {
						ses.invalidate();
					}
					res.setStatus(401); // Status Code 401 = Unauthorized.
					res.getWriter().println("Login failed");
				}
			}
		}

	public void logout(HttpServletRequest req, HttpServletResponse res) throws IOException {
		HttpSession ses = req.getSession(false);

		if (ses != null) {
			User u = (User) ses.getAttribute("user");
			ses.invalidate();
			res.setStatus(200); // "Ok", which is a type of Success!
			res.getWriter().println(u + " has logged out successfully.");
		} else {
			res.setStatus(400); // "Bad Request", which is a type of Client-Side Error.
			res.getWriter().println("Status 400: You have made a bad request. You must be logged in to logout.");
		}
	}

}
