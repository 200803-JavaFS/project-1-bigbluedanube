package com.revature.daos;

import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;

public interface IReimbursementStatusDAO {

	ReimbursementStatus findById(int id);

	
	public List<Reimbursement> findAllByStatus(String reimbStatus);

//	public List<Reimbursement> findAllApproved(String reimbStatus);
//	
//	boolean findAllDenied(String reimbStatus);
	
//	boolean findAllPending(String reimbStatus);


}
