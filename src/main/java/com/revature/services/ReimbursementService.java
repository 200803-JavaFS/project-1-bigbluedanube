package com.revature.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.daos.IReimbursementDAO;
import com.revature.daos.IUserDAO;
import com.revature.daos.ReimbursementDAO;
import com.revature.daos.UserDAO;
import com.revature.models.Reimbursement;

public class ReimbursementService {
	
	private static final Logger log = LogManager.getLogger(ReimbursementService.class);
	
	IReimbursementDAO rDao = new ReimbursementDAO();		// it may be helpful to have a few rows of Dummy Data for this part.
	
	public List<Reimbursement> findAll(){
		log.info("Finding all reimbursements.");
		return rDao.findAll();
	}
	
	public Reimbursement findById(int id) {
		log.info("Retrieving reimbursement by ID...");
		return rDao.findById(id);
	}
	
	public boolean addReimbursement(Reimbursement r) {
		log.info("Adding new reimbursement...");
		return rDao.addReimbursement(r);
	}

}
