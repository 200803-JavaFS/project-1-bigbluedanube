package com.revature.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.daos.UserDAO;
import com.revature.daos.IUserDAO;
import com.revature.models.User;

public class UserService {
	
	private static final Logger log = LogManager.getLogger(UserService.class);

		IUserDAO uDao = new UserDAO();
		
		public List<User> findAll() {
			log.info("Retrieving all users.");
			return uDao.findAll();
		}
		
		public User findById(int id) {
			log.info("Finding user by ID...");
			return uDao.findById(id);
		}
		
		public User findByPassword(String username, String password) {				// LizaMarie has a selectByCredentials() method.
			log.info("Finding user " + username + " and password " + password);		// Mine are separated by username and passsord.
			return uDao.findByPassword(password);
		}
		
		public boolean addUser(User u) {
			log.info("Adding new user...");
			return uDao.addUser(u);
		}
		
		public boolean updateUser(User u) {
			log.info("Updating user...");
			return uDao.updateUser(u);
		}
		
		public boolean deleteUser(int userId) {
			log.info("Deleting user...");
			return uDao.deleteUser(userId);
		}
	}
