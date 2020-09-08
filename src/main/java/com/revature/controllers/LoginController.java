package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
	private static final Logger log = LogManager.getLogger(LoginController.class);
	private User u;

	public boolean login(HttpServletRequest req, HttpServletResponse res) throws IOException {

		if (req.getMethod().equals("GET")) {
			log.warn("This is a very bad thing. Stop it.");
			return false;
			
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
				
				lDTO  = om.readValue(body, LoginDTO.class);
				u = ls.login(lDTO);
				System.out.println("lDTO = " + lDTO);
				System.out.println("u = " + u);
				
				if (u != null) {
					HttpSession ses = req.getSession();
					ses.setAttribute("User", u);
					ses.setAttribute("loggedin", true);
					res.setStatus(200);
					res.getWriter().println(u.getUserId());
					return true;
				} else {
					HttpSession ses = req.getSession(false);
					if (ses != null) {
						ses.invalidate();
						return false;
					}
					res.setStatus(401); // Status Code 401 = Unauthorized.
					res.getWriter().println("Login failed");
					return false;
				}
			} else {
				log.warn("What happened?! WHAT DID YOU DO?!?!?!?!");
				return false;
			}
		}
	
	
	public void getUser(HttpServletRequest req, HttpServletResponse res) throws IOException {
		if(req.getMethod().equals("GET")) {
			HttpSession ses = req.getSession();
			res.setStatus(200);
			u = (User) ses.getAttribute("User");
			String JSON = om.writeValueAsString(u);
			res.getWriter().println(JSON);
		} else {
			log.warn("WHAT HAVE YOU DONE, JACKSON?!?! WHAT HAVE YOU DONE?!?!?!");
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
