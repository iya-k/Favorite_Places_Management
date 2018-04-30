package com.example.jetty_jersey.ws;

import com.example.jetty_jersey.constant.Constants;
import com.example.jetty_jersey.ws.requests.PlaceRequest;
import com.irif.projet.genielogiciel.jetty_jersey.DAO.AbstractDAOFactory;
import com.irif.projet.genielogiciel.jetty_jersey.DAO.DAO;
import com.irif.projet.genielogiciel.jetty_jersey.model.Event;
import com.irif.projet.genielogiciel.jetty_jersey.model.Map;
import com.irif.projet.genielogiciel.jetty_jersey.model.Picture;
import com.irif.projet.genielogiciel.jetty_jersey.model.Place;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.util.List;
@Path("/id_map")
public class PlaceRessource {
    AbstractDAOFactory daoFact = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
    DAO<Place> placeDao = daoFact.getPlaceDAO();
    DAO<Event> eventDao = daoFact.getEventDAO();
    DAO<Picture> pictureDao = daoFact.getPictureDAO();
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

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/add_place")
    public int addPlace(PlaceRequest placeRequest) {
    	int status = 0;
        System.out.println(placeRequest.toString());
        placeRequest.saveImages("place_image_");

    	if(placeRequest!=null){
    		if(placeRequest.getType() ==1){
    			Place place = new Place(placeRequest, Constants.getCurrentMapName());
    			status = placeDao.add(Constants.pINDEX, Constants.pTYPE, place);
    			if(status ==1){
    				String imageName = Constants.getUserId()+Constants.getCurrentMapName()+placeRequest.getName()+Constants.getCurrentDateTime("yyyyMMddhhmmss");
                    placeRequest.saveImages(imageName);
                    Picture pic;
                    String placeid = placeDao.getId(Constants.pINDEX, Constants.pTYPE, place);
                    for (int i = 0; i < placeRequest.getImagesPath().length; i++) {
						pic = new Picture(place.getPlacename(), placeRequest.getImagesPath()[i], placeid);
					}
    			}
    		}else{
    			Event event  = new Event(placeRequest, Constants.getCurrentMapName());
    			status = eventDao.add(Constants.eINDEX, Constants.eTYPE, event);
    			if(status ==1){
    				String imageName = Constants.getUserId()+Constants.getCurrentMapName()+placeRequest.getName()+Constants.getCurrentDateTime("yyyyMMddhhmmss");
                    placeRequest.saveImages(imageName);
                    Picture pic;
                    String eventid = placeDao.getId(Constants.eINDEX, Constants.eTYPE, event);
                    for (int i = 0; i < placeRequest.getImagesPath().length; i++) {
						pic = new Picture(event.getPlacename(), placeRequest.getImagesPath()[i], eventid);
					}
    			}
    		}
    	}
        
        switch (status) {
            case 0:
                System.out.println("missing data");
                break;
            case 1:
                System.out.println("add map with successes");
                break;
            default: // -1
                System.out.println("something went wrong");
                break;
        }
        return status;
    }
}
