package com.revature.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.LoginDTO;

public class LoginService {
	
	private static final Logger log = LogManager.getLogger(LoginService.class);

	
	public boolean login(LoginDTO l) {
		//This is having us set the query parameters, username and password in the URI, which is bad because everyone can see them.
		if(l.username.equals("agent") && l.password.equals("11235811")) {	// these passwords will have to change. And be encrypted using a hash() method.
			log.info("Attempting login.");
			return true;
		}
		return false;
	}
	// we can also include the logout service here and call it "LoginAndOutService" (that's not a joke).
	// Calling it "LoggingService" will only confuse me because there are Logger classes being imported everywhere.
	// log.info("Logging out.");

}
