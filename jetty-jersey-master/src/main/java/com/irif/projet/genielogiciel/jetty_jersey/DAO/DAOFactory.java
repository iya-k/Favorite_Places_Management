package com.irif.projet.genielogiciel.jetty_jersey.DAO;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import com.irif.projet.genielogiciel.jetty_jersey.DAO.implement.*;

public class DAOFactory extends AbstractDAOFactory{

	
	private static TransportClient client;
	
	//private TransportClient client;
	private static UserDAO userDAO;
	private static MapDAO mapDAO;
	private static PlaceDAO placeDAO;
	private static EventDAO eventDAO;
	private static CommentDAO commentDAO;
	private static PictureDAO pictureDAO;
	
	
	public void initClient() {
		
		try {
			client = new PreBuiltTransportClient(Settings.EMPTY)
						.addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300))
				        .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9301));
				
			try {
				IndexResponse response = client.prepareIndex("twitter", "tweet", "1")
				        .setSource(XContentFactory.jsonBuilder()
				                    .startObject()
				                        .field("user", "kimchy")
				                        .field("postDate", new Date())
				                        .field("message", "trying out Elasticsearch")
				                    .endObject()
				                  )
				        .execute()
				        .actionGet();
				System.out.println("done");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	catch (UnknownHostException e) {
			System.out.println(e);
		}
		
		
	}
	
	@Override
	public DAO getUserDAO() {
		if(userDAO == null) {
			userDAO = new UserDAO();
		}
		return userDAO;
	}
	
	@Override
	public DAO getMapDAO() {
		if(mapDAO == null) {
			mapDAO = new MapDAO(client);
		}
		return mapDAO;
	}
	
	@Override
	public DAO getPlaceDAO() {
		if(placeDAO == null) {
			placeDAO = new PlaceDAO(client);
		}
		return placeDAO;
	}
	@Override
	public DAO getEventDAO() {
		if(eventDAO == null) {
			eventDAO = new EventDAO(client);
		}
		return eventDAO;
	}
	
	
	@Override
	public DAO getCommentDAO() {
		if(commentDAO == null) {
			commentDAO = new CommentDAO(client);
		}
		return commentDAO;
	}
	
	@Override
	public DAO getPictureDAO() {
		if(pictureDAO == null) {
			pictureDAO = new PictureDAO(client);
		}
		return pictureDAO;
	}
	
	
	
}
