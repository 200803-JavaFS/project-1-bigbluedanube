package com.revature.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.daos.UserDAO;
import com.revature.models.LoginDTO;
import com.revature.models.User;

// THIS SHOULD HANDLE BOTH LOGIN AND LOGOUT.

public class LoginService {
	
	private static final Logger log = LogManager.getLogger(LoginService.class);
	private static UserService us = new UserService();
	private static UserDAO uDao = new UserDAO();

	public boolean login(User u) {
		StringBuilder sb = new StringBuilder();
		int hashedPW = u.getPassword().hashCode();
		sb.append(hashedPW);
		
		String hashedSBPW = sb.toString();
		
		u = us.findByNameAndPW(u.getUsername(), hashedSBPW);
		log.info("The user's encrypted password is: " + hashedSBPW);
		log.info("The user's unencrypted password is: " + u.getPassword());
		
		if(u.getPassword().equals(hashedSBPW)) {
			log.info("IT'S A MATCH!");
			return true;
			
		} else {
			log.info("These passwords do not match.");
			return false;
		}
		
	}
	
}




/*
 * 		// Alex did this for debugging purposes. Thank you, Alex!

 * 		User u = uDao.findByUsername(u.getUsername());					// "Go and find the User who has this username."
		System.out.println("The User pw is... " + u.getPassword());		// print off their password
		System.out.println("The LoginDTO pw = " + u.getPassword());		// print off the LoginDTO password.
		
		System.out.println(u);	
		
					if(u != null) {
			if(u.getPassword().equals(u.getPassword())) {			// Do they match?
				log.info("The Login was successful.");				// Yes! Success!
				return true;
			} else {
				log.info("Incorrect password. Login failed.");		// Nope! No login for YOU!
				return false;
			}
		} else {
			System.out.println("This person does not exist.");
			log.info("Incorrect Entry or Unknown User.");
			return false;
		}
 */
	
	
	
	
	
	
	
	
	
	// we can also include the logout service here and call it "LoginAndOutService" (that's not a joke).
	// Calling it "LoggingService" will only confuse me because there are Logger classes being imported everywhere.
	// log.info("Logging out.");
