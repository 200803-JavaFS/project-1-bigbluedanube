package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.daos.ReimbursementStatusDAO;
import com.revature.models.Reimbursement;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;

public class ReimbursementController {
	
	private static UserService us = new UserService();
	private static ReimbursementStatusDAO rsDao = new ReimbursementStatusDAO();
	private static ReimbursementService rs = new ReimbursementService();
	private static ObjectMapper om = new ObjectMapper();
	
	public void getReimbursement(HttpServletResponse res, int id) throws IOException {
		
		Reimbursement r = rs.findById(id);
		if(r == null) {
			res.setStatus(204);
		} else {
			res.setStatus(200);
			String json = om.writeValueAsString(r);
			res.getWriter().println(json);
		}
	}
	
	public void getAllReimbursements(HttpServletResponse res) throws IOException {
		List<Reimbursement> all = rs.findAll();
		res.setStatus(200);
		res.getWriter().println(om.writeValueAsString(all));
		// Converts the list of Reimbursements to a JSON String, and then puts that into the body.
	}
	
	public void addReimbursement(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		BufferedReader reader = req.getReader();
		
		StringBuilder s = new StringBuilder();
		
		String line = reader.readLine();	// similar to next() in JDBC, 'reads' the next line in  the JSON string.
		
		while(line != null) {
			s.append(line);
			line = reader.readLine();		// this loop increments through the JSON string line by line.
			
		}
		
		String body = new String(s);
		System.out.println(body);
		
		Reimbursement r = om.readValue(body, Reimbursement.class);
		
		System.out.println(r);
		
		if (rs.addReimbursement(r)) {
			
			res.setStatus(201);				// 201 means 'created'
			res.getWriter().println("Reimbursement was created.");
		} else {
			res.setStatus(403);				// 403 means 'forbidden'
			res.getWriter().println("Status Code 403: Such a transaction is forbidden by the Iron Bank. Try again.");
		}

	}
	
	public void updateReimbursementStatus(HttpServletRequest req, HttpServletResponse res) throws IOException {
		BufferedReader reader = req.getReader();
		
		StringBuilder s = new StringBuilder();
		
		String line = reader.readLine();	// similar to next() in JDBC, 'reads' the next line in  the JSON string.
		
		while(line != null) {
			s.append(line);
			line = reader.readLine();		// this loop increments through the JSON string line by line.
			
		}
		
		String body = new String(s);
		System.out.println(body);

		Reimbursement r = om.readValue(body, Reimbursement.class);		// this might need to be a ReimbursementDTO
		
		

		
	}

}
