package com.irif.projet.genielogiciel.jetty_jersey.DAO.implement;

import java.io.IOException;
import java.util.List;

import org.elasticsearch.client.transport.TransportClient;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.irif.projet.genielogiciel.jetty_jersey.DAO.DAO;
import com.irif.projet.genielogiciel.jetty_jersey.model.User;

public class EventDAO extends DAO<EventDAO>{

	public EventDAO(TransportClient client) {
		super(client);
	}

	@Override
	public boolean create(String index, String type, EventDAO obj) throws JsonProcessingException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String index, String type, EventDAO obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(String index, String type, EventDAO obj) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public EventDAO find(String index, String type, EventDAO obj)
			throws JsonParseException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<EventDAO> findAll(String index, String type) throws JsonParseException, JsonMappingException, IOException{
		// TODO Auto-generated method stub
		return null;
	}
}
