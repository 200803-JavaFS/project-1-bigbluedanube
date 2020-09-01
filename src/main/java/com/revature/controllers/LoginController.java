package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.LoginDTO;
import com.revature.services.LoginService;

// req.getParameterMap().containsKey("username") returns a Boolean.
// req.getParameter("username") returns a String/an actual Object.

public class LoginController {

	private static LoginService ls = new LoginService();
	private static ObjectMapper om = new ObjectMapper();

	public void login(HttpServletRequest req, HttpServletResponse res) throws IOException {
		// This shows logging in with query parameters for example purposes only. Do not
		// ever actually do this.

		if (req.getMethod().equals("GET")) {

			if (req.getParameterMap().containsKey("username") && req.getParameterMap().containsKey("password")) {
				LoginDTO l = new LoginDTO();
				l.username = req.getParameter("username"); // this is the "bad part" req.getParameter();
				l.password = req.getParameter("password"); // This one, too.

				if (ls.login(l)) {
					HttpSession ses = req.getSession();
					ses.setAttribute("user", l);
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
				
				LoginDTO l = om.readValue(body, LoginDTO.class);
				
				if (ls.login(l)) {
					HttpSession ses = req.getSession();
					ses.setAttribute("user", l);
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
			LoginDTO l = (LoginDTO) ses.getAttribute("user"); // We cast this to an DTO. Can you explain why we did
																// that? Because Attributes belong to Objects, and we
																// have to specify the Object to which this Attribute
																// belongs.
			ses.invalidate();
			res.setStatus(200); // "Ok", which is a type of Success!
			res.getWriter().println("l.username" + " has logged out successfully.");
		} else {
			res.setStatus(400); // "Bad Request", which is a type of Client-Side Error.
			res.getWriter().println("You muse be logged in to logout.");
		}
	}

}
