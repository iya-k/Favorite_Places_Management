package com.irif.projet.genielogiciel.jetty_jersey.DAO;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;

import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Setting;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import com.example.jetty_jersey.dao.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.irif.projet.genielogiciel.jetty_jersey.DAO.implement.*;

public class DAOFactory extends AbstractDAOFactory{
	private final String IP = "127.0.0.1";
	private final int PORT = 9300;
	private TransportClient client;
	private static UserDAO userDAO;
	private static MapDAO mapDAO;
	private static PlaceDAO placeDAO;
	private static EventDAO eventDAO;
	private static CommentDAO commentDAO;
	private static PictureDAO pictureDAO;
	
	
	public void initClient() throws UnknownHostException {
			client = new PreBuiltTransportClient(Settings.EMPTY)
					.addTransportAddress(new TransportAddress(InetAddress.getByName(IP),PORT));
	}
	
	public DAO getUserDAO() {
		if(userDAO == null) {
			userDAO = new UserDAO(client);
		}
		return userDAO;
	}
	
	public DAO getMapDAO() {
		if(mapDAO == null) {
			mapDAO = new MapDAO(client);
		}
		return mapDAO;
	}
	
	public DAO getPlaceDAO() {
		if(placeDAO == null) {
			placeDAO = new PlaceDAO(client);
		}
		return placeDAO;
	}
	public DAO getEventDAO() {
		if(eventDAO == null) {
			eventDAO = new EventDAO(client);
		}
		return eventDAO;
	}
	
	
	public DAO getCommentDAO() {
		if(commentDAO == null) {
			commentDAO = new CommentDAO(client);
		}
		return commentDAO;
	}
	
	public DAO getPictureDAO() {
		if(pictureDAO == null) {
			pictureDAO = new PictureDAO(client);
		}
		return pictureDAO;
	}
}
