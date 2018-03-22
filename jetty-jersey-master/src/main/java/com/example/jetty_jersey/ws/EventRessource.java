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

import com.example.jetty_jersey.ws.LoginRessource.LoginClass;
import com.irif.projet.genielogiciel.jetty_jersey.DAO.AbstractDAOFactory;
import com.irif.projet.genielogiciel.jetty_jersey.DAO.DAO;
import com.irif.projet.genielogiciel.jetty_jersey.DAO.DAOFactory;
import com.irif.projet.genielogiciel.jetty_jersey.model.Event;
import com.irif.projet.genielogiciel.jetty_jersey.model.User;

@Path("/id_map")
public class EventRessource {

	public static class EventClass {
		public int idEvent;
	}

	AbstractDAOFactory daoFact = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	DAO<Event> eventDao = daoFact.getUserDAO();

    private static void registerException(Exception e) {
        Logger.getLogger(MapRessource.class.getName()).log(Level.SEVERE, null, e);
    }

    @Context HttpServletRequest request;
    
    @GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/id_event")
	public EventClass getEventById() {
    	EventClass instance = new EventClass();
		instance.idEvent = 1;

		return instance;
	}
    
    @POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/ev")
	public void retrieveEvent(EventClass instance) {
		System.out.println(instance.idEvent);
    }

}
