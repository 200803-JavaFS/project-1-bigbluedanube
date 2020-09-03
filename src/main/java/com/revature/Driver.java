package com.revature;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.revature.daos.*;

import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;
import com.revature.models.User;
import com.revature.models.UserRoles;


public class Driver {
	
	// Both Tim's Movie/Director/Character demo and Jane Huang's code proved incredibly helpful here. Thank you both.
	
	public static IUserDAO uDao = new UserDAO();
	public static IReimbursementDAO rDao = new ReimbursementDAO();
	public static IUserRolesDAO ursDao = new UserRolesDAO();
	
	private static final Logger log = LogManager.getLogger(Driver.class);
	
	
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		InsertValues();
		
		List<User> users = uDao.findAll();
		
		for(User u : users) {
			System.out.println(u);
		}
		
		
		List<Reimbursement> reimbs = rDao.findAll();
		
		for(Reimbursement r : reimbs) {
			System.out.println(r);
		}
		
		
	}

	private static void InsertValues() throws NoSuchAlgorithmException {	// Jane Huang's influence comes in here.
		UserRoles role1 = ursDao.findById(1);			
		UserRoles role2 = ursDao.findById(2);
		
		User user1 = new User("IronBank4895", "wewillgetourdue", "Tycho", "Nestoris", "tycho.nestoris@ironbank.co.braavos", role1);
		User user2 = new User("StannisTheMannis", "fewerNeverMind", "Stannis", "Baratheon", "stannisthemannis@dragonstone.gov", role1);
		User user3 = new User("KingBobbyB", "killedbyaboar", "Robert", "Baratheon", "kingbobbyb@westeros.gov", role2);	// Members of the Royal Family get a Westeros.gov email.
		User user4 = new User("JustKeepRowin", "blacksmith", "Gendry", "Hill", "justkeeprowin@stormsend.gov", role2);	// and each holdfast has a government website.
		User user5 = new User("IAmNoOne", "facelessman", "Arya", "Stark", "iamnoone@blackandwhite.org", role1);			// I have decided that since The Faceless Men are technically a religious organization, they qualify for tax-exempt status.
		uDao.addUser(user1);
		uDao.addUser(user2);
		uDao.addUser(user3);
		uDao.addUser(user4);
		uDao.addUser(user5);
		
		Reimbursement reimb1 = new Reimbursement(1000.00, Timestamp reimbSubmitted, Timestamp reimbResolved, "FOOD", ByteArrayBuilder reimbReceipt, user3, user1, ReimbursementStatus reimbStatusFk, ReimbursementType reimbTypeFk);
		Reimbursement reimb1 = new Reimbursement();
		Reimbursement reimb1 = new Reimbursement();
		Reimbursement reimb1 = new Reimbursement();
		Reimbursement reimb1 = new Reimbursement();
		
		// This is Dummy Data that tests persistence to the DB.

	}

}
