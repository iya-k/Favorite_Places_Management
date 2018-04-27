package com.example.jetty_jersey.ws;

import com.example.jetty_jersey.constant.Constants;
import com.example.jetty_jersey.ws.requests.ForgetPassword;
import com.example.jetty_jersey.ws.requests.Login;
import com.example.jetty_jersey.ws.requests.ResetPassword;
import com.example.jetty_jersey.ws.requests.SignUp;
import com.irif.projet.genielogiciel.jetty_jersey.DAO.AbstractDAOFactory;
import com.irif.projet.genielogiciel.jetty_jersey.DAO.DAO;
import com.irif.projet.genielogiciel.jetty_jersey.DAO.implement.UserDAO;
import com.irif.projet.genielogiciel.jetty_jersey.model.User;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("/" + Constants.USERS)
public class LoginRessource {
	AbstractDAOFactory daoFact = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	DAO<User> userDao = daoFact.getUserDAO();

	
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	public int signUp(SignUp signUpRequest) {
		int status = 0;
		User user;
		System.out.println(signUpRequest.toString());
		if (signUpRequest != null) {
			user = new User(signUpRequest);
			status = userDao.add(Constants.uINDEX, Constants.uTYPE, user);
		}
		switch (status) {
		case -1:
			System.out.println("Exception");
			break;
		case 0:
			System.out.println("Echec");
			break;
		case 1:
			System.out.println("Reussite");
			break;
		}
		return status;
	}

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	public int signIn(Login loginRequest) {
		int status = 0;
		System.out.println("login ["+loginRequest+" ]");
		if(loginRequest != null) {
			Constants.setCurrentUser(((UserDAO)userDao).connect(Constants.uINDEX,Constants.uTYPE,loginRequest.getUsername(),loginRequest.getPassword()));
			Constants.setUserId(userDao.getId(Constants.uINDEX,Constants.uTYPE,Constants.getCurrentUser()));
		}
		status = Constants.getCurrentUser() == null?0:1;

		switch(status) {
		case 0:System.out.println("Echec");break;
		case 1:System.out.println("Reussite");break;
		}
		return status;
	}

	@DELETE
	@Path("/{id}")
	public int deleteUser(User user) {
		int status = 0;
		if (user != null) {
			status = userDao.delete(Constants.uINDEX, user);
		}

		switch (status) {
		case 0:
			System.out.println("Echec");
			break;
		case 1:
			System.out.println("Reussite");
			break;
		}

		return status;
	}

	@POST
	@Path("/forget_password")
	@Consumes(MediaType.APPLICATION_JSON)
	public int forgetPassword(ForgetPassword forgetPasswordRequest) {
		int status = 0;

		System.out.println(forgetPasswordRequest.toString());

		return status;
	}

	@POST
	@Path("/reset_password")
	@Consumes(MediaType.APPLICATION_JSON)
	public int resetPassword(ResetPassword resetPasswordRequest) {
		int status = 0;
		if(((UserDAO)userDao).connect(Constants.uINDEX,Constants.uTYPE,Constants.getCurrentUser().getUsername(),resetPasswordRequest.getActivationCode()) !=null){
			User user = Constants.getCurrentUser();
			user.setPassword(resetPasswordRequest.getNewPassword());
			status = ((UserDAO)userDao).update(Constants.uINDEX,Constants.uTYPE, user);
			if(status == 1){
				Constants.setCurrentUser(user);
			}
		}
		switch (status) {
		case 0:
			System.out.println("Echec");
			break;
		case 1:
			System.out.println("Reussite");
			break;
		}
		return status;
	}

}
