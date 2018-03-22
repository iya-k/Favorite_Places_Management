package com.example.jetty_jersey.ws;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.irif.projet.genielogiciel.jetty_jersey.DAO.AbstractDAOFactory;
import com.irif.projet.genielogiciel.jetty_jersey.DAO.DAO;
import com.irif.projet.genielogiciel.jetty_jersey.model.Event;

@Path("/login")
public class LoginRessource {

	public static class LoginClass {
		public String username;
		public String pass;
	}
	
	AbstractDAOFactory daoFact = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	DAO<Event> eventDao = daoFact.getUserDAO();

    private static void registerException(Exception e) {
        Logger.getLogger(MapRessource.class.getName()).log(Level.SEVERE, null, e);
    }

    @Context HttpServletRequest request;
    

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/user")
	public LoginClass getLogin() {
		LoginClass instance = new LoginClass();
		instance.username = "Test";
		instance.pass = "ok";

		return instance;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/pass")
	public void retrieveLogin(LoginClass instance) {
		System.out.println(instance.username);
		System.out.println(instance.pass);
	}

}
