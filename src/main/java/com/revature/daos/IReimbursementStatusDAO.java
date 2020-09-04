package com.revature.daos;

import com.revature.models.ReimbursementStatus;

public interface IReimbursementStatusDAO {

	ReimbursementStatus findById(int id);

	boolean findAllPending(String reimbStatus);

	boolean findAllApproved(String reimbStatus);

	boolean findAllDenied(String reimbStatus);

}
