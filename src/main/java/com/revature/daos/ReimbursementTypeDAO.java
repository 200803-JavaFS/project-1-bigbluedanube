package com.revature.daos;

import org.hibernate.Session;

import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;
import com.revature.utils.HibernateUtil;

public class ReimbursementTypeDAO implements IReimbursementTypeDAO {
	
	@Override
	public ReimbursementType findById(int id) {
		Session ses = HibernateUtil.getSession();
		
		ReimbursementType typeId = ses.get(ReimbursementType.class, id);
		return typeId;
	}

	@Override
	public boolean findAllLodging(String reimbType) {
		Session ses = HibernateUtil.getSession();
		
		if(ses.get(ReimbursementType.class, reimbType).toString().equals("LODGING")) {
			return true;
		} else {
			System.out.println("No reimbursements for LODGING at this time.");
			return false;
		}
	}

	@Override
	public boolean findAllTravel(String reimbType) {
		Session ses = HibernateUtil.getSession();
		
		if(ses.get(ReimbursementType.class, reimbType).toString().equals("TRAVEL")) {
			return true;
		} else {
			System.out.println("No reimbursements for TRAVEL at this time.");
			return false;
		}
	}

	@Override
	public boolean findAllFood(String reimbType) {
		Session ses = HibernateUtil.getSession();
		
		if(ses.get(ReimbursementType.class, reimbType).toString().equals("FOOD")) {
			return true;
		} else {
			System.out.println("No reimbursements for FOOD at this time.");
			return false;
		}

	}
	
	@Override
	public boolean findAllOther(String reimbType) {
		Session ses = HibernateUtil.getSession();
		
		if(ses.get(ReimbursementType.class, reimbType).toString().equals("OTHER")) {
			return true;
		} else {
			System.out.println("No reimbursements for OTHER at this time.");
			return false;
		}

	}


}
