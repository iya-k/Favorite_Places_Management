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
import com.irif.projet.genielogiciel.jetty_jersey.DAO.implement.UserDAO;
import com.irif.projet.genielogiciel.jetty_jersey.model.User;

@Path("/"+Constants.USERS)
public class LoginRessource {
	private static final String INDEX = "userdb";
	private static final String TYPE = "user";
	private User current_user;
	AbstractDAOFactory daoFact = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	DAO<User> userDao = daoFact.getUserDAO();
	
	User usr = new User("Jojo12345","Jean","CHIC","jojo@free.com","p1a2r3i4s5");//temporaire
	User usr1 = new User("Jo","Joris","Mahieu","mahieu@free.com","3210");//idem
	
	public void test(int status) {   	
    	switch(status){
    		case 1 ://create user
    			userDao.add(INDEX,TYPE,usr);
    			break;
    		case 2 ://connect
    			current_user = ((UserDAO)userDao).connect(INDEX,TYPE,usr.getUsername(),usr.getPassword());
    			break;
    		case 3 : //delete index
    			userDao.deleteIndex(INDEX);
    			break;
    		case 4 ://delete user
    			userDao.delete(INDEX,usr);
    			break;
    		case 5 ://update user
    			current_user = ((UserDAO)userDao).connect(INDEX,TYPE,usr.getUsername(),usr.getPassword());
    			if(current_user != null) {
    				//current_user.setUsername("Charles");
    				current_user.setLastname("Charles");
    				//current_user.setFirstname("Louis123");
    				userDao.update(INDEX, TYPE,current_user);
    			}
    			break;
    		case 6 ://get all users
    			List<User> list = userDao.findAll(INDEX,TYPE,User.class);
        		for(int i = 0; i < list.size();i++) {
        			System.out.println("User : "+list.get(i));
        		}
    			
    	}	
    	
    }
	
	
    private static void registerException(Exception e) {
        Logger.getLogger(LoginRessource.class.getName()).log(Level.SEVERE, null, e);
    }
    
    
    @POST
    @Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
    public int signUp(SignUp SignUpRequest){
    	int status = 0;
    	User user ;
    	
    	if(SignUpRequest != null) {
    		user = new User(SignUpRequest);
    		status = userDao.add(INDEX,TYPE,user);
    	}
    	switch(status) {
    		case -1: System.out.println("Exception");break;
    		case 0:System.out.println("Echec");break;
    		case 1:System.out.println("Reussite");break;
    	}
    	return status;
    }
    
    @POST
    @Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	public int signIn(Login loginRequest){
    	int status = 0;
    	test(5);
    	/*if(loginRequest != null) {
    		current_user = ((UserDAO)userDao).connect(INDEX,TYPE,usr.getUsername(),usr.getPassword());
    	}
    	status = current_user == null?0:1;
    	
    	switch(status) {
			case 0:System.out.println("Echec");break;
			case 1:System.out.println("Reussite");break;
    	}*/
    	return status;
	}
    
    @DELETE
	@Path("/{id}")
	public int deleteUser(User user){
    	int status = 0;
    	if(user != null) {
    		status = userDao.delete(INDEX,usr);
    	}
    	
    	switch(status) {
			case 0:System.out.println("Echec");break;
			case 1:System.out.println("Reussite");break;
    	}
    	
		return status;
	}
}
