package com.example.jetty_jersey.dao;


public class User {
	int userId;
	String username;//username unique a utiliser comme primary key
	String firstname;
	String lastname;
	String email;
	String password;

	public User() {
		
	}
	
	public User(int userId,String username,String firstname,String lastname,String email,String password) {
		this.userId=userId;
		this.username=username;
		this.firstname=firstname;
		this.lastname=lastname;
		this.email=email;
		this.password=password;
	}
	
	
	


}
