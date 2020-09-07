package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.daos.ReimbursementStatusDAO;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementDTO;
import com.revature.models.ReimbursementStatus;
import com.revature.models.User;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;

public class ReimbursementController {
	
	private static UserService us = new UserService();
	private static ReimbursementStatusDAO rsDao = new ReimbursementStatusDAO();
	private static ReimbursementService rs = new ReimbursementService();
	private static ObjectMapper om = new ObjectMapper();
	private static final Logger log = LogManager.getLogger(LoginController.class);
	
	public void getReimbursement(HttpServletResponse res, int id) throws IOException {
		
		Reimbursement r = rs.findById(id);
		if(r == null) {
			res.setStatus(204);		// 204 means No Content: think of the doggo with the empty food bowl.
		} else {
			res.setStatus(200);		// 200 means OK, like the Good Boi in the 200 status code picture from httpstatusdogs.com
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
	
	public void getAllByStatus(HttpServletResponse res, String reimbStatus) throws IOException {
		List<Reimbursement> all = rs.findByStatus(reimbStatus);
		res.getWriter().println(om.writeValueAsString(all));
		res.setStatus(200);
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
	
	// Looked to Nancy's code for guidance here.
	
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

		ReimbursementDTO rDTO = om.readValue(body, ReimbursementDTO.class);		// this might need to be a ReimbursementDTO
		
		int rId = rDTO.getrId();
		
		Reimbursement r = rs.findById(rId);
		
		String status = rDTO.getrStatus();
		
		ReimbursementStatus reimbStatus = null;
		if(status.equals("APPROVED")) {
			reimbStatus = new ReimbursementStatus(2, "APPROVED");
		} else if (status.equals("DENIED")) {
			reimbStatus = new ReimbursementStatus(3, "DENIED");
		} else {
			log.info("Something is rotten in the ReimbursementController...");
		}
		
		int resolverId = rDTO.getrAuthorId();
		
		r.setReimbStatusFk(reimbStatus);
		User reimbResolver = us.findById(resolverId);						// Find the Resolver of this Reimbursement from among my Users.
		r.setReimbResolver(reimbResolver);
		r.setReimbResolved(new Timestamp(System.currentTimeMillis()));		// This sets the Time of Resolution to whenever this was called.
		
		if(rs.updateReimbursement(r)) {
			res.setStatus(202);			// 202 means "Accepted": think of the Cat "accepting" the pizza or the doggo "accepting" the cat as a nap companion.
			res.getWriter().println("Reimbursement Successfully Updated.");
		} else {
			res.setStatus(403);			// 403 means "forbidden", like the love between that adorable kitty and the big doggo who tap noses in cute defiance.
		}
	}

}
