package com.example.jetty_jersey.ws;

import com.example.jetty_jersey.constant.Constants;
import com.example.jetty_jersey.ws.requests.MapRequest;
import com.irif.projet.genielogiciel.jetty_jersey.DAO.AbstractDAOFactory;
import com.irif.projet.genielogiciel.jetty_jersey.DAO.DAO;
import com.irif.projet.genielogiciel.jetty_jersey.model.Map;
import com.irif.projet.genielogiciel.jetty_jersey.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
		System.out.println("getMap");
		q ="";
		String mapname = Constants.getUserId()+" "+q;
		Map map = mapDao.find("mapdb","map",mapname);
		//System.out.println("Map trouve : "+map);
		if(map != null){
			Constants.setCurrentMapName(mapDao.getId("mapdb","map",map));
			for (int i = 0; i < map.getPlaceList().size(); i++) {
				//System.out.println("place "+map.getPlaceList().get(i));
			}
			for (int i = 0; i < map.getEventList().size(); i++) {
				//System.out.println("place "+map.getEventList().get(i));
			}
		}
		return status;
	}
	@Path("/list")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Map> getMaps(@PathParam("q")String q) {
		System.out.println("getMaps");
		String query = "NJdv92IBe9pBoNiC9K3Ymap";
		List<Map> listMap = mapDao.findAllById(Constants.mINDEX, Constants.mTYPE,query);
		return listMap;
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
	public int deleteMap(Map map) {
		return mapDao.delete(Constants.mINDEX, map);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/add_map")
	public int addMap(MapRequest mapRequest) {
		int status = 0;
		System.out.println(mapRequest.toString());
		mapRequest.saveImage("map_image_");

        Map map;
        if (mapRequest != null) {
			String imageName = Constants.getCurrentUser().getUsername()+mapRequest.getName()+Constants.getCurrentDateTime("yyyyMMddhhmmss");
			mapRequest.saveImage(imageName);
			map = new Map(mapRequest,Constants.getUserId());
			status = mapDao.add(Constants.uINDEX, Constants.uTYPE, map);
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