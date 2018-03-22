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

import com.example.jetty_jersey.ws.EventRessource.EventClass;
import com.irif.projet.genielogiciel.jetty_jersey.DAO.AbstractDAOFactory;
import com.irif.projet.genielogiciel.jetty_jersey.DAO.DAO;
import com.irif.projet.genielogiciel.jetty_jersey.model.Event;

@Path("/id_map")
public class PlaceRessource {

	public static class PlaceClass {
		public int id_place;
	}

	AbstractDAOFactory daoFact = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	DAO<Event> placeDao = daoFact.getUserDAO();

    private static void registerException(Exception e) {
        Logger.getLogger(MapRessource.class.getName()).log(Level.SEVERE, null, e);
    }

    @Context HttpServletRequest request;
    
    @GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/id_place")
	public PlaceClass getEventById() {
    	PlaceClass instance = new PlaceClass();
		instance.id_place = 2;

		return instance;
	}

    @POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/pl")
	public void retrievePlace(PlaceClass instance) {
		System.out.println(instance.id_place);
    }
    
}
