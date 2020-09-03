package com.revature.services;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.daos.UserDAO;
import com.revature.models.LoginDTO;
import com.revature.models.User;

// THIS SHOULD HANDLE BOTH LOGIN AND LOGOUT.

public class LoginService {
	
	private static final Logger log = LogManager.getLogger(LoginService.class);
	private static UserDAO uDao = new UserDAO();
		
	// Alex did this for debugging purposes. Thank you, Alex!

	public boolean login(LoginDTO lDTO) {
		User u = uDao.findByUsername(lDTO.username);					// "Go and find the User who has this username."
		System.out.println("The User pw is... " + u.getPassword());		// print off their password
		System.out.println("The LoginDTO pw = " + lDTO.password);		// print off the LoginDTO password.
		
		System.out.println(u);		
		
		if(u != null) {
			if(u.getPassword().equals(lDTO.password)) {					// Do they match?
				System.out.println("The Login was successful.");		// Yes! Success!
				return true;
			} else {
				System.out.println("The password was incorrect.");		// Nope! No login for YOU!
				return false;
			}
		} else {
			System.out.println("We have no users under that name, Your Grace.");
			return false;
		}
	}
	
}
	
	
	
	
	
	
	
	
	
	// we can also include the logout service here and call it "LoginAndOutService" (that's not a joke).
	// Calling it "LoggingService" will only confuse me because there are Logger classes being imported everywhere.
	// log.info("Logging out.");
