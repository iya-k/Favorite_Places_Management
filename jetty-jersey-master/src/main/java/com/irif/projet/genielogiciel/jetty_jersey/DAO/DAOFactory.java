package com.irif.projet.genielogiciel.jetty_jersey.DAO;

import com.irif.projet.genielogiciel.jetty_jersey.DAO.implement.*;

public class DAOFactory extends AbstractDAOFactory{
	private static  Object connect;// a voir
	
	private static UserDAO userDAO;
	private static MapDAO mapDAO;
	private static PlaceDAO placeDAO;
	private static EventDAO eventDAO;
	private static CommentDAO commentDAO;
	private static PictureDAO pictureDAO;
	
	
	public DAO getUserDAO() {
		if(userDAO == null) {
			userDAO = new UserDAO(connect);
		}
		return userDAO;
	}
	
	public DAO getMapDAO() {
		if(mapDAO == null) {
			mapDAO = new MapDAO(connect);
		}
		return mapDAO;
	}
	
	public DAO getPlaceDAO() {
		if(placeDAO == null) {
			placeDAO = new PlaceDAO(connect);
		}
		return placeDAO;
	}
	public DAO getEventDAO() {
		if(eventDAO == null) {
			eventDAO = new EventDAO(connect);
		}
		return eventDAO;
	}
	
	
	public DAO getCommentDAO() {
		if(commentDAO == null) {
			commentDAO = new CommentDAO(connect);
		}
		return commentDAO;
	}
	
	public DAO getPictureDAO() {
		if(pictureDAO == null) {
			pictureDAO = new PictureDAO(connect);
		}
		return pictureDAO;
	}
	
	
	
}
