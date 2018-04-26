package com.example.jetty_jersey.ws;

import com.example.jetty_jersey.constant.Constants;
import com.example.jetty_jersey.ws.requests.PlaceRequest;
import com.irif.projet.genielogiciel.jetty_jersey.DAO.AbstractDAOFactory;
import com.irif.projet.genielogiciel.jetty_jersey.DAO.DAO;
import com.irif.projet.genielogiciel.jetty_jersey.model.Place;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.util.List;
@Path("/id_map")
public class PlaceRessource {
    AbstractDAOFactory daoFact = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
    DAO<Place> placeDao = daoFact.getPlaceDAO();
    int ok;

    @Path("/" + Constants.PLACES)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Place> getPlaces(@PathParam("query") String query) {
        List<Place> retour = null;
		retour = placeDao.findAllById(Constants.pINDEX, Constants.pTYPE, query);
        return retour;
    }
    
    @GET
    @Path("/{id_place}")
    @Consumes(MediaType.APPLICATION_JSON)
    //detail d'une place
    public Place getPlace(@PathParam("query")String query) {
			return placeDao.find(Constants.pINDEX, Constants.pTYPE, query);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public int addPlace(Place place) {
		 ok = placeDao.add(Constants.pINDEX, Constants.pTYPE,place);
        return ok;
    }

    
    @POST
    @Path("/{id_place}")
    @Consumes(MediaType.APPLICATION_JSON)
    public int updatePlace(Place place) {
		 return placeDao.update(Constants.pINDEX, Constants.pTYPE, place);
    }

    
    @DELETE
    @Path("/{id_place}")
    public int delete(Place place) {
        return placeDao.delete(Constants.pINDEX, place);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/add_place")
    public int addPlace(PlaceRequest placeRequest) {
    	if(placeRequest!=null){
    		if(placeRequest.getType() ==1){
    			//place
    		}else{
    			//event
    		}
    		System.out.println(placeRequest.toString());
            String imageName = "userid_"+"mapid_"+placeRequest.getName()+Constants.getCurrentDateTime("yyyyMMddhhmmss");
            placeRequest.saveImages(imageName);
    	}
        

        return 0;
    }
}
