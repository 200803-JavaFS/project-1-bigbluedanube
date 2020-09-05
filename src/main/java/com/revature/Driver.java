package com.revature;
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
import com.revature.services.ReimbursementService;


public class Driver {
	
	// Both Tim's Movie/Director/Character demo and Jane Huang's code proved incredibly helpful here. Thank you both.
	// For now, I have to run my entire SQL script to avoid bugs here. I don't know if that's kosher.
	
	public static IUserDAO uDao = new UserDAO();
	public static IUserRolesDAO ursDao = new UserRolesDAO();
	public static IReimbursementDAO rDao = new ReimbursementDAO();	
	public static IReimbursementStatusDAO rsDao = new ReimbursementStatusDAO();
	public static IReimbursementTypeDAO rtDao = new ReimbursementTypeDAO();
	
	public static ReimbursementService rs = new ReimbursementService();
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
		UserRoles role1 = new UserRoles(1, "Employee");		
		UserRoles role2 = new UserRoles(2, "Financial Manager");
		ursDao.addUserRoles(role1);
		ursDao.addUserRoles(role2);
		
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
		
		
		ReimbursementStatus rStatus1 = rsDao.findById(1);	// PENDING
		ReimbursementStatus rStatus2 = rsDao.findById(2);	// APPROVED
		ReimbursementStatus rStatus3 = rsDao.findById(3);	// DENIED
		
		ReimbursementType rType1 = rtDao.findById(1);		// LODGING
		ReimbursementType rType2 = rtDao.findById(2);		// TRAVEL
		ReimbursementType rType3 = rtDao.findById(3);		// FOOD
		ReimbursementType rType4 = rtDao.findById(4);		// OTHER

		
		Reimbursement reimb1 = new Reimbursement(10000.00, null, null, "Guest tribute for royal progress to Winterfell.", user3, user1, rStatus1, rType1);
		Reimbursement reimb2 = new Reimbursement(10.00, null,null, "Purchased a horse to travel to the Wall.", user4, user2, rStatus1, rType2);
		Reimbursement reimb3 = new Reimbursement(50000.00, null, null, "Bought a bowl of brown in Flea Bottom.", user5, user1, rStatus1, rType3);
		Reimbursement reimb4 = new Reimbursement(90000.00, null, null, "Payment for the breastplate stretcher.", user3, user2, rStatus1, rType4);
		Reimbursement reimb5 = new Reimbursement(5.00, null, null, "Bribe for the goldcloaks to let me out of the city.", user4, user1, rStatus1, rType4);
		rs.addReimbursement(reimb1);
		rs.addReimbursement(reimb2);
		rs.addReimbursement(reimb3);
		rs.addReimbursement(reimb4);
		rs.addReimbursement(reimb5);

		// This is Dummy Data that tests persistence to the DB.

	}

}
