package com.revature;
import com.revature.daos.*;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.User;


public class Driver {
	
	private static final Logger log = LogManager.getLogger(Driver.class);
	
	public static void main(String[] args) {
		InsertValues();
		
//		List<User> users = uDao.findAll();	// this happens in the Service Layer. We don't need it here. Check UserService.
//		
//		for (User u : users) {
//			System.out.println(u);
//		}
	}

	private static void InsertValues() {
		User user1 = new User();
		User user2 = new User();
		User user3 = new User();
		uDAO.insert(user1);
		uDAO.insert(user2);
		uDAO.insert(user3);		
	}
	
	
	
	

}
