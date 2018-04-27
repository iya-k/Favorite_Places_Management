package com.irif.projet.genielogiciel.jetty_jersey.model;

import java.util.ArrayList;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

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
		this.password = Encrypt(username,SignUpRequest.getPassword());
		System.out.println("Encodeage "+password);
	}

	public User(String username,String firstname,String lastname,String email,String password){
		this.username=username;
		this.firstname=firstname;
		this.lastname=lastname;
		this.email=email;
		this.password=Encrypt(username,password);
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
	
	public static String Encrypt(String key,String mdp){
		String res = "";
        try{
        	byte[] KeyData = key.getBytes();
        	SecretKeySpec KS = new SecretKeySpec(KeyData, "Blowfish");
        	Cipher cipher = Cipher.getInstance("Blowfish");
        	cipher.init(Cipher.ENCRYPT_MODE, KS);
            byte[] encrypted = cipher.doFinal(mdp.getBytes());
            res = DatatypeConverter.printBase64Binary(encrypted);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

}