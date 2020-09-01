package com.revature.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.daos.UserRolesDAO;
import com.revature.daos.IUserRolesDAO;
import com.revature.models.UserRoles;

public class UserRoleService {
	
	private static final Logger log = LogManager.getLogger(UserService.class);
	
	IUserRolesDAO ursDao = new UserRolesDAO();
	
	public List<UserRoles> findAll(){
		log.info("Retrieving all User Roles.");
		return ursDao.findAll();
	}
	
	public UserRoles findById(int id) {
		log.info("Finding User Roles by ID.");
		return ursDao.findById(id);
	}
	
	public boolean addUserRoles(UserRoles urs) {
		log.info("Adding new User Role...");
		return ursDao.addUserRoles(urs);
	}
	
	public boolean updateUserRoles(UserRoles urs) {
		log.info("Updating User Roles...");
		return ursDao.updateUserRoles(urs);
	}
	
	public boolean deleteUserRoles(int userRoleId) {
		log.info("Deleting User Roles.");
		return ursDao.deleteUserRoles(userRoleId);
	}
	

}
