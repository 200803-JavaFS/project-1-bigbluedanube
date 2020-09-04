package com.revature.daos;

import com.revature.models.ReimbursementType;

public interface IReimbursementTypeDAO {
	
	ReimbursementType findById(int id);

	boolean findAllLodging(String reimbType);

	boolean findAllTravel(String reimbType);

	boolean findAllFood(String reimbType);
	
	boolean findAllOther(String reimbType);

}
