package com.example.jetty_jersey.ws;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.example.jetty_jersey.constant.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.irif.projet.genielogiciel.jetty_jersey.DAO.AbstractDAOFactory;
import com.irif.projet.genielogiciel.jetty_jersey.DAO.DAO;
import com.irif.projet.genielogiciel.jetty_jersey.model.Map;


@Path("/"+Constants.MAPS)
public class MapRessource {

	
	AbstractDAOFactory daoFact = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	DAO<Map> mapDao = daoFact.getMapDAO();
	Map retour = null;
	boolean ok;

    private static void registerException(Exception e) {
        Logger.getLogger(MapRessource.class.getName()).log(Level.SEVERE, null, e);
    }

    @Path("/list")
    @GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Map> getMaps(@PathParam("index")String index) {
    	List<Map> retour = null;
		try {
			retour = mapDao.findAll(index, Constants.MAPS);
		} catch (IOException e) {
			registerException(e);
		}

		return retour;
	}

	@GET
	@Path("/{id_map}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map getMap(Map map){
		try {
			map = mapDao.find(String.valueOf(map.getMapId()), Constants.MAPS, map);
		} catch (IOException e) {
			registerException(e);
		}
		return map;
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean addMap(Map map) {
		ok = false;
		 try {
			ok = mapDao.create(String.valueOf(map.getMapId()),Constants.MAPS,map);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			registerException(e);
		}
		
		return ok;
	}

	@DELETE
	@Path("/{id_map}")
	public boolean deleteMap(Map map) {
		ok = mapDao.delete(String.valueOf(map.getMapId()),Constants.MAPS,map);
		return ok;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/test")
	public void retrieve() {
		System.out.println(Constants.MAPS);
    }
}
