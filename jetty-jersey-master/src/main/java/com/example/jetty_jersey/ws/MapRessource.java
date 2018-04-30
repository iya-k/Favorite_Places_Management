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

	@Path("/{id_map}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Map getMap(@PathParam("id_map") String id_map) {
		System.out.println("getMap");
		Map status = null;

		System.out.println("id_map = " + id_map);
		if(id_map.equals("-1")){
			// TODO search a last map id
			// id_map = last_id_map;
		}

		String mapname = Constants.getUserId() + " " + id_map;
		System.out.println("mapname = " + mapname);
		Map map = mapDao.find("mapdb","map", mapname);

		System.out.println("Map trouve : "+map);
		if(map != null){
			Constants.setCurrentMapName(mapDao.getId("mapdb","map",map));
			for (int i = 0; i < map.getPlaceList().size(); i++) {
				System.out.println("place "+map.getPlaceList().get(i));
			}
			for (int i = 0; i < map.getEventList().size(); i++) {
				System.out.println("place "+map.getEventList().get(i));
			}
		}
		return status;
	}

	@Path("/list")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Map> getMaps() {
		System.out.println("getMaps");
		List<Map> listMap = null;

		// TODO get current user id
		String user_id = null;
		listMap = mapDao.findAllById(Constants.mINDEX, Constants.mTYPE, user_id);

		return listMap;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public int addMap(MapRequest mapRequest) {
		int status = 0;
		System.out.println(mapRequest.toString());

		Map map;
		if (mapRequest != null) {
			String imageName = Constants.getCurrentUser().getUsername()+mapRequest.getName()+Constants.getCurrentDateTime("yyyyMMddhhmmss");
			mapRequest.saveImage(imageName);
			map = new Map(mapRequest,Constants.getUserId());
			status = mapDao.add(Constants.mINDEX, Constants.mTYPE, map);
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

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public int updateMap(MapRequest mapRequest) {
		int status = 0;
		System.out.println(mapRequest.toString());



		Map map;
		if (mapRequest != null) {
			String imageName = Constants.getCurrentUser().getUsername()+mapRequest.getName()+Constants.getCurrentDateTime("yyyyMMddhhmmss");
			mapRequest.saveImage(imageName);
			map = new Map(mapRequest,Constants.getUserId());
			status = mapDao.add(Constants.mINDEX, Constants.mTYPE, map);
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

	@DELETE
	@Path("/{id_map}")
	public int deleteMap(@PathParam("id_map") String id_map) {
		int status = -1;
		// TODO get current map if exist
		Map map = null;
		if(map != null){
			status = mapDao.delete(Constants.mINDEX, map);
		}

		return status;
	}
}