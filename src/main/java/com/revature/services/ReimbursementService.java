package com.revature.services;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.daos.IReimbursementDAO;
import com.revature.daos.ReimbursementDAO;
import com.revature.models.Reimbursement;

public class ReimbursementService {
	
	private static final Logger log = LogManager.getLogger(ReimbursementService.class);
	
	IReimbursementDAO rDao = new ReimbursementDAO();
	
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
		Timestamp now = new Timestamp(new Date().getTime());
		log.info(now);
		r.setReimbSubmitted(now);
		return rDao.addReimbursement(r);
	}
	
	public boolean updateReimbursement(Reimbursement r) {
		log.info("Updating existing reimbursement...");
		Timestamp now = new Timestamp(new Date().getTime());
		log.info(now);
		r.setReimbResolved(now);
		return rDao.addReimbursement(r);
	}

}
