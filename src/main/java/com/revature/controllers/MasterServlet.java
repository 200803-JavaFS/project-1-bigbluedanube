package com.revature.controllers;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MasterServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static UserController uc = new UserController();

	public MasterServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json");
		
		//By default, Tomcat will send back a successful status code if it finds a servlet method.
		// Because all requests will hit this method, we are defaulting to Not Found and will override for *successful* requests.
		// so if anything goes wrong, you'll end up at the 404 message, if it goes right, we'll override this method again to show the success.
		res.setStatus(404);
		
		final String URI = req.getRequestURI().replace("/project1/", "");	// turns the end of the URI to blank, basically cuts it off.
		
		String[] portions = URI.split("/");	// creates an array out of each section following the base URI, each section between slashes.
		System.out.println(Arrays.toString(portions));
		
		try {
		switch (portions[0]) {
		case "user":
			if(req.getMethod().equals("GET")) {				// GET means you are getting something FROM the DB. It's like [midiparse] in Max/MSP
				if(portions.length == 2) {
					int id = Integer.parseInt(portions[1]);	// this is dangerous because the input might not be an int. So we nest the whole thing in a Try-Catch Block.
					uc.getUser(res, id);
				}else if (portions.length == 1){
				uc.getAllUsers(res);	
				}
			} else if (req.getMethod().equals("POST")){		// POST means YOU are POSTING/adding something TO the DB.
				uc.addUser(req, res);
			}
		
		}
		
	} catch(NumberFormatException e) {
		e.printStackTrace();
		res.getWriter().print("The id you provided is not an integer.");
		res.setStatus(404);
	}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
		

		
	}

}
