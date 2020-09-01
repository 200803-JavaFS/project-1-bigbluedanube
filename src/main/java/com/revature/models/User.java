package com.revature.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ers_users")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ers_users")
	private int userId;
	
	@Column(name="ers_username", nullable=false)
	private String username;
	
	@Column(name="ers_password", nullable=false)
	private String password;
	
	@Column(name="user_first_name", nullable=false)
	private String firstName;
	
	@Column(name="user_last_name", nullable=false)
	private String lastName;
	
	@Column(name="user_email", nullable=false)
	private String email;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="user_role_id_fk", referencedColumnName="ers_user_role_id", nullable=false)
	private UserRoles userRoleIdFk;
	
	
	/*
	 * "FetchType defines when Hibernate will go to the DB to fetch an associated object."
	 * LAZY - causes Hibernate to give a proxy object until your code actually calls for the object.
	 * It's an object with no data, like a null, but not.
	 * This only works while the object is persistent.
	 * Once it becomes "detached", there is no longer a Session to replace the proxy.
	 * "FetchType" EAGER returns the dependent object immediately with no proxy. This is generally safer.
	 * 
	 * There can be MANY movies to ONE director, so this is a Many to one relationship.
	 * 
	 * Cascade defines how to queries will maintain referential integrity.
	 */
	
	public User(int userId, String username, String password, String firstName, String lastName, String email, UserRoles userRoleIdFk) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userRoleIdFk = userRoleIdFk;
	}

	public User() {
		super();
	}

	public User(String username, String password, String firstName, String lastName, String email, UserRoles userRoleIdFk) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userRoleIdFk = userRoleIdFk;
	}
	
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", firstName="
				+ firstName + ", lastName=" + lastName + ", email=" + email + ", userRoleIdFk=" + userRoleIdFk + "]";
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + userId;
		result = prime * result + ((userRoleIdFk == null) ? 0 : userRoleIdFk.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userId != other.userId)
			return false;
		if (userRoleIdFk == null) {
			if (other.userRoleIdFk != null)
				return false;
		} else if (!userRoleIdFk.equals(other.userRoleIdFk))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public UserRoles getUserRoleId() {
		return userRoleIdFk;
	}

	public void setUserRoleId(UserRoles userRoleIdFk) {
		this.userRoleIdFk = userRoleIdFk;
	}

}


/* I suppose business logic for user_roles (employee versus finManager would be carried out in the Service Layer?
 * or things would be declared/instantiated/initialized in the User_Role model and the logic would be carried out in
 * UserRoleServices? I think the latter is probably more likely. The goal is to get something that is as clean and simple as possible.
 * Nikki says ALL Models should be POJOs. Methods should be in the respective DAO.
*/
