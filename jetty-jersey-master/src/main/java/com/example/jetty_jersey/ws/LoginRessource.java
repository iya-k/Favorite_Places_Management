package com.example.jetty_jersey.ws;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.example.jetty_jersey.constant.Constants;
import com.example.jetty_jersey.ws.ExampleResource.ExampleClass;
import com.example.jetty_jersey.ws.requests.Login;
import com.example.jetty_jersey.ws.requests.SignUp;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.irif.projet.genielogiciel.jetty_jersey.DAO.AbstractDAOFactory;
import com.irif.projet.genielogiciel.jetty_jersey.DAO.DAO;
import com.irif.projet.genielogiciel.jetty_jersey.model.User;

@Path("/"+Constants.USERS)
public class LoginRessource {

	AbstractDAOFactory daoFact = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	DAO<User> userDao = daoFact.getUserDAO();
	User retour = null;
	boolean ok;

    private static void registerException(Exception e) {
        Logger.getLogger(LoginRessource.class.getName()).log(Level.SEVERE, null, e);
    }
    
    @POST
    @Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	public int signIn(Login loginRequest) {
    	int status = 0;
    	
    	System.out.println("login POST request has being called");
    	
    	if(loginRequest != null) {
    		System.out.println(loginRequest.toString());
    	}
    	
//    	System.out.println(password);
//		ok = false;
//		List<User> users;
//		try {
//			users = userDao.findAll(login, Constants.USERS,0);
//			for (User u : users) {
//				System.out.println(u.toString());
//				if (u.getName().equals(login)) {
//					if (u.getPassword().equals(password)) {
//						ok = true;
//					}
//				}
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			registerException(e);
//		}

		return status;
	}
    

    @POST
    @Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
    public int signUp(SignUp SignUpRequest){

    	System.out.println("register POST request has being called");
    	
    	if(SignUpRequest != null) {
    		System.out.println(SignUpRequest.toString());
    	}
    	
    	return 0;
    }
    
    @PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean addUser(User user) {
		ok = false;
		 try {
			ok = userDao.create(String.valueOf(user.getId()),Constants.USERS,user);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			registerException(e);
		}
		
		return ok;
	}

	@DELETE
	@Path("/{id}")
	public boolean deleteUser(User user){
		ok = userDao.delete(String.valueOf(user.getId()),Constants.USERS, user);
		return ok;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/test")
	public void retrieve() {
		System.out.println(Constants.USERS);
    }


}
