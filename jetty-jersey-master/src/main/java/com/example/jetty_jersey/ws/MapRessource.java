package com.example.jetty_jersey.ws;

import com.example.jetty_jersey.constant.Constants;
import com.example.jetty_jersey.ws.requests.MapRequest;
import com.irif.projet.genielogiciel.jetty_jersey.DAO.AbstractDAOFactory;
import com.irif.projet.genielogiciel.jetty_jersey.DAO.DAO;
import com.irif.projet.genielogiciel.jetty_jersey.model.Map;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.util.List;


@Path("/"+Constants.MAPS)
public class MapRessource {
    
    AbstractDAOFactory daoFact = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
    DAO<Map> mapDao = daoFact.getMapDAO();
    Map retour = null;
    int ok;

    @Path("/id_map")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map getMap(@PathParam("q")String q) {
         Map status = null;
        if (q != "") {
        	status = mapDao.find(Constants.mINDEX, Constants.mTYPE, q);
        }
        return status;
    }
    @Path("/list")
    @GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Map> getMaps(@PathParam("q")String q) {
    	List<Map> retour = null;
    	if (q != "") {
			retour = mapDao.findAllById(Constants.mINDEX, Constants.mTYPE, q);
    	}

		return retour;
	}

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public int addMap(Map obj) {
		ok = mapDao.add(Constants.mINDEX, Constants.mTYPE, obj);

        return ok;
    }

    @POST
    @Path("/{id_map}")
    @Consumes(MediaType.APPLICATION_JSON)
    public int updateMap(Map map) {
		 return mapDao.update(Constants.mINDEX, Constants.mTYPE, map);
    }
    
    @DELETE
    @Path("/{id_map}")
    public boolean deleteMap(@PathParam("index")String index) {
        return mapDao.deleteIndex(index);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/add_map")
    public int addMap(MapRequest mapRequest) {

        System.out.println(mapRequest.toString());

        return 0;
    }
}