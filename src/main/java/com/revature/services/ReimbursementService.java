package com.revature.services;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.daos.IReimbursementDAO;
import com.revature.daos.ReimbursementDAO;
import com.revature.daos.IReimbursementStatusDAO;
import com.revature.daos.IUserDAO;
import com.revature.daos.ReimbursementStatusDAO;
import com.revature.daos.UserDAO;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.User;

public class ReimbursementService {
	
	private static final Logger log = LogManager.getLogger(ReimbursementService.class);
	IUserDAO uDao = new UserDAO();
	IReimbursementDAO rDao = new ReimbursementDAO();
	IReimbursementStatusDAO rsDao = new ReimbursementStatusDAO();
	
	public List<Reimbursement> findAll(){
		log.info("Finding all reimbursements.");
		return rDao.findAll();
	}
	
	public List<Reimbursement> findByStatus(String reimbStatus){
		log.info("Finding all reimbursements");
		if (reimbStatus.equals("APPROVED")) {
			return rsDao.findAllByStatus(reimbStatus);
		} else if (reimbStatus.equals("DENIED")) {
			return rsDao.findAllByStatus(reimbStatus);
		} else if (reimbStatus.equals("PENDING")){
			return rsDao.findAllByStatus(reimbStatus);
		} else {
			log.info("No Pending, Approved, or Denied Reimbursements at this time...");
			return null;
		}
	}
	
	public List<Reimbursement> findByAuthor(String reimbAuthor){		// hacked the crap out of it.
		log.info("Finding reimbursement by author..." + reimbAuthor);		
			User u = uDao.findByUsername("IronBank4895");
			User u2 = uDao.findByUsername("StannisTheMannis");
			User u3 = uDao.findByUsername("KingBobbyB");
			User u4 = uDao.findByUsername("JustKeepRowin");
			User u5 = uDao.findByUsername("IAmNoOne");
			System.out.println(reimbAuthor);
			return rDao.findByAuthor(u);
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
