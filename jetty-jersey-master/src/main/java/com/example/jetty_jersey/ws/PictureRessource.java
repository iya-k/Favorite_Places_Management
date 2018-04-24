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
import com.irif.projet.genielogiciel.jetty_jersey.model.Picture;

public class PictureRessource {
	
	AbstractDAOFactory daoFact = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
    DAO<Picture> picDao = daoFact.getPictureDAO();
    
  //list of picture of map
    @Path("/"+Constants.PICTURES)
    @GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Picture> getPictures(@PathParam("q")String q) {
    	List<Picture> retour = null;
			retour = picDao.findAllById(Constants.piINDEX, Constants.piTYPE, q);
		return retour;
	}
    
    @PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public int addPicture(Picture pic){
			return picDao.add(Constants.piINDEX, Constants.piTYPE,pic);
	}
    
    @DELETE
	@Path("/{id_picture}")
	public boolean deletePicture(@PathParam("index")String index){
		return picDao.deleteIndex(index);
	}
    
    @POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/test")
	public void retrievePlace() {
		System.out.println(Constants.PICTURES);
    }

}
