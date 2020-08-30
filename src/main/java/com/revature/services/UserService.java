package com.revature.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.daos.UserDAO;
import com.revature.daos.IReimbursementDAO;
import com.revature.daos.IUserDAO;
import com.revature.daos.ReimbursementDAO;
import com.revature.models.User;

public class UserService {
	
	private static final Logger log = LogManager.getLogger(UserService.class);	// I believe this is where we retrieve users from the DB.


		IUserDAO uDao = new UserDAO();						// it may be helpful to have a few rows of Dummy Data for this part.
		IReimbursementDAO rDao = new ReimbursementDAO();
		
		public List<User> findAll() {
			log.info("Retrieving all users.");
			return uDao.findAll();
		}
		
		public User findById(int id) {
			log.info("Finding user by ID.");
			return uDao.findById(id);
		}
		
		public boolean addUser(User u) {
			log.info("Adding new user.");
			return uDao.addUser(u);
		}
	}
