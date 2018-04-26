package com.example.jetty_jersey.ws.requests;

public class SignUp {

	private String firstName;
	private String lastName;
	private String email;
	private String username;
	private String password;
	private String confirmPassword;
	
	public SignUp() {
		
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

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@Override
	public String toString() {
		return "first name: " + firstName +
				"\nlast name: " + lastName +
				"\nemail: " + email +
				"\nusername: " + username +
				"\npassword: " + password +
				"\nconfirm password: " + confirmPassword;
	}
	
	public int checkFields() {
		//validador
		
		if(!Validator.checkName(firstName)) {
			return 0;
		}
		//si tout est ok
		return 1;
	}
	
}
