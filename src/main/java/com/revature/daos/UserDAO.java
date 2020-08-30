package com.revature.daos;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.daos.IReimbursementDAO;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.utils.HibernateUtil;

public class UserDAO implements IUserDAO {

	private IReimbursementDAO rDao = new ReimbursementDAO();

	@Override
	public List<User> findAll() {
		Session ses = HibernateUtil.getSession();
		
		List<User> list = ses.createQuery("FROM User").list();
		return list;
	}
		
	@Override
	public User findById(int id) {
		Session ses = HibernateUtil.getSession();
		
		User u = ses.get(User.class, id);
		return u;
	}

	@Override
	public boolean addUser(User u) {
		Session ses = HibernateUtil.getSession();
		try {
			ses.save(u);
			return true;	
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateUser(User u) {
		Session ses = HibernateUtil.getSession();
		
		try {
			ses.merge(u);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteUser(int userId) {
		Session ses = HibernateUtil.getSession();
		
		try {
			ses.createQuery("DELETE FROM User WHERE userId = " + userId);		// userId is the FIELD, not the Column name, and we're referencing CLASSES, and not Table names.
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}	
	}

}
