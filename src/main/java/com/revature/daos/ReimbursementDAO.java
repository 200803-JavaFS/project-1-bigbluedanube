package com.revature.daos;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;
import com.revature.models.User;
import com.revature.utils.HibernateUtil;

public class ReimbursementDAO implements IReimbursementDAO {
		
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
		Transaction tx = ses.beginTransaction();
		try {
			ses.saveOrUpdate(r);
			tx.commit();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean updateReimbursement(Reimbursement r) {
		Session ses = HibernateUtil.getSession();
		
		try {
			ses.merge(r);		// This is a Hibernate Session (Update) Method. It merges with the object in the cache (if it exists), then updates the DB. Safer than update().
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public List<Reimbursement> findByStatus(ReimbursementStatus reimbStatus) {
		Session ses = HibernateUtil.getSession();
		
		List<Reimbursement> list = ses.createQuery("FROM ReimbursementStatus WHERE reimbStatusFk = " + reimbStatus.getReimbStatusId(), Reimbursement.class).list();
		return list;
	}
	
	@Override
	public ReimbursementStatus getStatusById(int id) {
        Session ses = HibernateUtil.getSession();
        ReimbursementStatus s = ses.get(ReimbursementStatus.class, id);
            return s;
    }
	
	@Override
	public ReimbursementType getTypeById(int id) {
        Session ses = HibernateUtil.getSession();
        ReimbursementType s = ses.get(ReimbursementType.class, id);
            return s;
    }
	
	@Override
	public List<Reimbursement> findByAuthor(User reimbAuthor){
        Session ses = HibernateUtil.getSession();
        
		List<Reimbursement> list = ses.createQuery("FROM Reimbursement WHERE reimbAuthor = " + reimbAuthor, Reimbursement.class).list();
		return list;
	}
}
