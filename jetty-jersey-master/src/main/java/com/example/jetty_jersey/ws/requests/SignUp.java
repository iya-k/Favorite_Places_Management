package com.example.jetty_jersey.ws.requests;

public class SignUp {

	private String fullname;
	private String username;
	private String password;
	private String password2;
	
	public SignUp() {
		
	}
	
	public SignUp(String fullname, String username, String password, String password2) {
		super();
		this.fullname = fullname;
		this.username = username;
		this.password = password;
		this.password2 = password2;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
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

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	
	@Override
	public String toString() {
		return "fullname: " + fullname +
				"\nusername: " + username +
				"\npassword: " + password +
				"\npassword2: " + password2;
	}
	
	public int[] checkFields() {
		int[] validation = {0, 0, 0, 0, 0};
		
		if(
				(fullname.isEmpty()) || 
				(username.isEmpty()) ||
				(password.isEmpty()) ||
				(password.isEmpty())
			) {
			
		}else {
			
		}
		
		return validation;
	}
	
}
