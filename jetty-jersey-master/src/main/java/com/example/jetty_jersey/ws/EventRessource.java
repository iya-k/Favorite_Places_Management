package com.example.jetty_jersey.ws;

import java.util.List;

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
import com.irif.projet.genielogiciel.jetty_jersey.DAO.AbstractDAOFactory;
import com.irif.projet.genielogiciel.jetty_jersey.DAO.DAO;
import com.irif.projet.genielogiciel.jetty_jersey.model.Event;

@Path("/"+Constants.EVENTS)
public class EventRessource {

	AbstractDAOFactory daoFact = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	DAO<Event> eventDao = daoFact.getEventDAO();

	//list of events of map
    @Path("/"+Constants.EVENTS)
    @GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Event> getEvents(@PathParam("q")String q) {
    	List<Event> retour = null;
			retour = eventDao.findAllById(Constants.eINDEX, Constants.eTYPE, q);
		return retour;
	}

    //detail d'un event
	@GET
	@Path("/{id_event}")
	@Produces(MediaType.APPLICATION_JSON)
	public Event getEvent(@PathParam("q")String q){
			Event event = eventDao.find(Constants.eINDEX, Constants.eTYPE, q);
		return event;
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public int addEvent(Event event){
			return eventDao.add(Constants.eINDEX, Constants.eTYPE,event);
	}
	
	 @POST
	 @Path("/{id_map}")
	 @Consumes(MediaType.APPLICATION_JSON)
	 public int updateEvent(Event event) {
		 return eventDao.update(Constants.eINDEX, Constants.eTYPE, event);
	}

	@DELETE
	@Path("/{id_event}")
	public boolean deleteEvent(@PathParam("index")String index){
		return eventDao.deleteIndex(index);
	}
    
    @POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/test")
	public void retrieveEvent() {
		System.out.println(Constants.EVENTS);
    }

}
