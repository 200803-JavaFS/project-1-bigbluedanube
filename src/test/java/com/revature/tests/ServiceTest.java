package com.revature.tests;
import static org.junit.jupiter.api.Assertions.*;	// This replaces assertEquals and assertTrue.

import org.apache.logging.log4j.LogManager;			// Of course, if the Jupiter API stops working for me, I will nix it.
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;	// The Linter suggested this because it comes from JUnit 5 whereas @Before is from JUnit 4.
import org.junit.jupiter.api.Test;			// JUnit.Jupiter.API is the updated API for JUnit Testing...? Yes...?

import com.revature.models.*;

import com.revature.services.LoginService;
import com.revature.services.ReimbursementService;
import com.revature.services.UserRoleService;
import com.revature.services.UserService;

class ServiceTest {
	private static final Logger log = LogManager.getLogger(LoginService.class);

	public static Reimbursement reTest;
	public static ReimbursementStatus rsTest;
	public static ReimbursementType rtTest;
	public static User uTest;
	public static UserRoles urTest;
	
	public static LoginService loginServTest;
	public static ReimbursementService reimbServTest;
	public static UserRoleService userRoleTest;
	public static UserService userServTest;	
		

	public ServiceTest() {
		super();
	}
	
	@BeforeClass
	public static void setServiceLayer() {
		log.info("in @BeforeClass");
		
		loginServTest = new LoginService();
		reimbServTest = new ReimbursementService();
		userRoleTest = new UserRoleService();
		userServTest = new UserService();
	}
	
	@BeforeEach
	public static void setUnitTests() {
		log.info("in @Before");
		
		reTest = new Reimbursement(999.99, null, null, "Testing the walls of Winterfell.", uTest, uTest, rsTest, rtTest);
		rsTest = new ReimbursementStatus(1, "LODGING");
		rtTest = new ReimbursementType(1, "FOOD");
		uTest = new User("ThisIsATest", "testpassword", "Tester", "McTestorsen", "thisisatest@junit.org", urTest);
		urTest = new UserRoles(1, "Employee");
	}
	
	// I have never done JUnit testing in Java, so I looked at Nancy Ballar's code for help.
	
	@Test
	public void loginTest() {
		log.info("running loginTest...");
		User u = new User("YetAnotherTest", "testpassword2", "Tester Junior", "McTestorsen", "alsoatest@junit.org", urTest);
		System.out.println(u);
		boolean loginBae = loginServTest.login(u);
	}
	
	
	@Test
	public void addUserTest() {
		log.info("running addUserTest...");
		
	}
	
	@Test
	public void addReimbursementTest() {
		log.info("running addReimbursementTest...");

	}
	
	
	@AfterClass
	public static void addUser() {
		log.info("in @AfterClass");
	}	
	
	
}
