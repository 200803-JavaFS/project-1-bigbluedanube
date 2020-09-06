package com.revature.daos;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.revature.models.User;
import com.revature.utils.HibernateUtil;

public class UserDAO implements IUserDAO {

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
	public User findByUsername(String username) {
		Session ses = HibernateUtil.getSession();
		
		User u = (User) ses.createQuery("FROM User WHERE username = " + username);
		return u;
	}

	
	@Override
	public boolean findByBoth(String username, String password) {		// This one's new!!! Thank you, Alex and Lev!!!
		Session ses = HibernateUtil.getSession();
		
		try {
			
		ses.createQuery("FROM User WHERE username = " + username + " AND password = " + password, User.class);
		return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
//	public User findByPassword(String password) {		// based on the above, needed it to complete a UserService method...
//		Session ses = HibernateUtil.getSession();		// ...findByPassword() which was needed for LoginService method...
////		return ses.get(User.class, password);			// ... loginUser(u), which was needed to test LoginService in the JUnit tests.
//		User u2 = (User) ses.createQuery("FROM User U WHERE");
//		return u2;
//	}

	@Override
	public boolean addUser(User u) {
		Session ses = HibernateUtil.getSession();
		try {
			ses.save(u);		// This is a Hibernate Session (Insert) Method. A new session begins and the save() method returns a serializable object that represents what was saved.
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
			ses.merge(u);		// This is a Hibernate Session (Update) Method. it merges with the object in the cache (if it exists), then updates the DB. Safer than update().
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
