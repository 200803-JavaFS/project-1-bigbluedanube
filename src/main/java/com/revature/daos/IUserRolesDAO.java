package com.revature.daos;

import java.util.List;

import com.revature.models.UserRoles;


public interface IUserRolesDAO {
	
	public List<UserRoles> findAll();
	
	public UserRoles findById(int id);
	
	public boolean addUserRoles(UserRoles urs);
	
	public boolean updateUserRoles(UserRoles urs);
	
	public boolean deleteUserRoles(int userRoleId);
}
