package com.revature.daos;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.revature.models.UserRoles;
import com.revature.utils.HibernateUtil;

public class UserRolesDAO implements IUserRolesDAO {
	
	@Override
	public List<UserRoles> findAll() {
		Session ses = HibernateUtil.getSession();
		
		List<UserRoles> list = ses.createQuery("FROM UserRoles").list();
		return list;
	}
	
	@Override
	public UserRoles findById(int id) {	
		Session ses = HibernateUtil.getSession();
		
		return ses.get(UserRoles.class, id);
	}
	
	@Override
	public boolean addUserRoles(UserRoles urs) {
		Session ses = HibernateUtil.getSession();
		try {
			ses.save(urs);		// This is a Hibernate Session (Insert) Method. A new session begins and the save() method returns a serializable object that represents what was saved.
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean updateUserRoles(UserRoles urs) {
		Session ses = HibernateUtil.getSession();
		
		try {
			ses.merge(urs);		// This is a Hibernate Session (Update) Method. it merges with the object in the cache (if it exists), then updates the DB. Safer than update().
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean deleteUserRoles(int userRoleId) {
		Session ses = HibernateUtil.getSession();
		
		try {
			ses.createQuery("DELETE FROM UserRoles WHERE userRoleId = " + userRoleId);		// userId is the FIELD, not the Column name, and we're referencing CLASSES, and not Table names.
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}
}
