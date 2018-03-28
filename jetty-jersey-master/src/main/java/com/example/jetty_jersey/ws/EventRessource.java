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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.irif.projet.genielogiciel.jetty_jersey.DAO.AbstractDAOFactory;
import com.irif.projet.genielogiciel.jetty_jersey.DAO.DAO;
import com.irif.projet.genielogiciel.jetty_jersey.model.Event;

@Path("/id_map")
public class EventRessource {

	AbstractDAOFactory daoFact = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	DAO<Event> eventDao = daoFact.getEventDAO();
	boolean ok;

    private static void registerException(Exception e) {
        Logger.getLogger(MapRessource.class.getName()).log(Level.SEVERE, null, e);
    }

    @Path("/"+Constants.EVENTS)
    @GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Event> getEvents(@PathParam("index")String index) {
    	List<Event> retour = null;
		try {
			retour = eventDao.findAll(index, Constants.EVENTS);
		} catch (IOException e) {
			registerException(e);
		}

		return retour;
	}

	@GET
	@Path("/{id_event}")
	@Produces(MediaType.APPLICATION_JSON)
	public Event getEvent(Event event){
		try {
			event = eventDao.find(String.valueOf(event.getId()), Constants.EVENTS, event);
		} catch (IOException e) {
			registerException(e);
		}
		return event;
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean addEvent(Event event) {
		ok = false;
		 try {
			ok = eventDao.create(String.valueOf(event.getId()),Constants.EVENTS,event);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			registerException(e);
		}
		
		return ok;
	}

	@DELETE
	@Path("/{id_event}")
	public boolean deleteEvent(Event event) {
		ok = eventDao.delete(String.valueOf(event.getId()),Constants.EVENTS,event);
		return ok;
	}
    
    @POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/test")
	public void retrieveEvent() {
		System.out.println(Constants.EVENTS);
    }

}
