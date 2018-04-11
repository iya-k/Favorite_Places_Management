package com.example.jetty_jersey.ws;

import com.example.jetty_jersey.constant.Constants;
import com.example.jetty_jersey.ws.requests.PlaceRequest;
import com.irif.projet.genielogiciel.jetty_jersey.DAO.AbstractDAOFactory;
import com.irif.projet.genielogiciel.jetty_jersey.DAO.DAO;
import com.irif.projet.genielogiciel.jetty_jersey.model.Place;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/id_map")
public class PlaceRessource {
    AbstractDAOFactory daoFact = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
    DAO<Place> placeDao = daoFact.getPlaceDAO();
    boolean ok;

    private static void registerException(Exception e) {
        Logger.getLogger(MapRessource.class.getName()).log(Level.SEVERE, null, e);
    }

    @Path("/" + Constants.PLACES)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Place> getPlaces(@PathParam("index") String index) {
        List<Place> retour = null;
		/*try {
			/retour = placeDao.findAll(index, Constants.PLACES,0);
		} catch (IOException e) {
			registerException(e);
		}*/
        return retour;
    }

    @GET
    @Path("/{id_place}")
    @Consumes(MediaType.APPLICATION_JSON)
    //@Produces(MediaType.APPLICATION_JSON)
    public Place getPlace(Place place) {
		/*try {
			place = placeDao.find(String.valueOf(place.getId()), Constants.PLACES, place);
		}catch (IOException e) {
			registerException(e);
		}*/
        return place;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean addPlace(Place place) {
        ok = false;
		/* try {
			ok = placeDao.create(String.valueOf(place.getId()),Constants.PLACES,place);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			registerException(e);
		}*/

        return ok;
    }

    @DELETE
    @Path("/{id_place}")
    public boolean deletePlace(Place place) {
        //ok = placeDao.delete(String.valueOf(place.getId()),place);
        return false;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/testP")
    public void retrievePlace() {
        System.out.println(Constants.PLACES);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/add_place")
    public int addPlace(PlaceRequest placeRequest) {
        System.out.println(placeRequest.toString());
        return 0;
    }
}
