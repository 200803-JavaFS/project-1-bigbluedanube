package com.revature.models;

public class LoginDTO {
	
	//DTO = Data Transfer Object, because we currently do not have a Full User with all the information that comes with it.
	public String username;
	public String password;
	
	public LoginDTO() {
		super();
	}
	
	public LoginDTO(String string, String string2) {
		super();
		this.username = string;
		this.password = string2;
	}


	public void setUsername(String newName) {
		this.username = newName;
	}


	public void setPassword(String newPW) {
		this.password = newPW;
	}
	
}
