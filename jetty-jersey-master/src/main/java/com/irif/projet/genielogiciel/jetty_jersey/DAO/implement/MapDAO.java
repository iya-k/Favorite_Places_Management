package com.irif.projet.genielogiciel.jetty_jersey.DAO.implement;

import java.io.IOException;
import java.util.List;

import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.irif.projet.genielogiciel.jetty_jersey.DAO.DAO;
import com.irif.projet.genielogiciel.jetty_jersey.model.Map;
import com.irif.projet.genielogiciel.jetty_jersey.model.User;

public class MapDAO extends DAO<Map>{

	public MapDAO(TransportClient client) {
		super(client);
	}

	@Override
	public boolean create(String index, String type, Map map) throws JsonProcessingException {
		byte[] json = this.mapper.writeValueAsBytes(map);
		IndexRequestBuilder indexRequest = client.prepareIndex(index,type)
                                                 .setId(Integer.toString(map.getId()))// static dans place
                                                 .setSource(json,XContentType.JSON);
		return (indexRequest.execute().actionGet().getId().equals(Integer.toString(map.getId())));
	}

	@Override
	public boolean delete(String index, String type, Map map) {
		return false;
	}

	@Override
	public boolean update(String index, String type, Map map) {
		return false;
	}
	@Override
	public Map find(String index, String type, Map map) throws JsonParseException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map> findAll(String index, String type, int id)
			throws JsonParseException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
