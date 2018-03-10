package com.irif.projet.genielogiciel.jetty_jersey.model;

public class User {
	private int userId;
	private String username;//username unique a utiliser comme primary key
	private String firstname;
	private String lastname;
	private String email;
	private String password;

	public User(){}
	
	public User(int userId,String username,String firstname,String lastname,String email,String password) {
		this.userId=userId;
		this.username=username;
		this.firstname=firstname;
		this.lastname=lastname;
		this.email=email;
		this.password=password;
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

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	


}