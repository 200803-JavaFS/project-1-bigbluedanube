package com.revature.daos;

import org.hibernate.Session;

import com.revature.models.ReimbursementStatus;
import com.revature.utils.HibernateUtil;

public class ReimbursementStatusDAO implements IReimbursementStatusDAO {	
	
	@Override
	public ReimbursementStatus findById(int id) {
		Session ses = HibernateUtil.getSession();
		
		ReimbursementStatus statusId = ses.get(ReimbursementStatus.class, id);
		return statusId;
	}
	
	@Override
	public boolean findAllPending(String reimbStatus) {
		Session ses = HibernateUtil.getSession();
		
		if(ses.get(ReimbursementStatus.class, reimbStatus).toString().equals("PENDING")) {
			return true;
		} else {
			System.out.println("No pending reimbursements at this time.");
			return false;
		}

	}
	
	@Override
	public boolean findAllApproved(String reimbStatus) {
		Session ses = HibernateUtil.getSession();
		
		if(ses.get(ReimbursementStatus.class, reimbStatus).toString().equals("APPROVED")) {
			return true;
		} else {
			System.out.println("No approved reimbursements at this time.");
			return false;
		}

	}
	
	@Override
	public boolean findAllDenied(String reimbStatus) {
		Session ses = HibernateUtil.getSession();
		
		if(ses.get(ReimbursementStatus.class, reimbStatus).toString().equals("DENIED")) {
			return true;
		} else {
			System.out.println("No denied reimbursements at this time.");
			return false;
		}

	}


}
