package com.revature.daos;

import java.sql.Timestamp;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.revature.models.Reimbursement;
import com.revature.services.LoginService;
import com.revature.utils.HibernateUtil;

public class ReimbursementDAO implements IReimbursementDAO {
	
	private static final Logger log = LogManager.getLogger(LoginService.class);
	
	@Override
	public List<Reimbursement> findAll() {
		Session ses = HibernateUtil.getSession();
		
		List<Reimbursement> list = ses.createQuery("FROM Reimbursement").list();		// there are other methods here besides "dot-list". When there's time, experiment with those.
		return list;
	}

	@Override
	public Reimbursement findById(int id) {
		Session ses = HibernateUtil.getSession();
		
		Reimbursement r = ses.get(Reimbursement.class, id);
		return r;
	}
	
	@Override
	public boolean addReimbursement(Reimbursement r) {
		Session ses = HibernateUtil.getSession();
		
		try {
			ses.saveOrUpdate(r);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}
}
