package com.revature.controllers;

import java.io.BufferedReader;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.revature.models.User;
import com.revature.services.UserService;


//EITHER PUT ALL USER FUNCTIONALITY HERE OR CREATE A SEPARATE CONTROLLER FOR THE OTHER USER TYPE.


public class UserController {
	
	private static UserService us = new UserService();
	private static ObjectMapper om = new ObjectMapper();
	
	public void getUser(HttpServletResponse res, int id) throws IOException {
		
		User u = us.findById(id);
		if(u == null) {
			res.setStatus(204);
		} else {
			res.setStatus(200);
			String json = om.writeValueAsString(u);
			res.getWriter().println(json);
		}
	}
	
	public void getAllUsers(HttpServletResponse res) throws IOException {
		List<User> all = us.findAll();
		res.getWriter().println(om.writeValueAsString(all));
		//converts the list of Users to a JSON String, and then putting that into the body.
		res.setStatus(200);
	}
	
	
	public void addUser(HttpServletRequest req, HttpServletResponse res) throws IOException {
	
		BufferedReader reader = req.getReader();
		
		StringBuilder s = new StringBuilder();
		
		String line = reader.readLine();	// similar to next() in JDBC, 'reads' the next line in  the JSON string.
		
		while(line != null) {
			s.append(line);
			line = reader.readLine();		// this loop increments through the JSON string line by line.
			
		}
		
		String body = new String(s);
		System.out.println(body);
		
		User u = om.readValue(body, User.class);
		
		System.out.println(u);
		
		if (us.addUser(u)) {
			
			res.setStatus(201);				// 201 means 'created'
			res.getWriter().println("User was created.");
		} else {
			res.setStatus(403);				// 403 means 'forbidden'
		}

	}

               
	

}
