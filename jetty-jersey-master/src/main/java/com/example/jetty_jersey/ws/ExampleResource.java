package com.example.jetty_jersey.ws;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.irif.projet.genielogiciel.jetty_jersey.DAO.AbstractDAOFactory;
import com.irif.projet.genielogiciel.jetty_jersey.DAO.DAO;
import com.irif.projet.genielogiciel.jetty_jersey.model.User;

@Path("/example")
public class ExampleResource {

	public static class ExampleClass {
		public String field;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/aircraft")
	public ExampleClass getExample() {
		ExampleClass instance = new ExampleClass();
		instance.field = "Test";

		return instance;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/aircraft")
	public void retrieveExample(ExampleClass instance) {
		System.out.println(instance.field);
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/test")
	public void getDAOexample() {
		//Test
		//findAllTest();
		//findTest();
		//updateTest();
		createTest();
		//deleteTest();
	}
	
	public static void createTest(){
		try {
			User usr = new User("Jojo", "Julien", "TEST", "chic@univ-paris-diderot.com", "1*2+3/4");
			AbstractDAOFactory aDAOF = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
			DAO<User> userDAO =aDAOF.getUserDAO();
			userDAO.create("userdb","user",usr);			
		}catch(JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteTest(){
		User usr = new User("Jojo", "Julien", "TEST", "chic@univ-paris-diderot.com", "1*2+3/4");
		AbstractDAOFactory aDAOF = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		DAO<User> userDAO =aDAOF.getUserDAO();
		userDAO.delete("userdb","user",usr);
	}
	
	public static void updateTest(){
		try {
			User usr = new User("Jojo_123", "Julien_123", "TEST123", "chic@univ-paris-diderot.com", "1*2+3/4");
			AbstractDAOFactory aDAOF = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
			DAO<User> userDAO =aDAOF.getUserDAO();
			userDAO.update("userdb","user",usr);			
		}catch(JsonProcessingException | InterruptedException | ExecutionException e){
			e.printStackTrace();
		}
	}
	public static void findTest(){
		try {
			User usr = new User("Jojo_123", "Julien_123", "TEST123", "chic@univ-paris-diderot.com", "1*2+3/4");
			AbstractDAOFactory aDAOF = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
			DAO<User> userDAO =aDAOF.getUserDAO();
			User test = userDAO.find("userdb","user",usr);
			System.out.println("Name test : "+test.getFirstname());
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public static void findAllTest(){
		try {
			AbstractDAOFactory aDAOF = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
			DAO<User> userDAO =aDAOF.getUserDAO();
			ArrayList<User> usersList=(ArrayList)userDAO.findAll("userdb","user");
			System.out.println("findAll Test : \n"+usersList);
		}catch(IOException e){
			e.printStackTrace();
		}
	}

}
