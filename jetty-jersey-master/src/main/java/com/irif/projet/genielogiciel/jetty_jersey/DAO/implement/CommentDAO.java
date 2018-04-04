package com.irif.projet.genielogiciel.jetty_jersey.DAO.implement;

import java.io.IOException;
import java.util.List;

import org.elasticsearch.client.transport.TransportClient;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.irif.projet.genielogiciel.jetty_jersey.DAO.DAO;
import com.irif.projet.genielogiciel.jetty_jersey.model.Comment;
import com.irif.projet.genielogiciel.jetty_jersey.model.User;

public class CommentDAO extends DAO<Comment>{

	public CommentDAO(TransportClient client) {
		super(client);
	}

	@Override
	public boolean create(String index, String type, Comment obj) throws JsonProcessingException {
		return false;
	}

	@Override
	public boolean delete(String index, String type, Comment obj) {
		return false;
	}

	@Override
	public boolean update(String index, String type, Comment obj) {
		return false;
	}

	@Override
	public Comment find(String index, String type, Comment obj)
			throws JsonParseException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> findAll(String index, String type, int id)
			throws JsonParseException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
}
