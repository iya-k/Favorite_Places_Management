package com.irif.projet.genielogiciel.jetty_jersey.DAO;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Setting;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import com.irif.projet.genielogiciel.jetty_jersey.DAO.implement.*;

public class DAOFactory extends AbstractDAOFactory{

	
	private static TransportClient connect;
	
	//private TransportClient client;
	private static UserDAO userDAO;
	private static MapDAO mapDAO;
	private static PlaceDAO placeDAO;
	private static EventDAO eventDAO;
	private static CommentDAO commentDAO;
	private static PictureDAO pictureDAO;
	
	
	public void initClient() {
		
		try {

			connect = new PreBuiltTransportClient(Settings.EMPTY)
						.addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300))
				        .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9301));
				
		}	catch (UnknownHostException e) {
			System.out.println(e);
		}
		
		
	}
	
	public DAO getUserDAO() {
		if(userDAO == null) {
			userDAO = new UserDAO();
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
