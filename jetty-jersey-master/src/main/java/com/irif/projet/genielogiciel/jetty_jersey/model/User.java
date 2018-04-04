package com.irif.projet.genielogiciel.jetty_jersey.model;

public class User {
	private static int cpt = 1;
	private int id;
	private String username;//username unique a utiliser comme primary key
	private String firstname;
	private String lastname;
	private String email;
	private String password;

	public User(){}
	
	public User(String username,String firstname,String lastname,String email,String password){
		this.id=cpt;
		this.username=username;
		this.firstname=firstname;
		this.lastname=lastname;
		this.email=email;
		this.password=password;
		cpt++;
	}
	

	public int getId(){
		return id;
	}

	public void setUserId(int id) {
		this.id = id;
	}

	public String getName(){
		return username;
	}

	public void setName(String username){
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

	public static  int getCpt() {
		return cpt;
	}
}