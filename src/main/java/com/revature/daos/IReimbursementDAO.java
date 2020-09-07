package com.revature.daos;

import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;

public interface IReimbursementDAO {
	
	public List<Reimbursement> findAll();
	
	public Reimbursement findById(int id);
	
	public boolean addReimbursement(Reimbursement r);

	boolean updateReimbursement(Reimbursement r);

	List<Reimbursement> findByStatus(ReimbursementStatus reimbStatus);

}
