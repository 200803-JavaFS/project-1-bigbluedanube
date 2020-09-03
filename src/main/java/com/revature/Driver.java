package com.revature;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.revature.daos.*;

import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

import com.revature.models.LoginDTO;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;
import com.revature.models.User;
import com.revature.models.UserRoles;
import com.revature.services.LoginService;


public class Driver {
	
	// Both Tim's Movie/Director/Character demo and Jane Huang's code proved incredibly helpful here. Thank you both.
	// For now, I have to run my entire SQL script to avoid bugs here. I don't know if that's kosher.
	
	public static IUserDAO uDao = new UserDAO();
	public static IReimbursementDAO rDao = new ReimbursementDAO();
	public static IUserRolesDAO ursDao = new UserRolesDAO();
	public static LoginService ls = new LoginService();
	
	public static Scanner myScanner = new Scanner(System.in);
	
	
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
		
		LoginDTO lDTO = new LoginDTO();
		
		String newName = myScanner.nextLine();
		String newPW = myScanner.nextLine();
		
		lDTO.setUsername(newName);
		lDTO.setPassword(newPW);
		
		ls.login(lDTO);
		

		
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
		
//		Reimbursement reimb1 = new Reimbursement(10000.00, Timestamp reimbSubmitted, Timestamp reimbResolved, "LODGING", ByteArrayBuilder reimbReceipt, user3, user1, ReimbursementStatus reimbStatusFk, ReimbursementType reimbTypeFk);
//		Reimbursement reimb2 = new Reimbursement(10.00, Timestamp reimbSubmitted, Timestamp reimbResolved, "TRAVEL", ByteArrayBuilder reimbReceipt, user4, user2, ReimbursementStatus reimbStatusFk, ReimbursementType reimbTypeFk);
//		Reimbursement reimb3 = new Reimbursement(50000.00, Timestamp reimbSubmitted, Timestamp reimbResolved, "FOOD", ByteArrayBuilder reimbReceipt, user5, user1, ReimbursementStatus reimbStatusFk, ReimbursementType reimbTypeFk);
//		Reimbursement reimb4 = new Reimbursement(90000.00, Timestamp reimbSubmitted, Timestamp reimbResolved, "OTHER", ByteArrayBuilder reimbReceipt, user3, user2, ReimbursementStatus reimbStatusFk, ReimbursementType reimbTypeFk);
//		Reimbursement reimb5 = new Reimbursement(5.00, Timestamp reimbSubmitted, Timestamp reimbResolved, "LODGING", ByteArrayBuilder reimbReceipt, user4, user1, ReimbursementStatus reimbStatusFk, ReimbursementType reimbTypeFk);
//		rDao.addReimbursement(reimb1);
//		rDao.addReimbursement(reimb2);
//		rDao.addReimbursement(reimb3);
//		rDao.addReimbursement(reimb4);
//		rDao.addReimbursement(reimb5);

// This is Dummy Data that tests persistence to the DB.

	}

}
