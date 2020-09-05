package com.revature.daos;

import java.util.List;

import com.revature.models.User;

public interface IUserDAO {
	
	public List<User> findAll();
	
	public User findById(int id);
	
	User findByUsername(String username);
	
	User findByPassword(String password);
	
	public boolean addUser(User u);
	
	public boolean updateUser(User u); 
	
	public boolean deleteUser(int userId);


}