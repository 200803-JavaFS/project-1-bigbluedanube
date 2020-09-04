package com.revature.daos;

import java.sql.Timestamp;
import java.util.List;

import com.revature.models.Reimbursement;

public interface IReimbursementDAO {
	
	public List<Reimbursement> findAll();
	
	public Reimbursement findById(int id);
	
	public boolean addReimbursement(Reimbursement r);

	Timestamp submitReimbursement(Timestamp reimbSubmitted);

	Timestamp resolveReimbursement(Timestamp reimbResolved);

}
