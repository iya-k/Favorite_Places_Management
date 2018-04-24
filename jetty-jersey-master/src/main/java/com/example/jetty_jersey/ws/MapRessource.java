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
		String mapname = "NJdv92IBe9pBoNiC9K3Ymap";
		Map map = mapDao.find("homemapdb","homemap",mapname);
		System.out.println("Map trouve : "+map);
		for (int i = 0; i < map.getPlaceList().size(); i++) {
			System.out.println("place "+map.getPlaceList().get(i));
		}
		for (int i = 0; i < map.getEventList().size(); i++) {
			System.out.println("place "+map.getEventList().get(i));
		}
		if (q != "") {
			status = mapDao.find(Constants.mINDEX, Constants.mTYPE, q);
		}
		return status;
	}
	@Path("/list")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Map> getMaps(@PathParam("q")String q) {
		List<Map> status = null;
		String username = "NJdv92IBe9pBoNiC9K3Ymap";
		Map map = mapDao.find("homemapdb","homemap",username);
		System.out.println("Map trouve : "+map);
		for (int i = 0; i < map.getPlaceList().size(); i++) {
			System.out.println("place "+map.getPlaceList().get(i));
		}
		for (int i = 0; i < map.getEventList().size(); i++) {
			System.out.println("event "+map.getEventList().get(i));
		}
		return status;
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