package com.revature;
import com.revature.daos.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.User;
import com.revature.models.UserRoles;


public class Driver {
	
	// Both Tim's Movie/Director/Character demo and Jane Huang's code proved incredibly helpful here. Thank you both.
	
	public static IUserDAO uDao = new UserDAO();
	public static IReimbursementDAO rDao = new ReimbursementDAO();
	
	private static final Logger log = LogManager.getLogger(Driver.class);
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		InsertValues();
		
		List<User> users = uDao.findAll();
		
		for(User u : users) {
			System.out.println(u);
		}
	}

	private static void InsertValues() throws NoSuchAlgorithmException {	// Jane Huang's influence comes in here.
		UserRoles role0 = new UserRoles("Employee");			
		UserRoles role1 = new UserRoles("Financial Manager");
		
		User user1 = new User("IronBank4895", "wewillgetourdue", "Tycho", "Nestoris", "tycho.nestoris@ironbank.co.braavos", role0);
		User user2 = new User("StannisTheMannis", "fewerNeverMind", "Stannis", "Baratheon", "stannisthemannis@dragonstone.gov", role0);
		User user3 = new User("KingBobbyB", "killedbyaboar", "Robert", "Baratheon", "kingbobbyb@westeros.gov", role1);	// Members of the Royal Family get a Westeros.gov email.
		User user4 = new User("JustKeepRowin", "blacksmith", "Gendry", "Hill", "justkeeprowin@stormsend.gov", role1);	// and each holdfast has a government website.
		User user5 = new User("IAmNoOne", "facelessman", "Arya", "Stark", "iamnoone@blackandwhite.org", role1);			// I have decided that since The Faceless Men are technically a religious organization, they qualify for tax-exempt status.
		uDao.addUser(user1);
		uDao.addUser(user2);
		uDao.addUser(user3);	
		uDao.addUser(user4);
		uDao.addUser(user5);

	}

}
