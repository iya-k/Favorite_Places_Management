package com.example.jetty_jersey.dao;

public class User {
  int id_user;
  static int cpt = 1;
  String username;
  String email;
  String password;
  
  public User() {username = "test";}
  
  public User(String name,String email,String password){
	  this.id_user = cpt;
	  this.username = name;
	  this.email = email;
	  this.password = password;
	  cpt++;
  }
  
  public String getUsername() {
	return username;
  }

  public void setUsername(String username) {
	  this.username = username;
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

  public String getId() {
	  return id_user+"";
  }  
}
