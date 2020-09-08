package com.revature.daos;

import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;
import com.revature.models.User;

public interface IReimbursementDAO {
	
	public List<Reimbursement> findAll();
	
	public Reimbursement findById(int id);
	
	public boolean addReimbursement(Reimbursement r);

	boolean updateReimbursement(Reimbursement r);

	List<Reimbursement> findByStatus(ReimbursementStatus reimbStatus);

	ReimbursementStatus getStatusById(int id);

	ReimbursementType getTypeById(int id);

	List<Reimbursement> findByAuthor(User reimbAuthor);

}
