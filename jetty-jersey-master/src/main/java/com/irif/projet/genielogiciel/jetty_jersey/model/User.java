package com.irif.projet.genielogiciel.jetty_jersey.model;

import java.util.ArrayList;

import com.example.jetty_jersey.ws.requests.SignUp;

public class User {
	private String username;//username unique a utiliser comme primary key
	private String firstname;
	private String lastname;
	private String email;
	private String password;

	public User(){}

	public User(SignUp SignUpRequest) {
		this.username = SignUpRequest.getUsername();
		this.firstname = SignUpRequest.getFirstName();
		this.lastname = SignUpRequest.getLastName();
		this.email = SignUpRequest.getEmail();
		this.password = SignUpRequest.getPassword();
	}

	public User(String username,String firstname,String lastname,String email,String password){
		this.username=username;
		this.firstname=firstname;
		this.lastname=lastname;
		this.email=email;
		this.password=password;
	}
	public String getUsername(){
		return username;
	}

	public void setUsername(String username){
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

	public void setPassword(String password){
		this.password = password;
	}

	public String toString(){
		String res = "Username : "+username + " password : "+ password;
		return res;
	}

}