package com.irif.projet.genielogiciel.jetty_jersey.DAO.implement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetRequestBuilder;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.irif.projet.genielogiciel.jetty_jersey.DAO.DAO;
import com.irif.projet.genielogiciel.jetty_jersey.model.Map;
import com.irif.projet.genielogiciel.jetty_jersey.model.Place;
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
		DeleteResponse response = client.prepareDelete(index,type,Integer.toString(map.getId())).get();
		return false;
	}

	@Override
	public boolean update(String index, String type, Map map) throws JsonProcessingException, InterruptedException, ExecutionException {
		UpdateRequest updateRequest = new UpdateRequest();
		updateRequest.index(index);
		updateRequest.type(type);
		updateRequest.id(Integer.toString(map.getId()));
		byte[] json = this.mapper.writeValueAsBytes(map);
		updateRequest.doc(json,XContentType.JSON);
		client.update(updateRequest).get();
		return false;
	}
	@Override
	public Map find(String index, String type, Map map) throws JsonParseException, JsonMappingException, IOException {
		GetResponse response = client.prepareGet(index,type,Integer.toString(map.getId())).get();
		return (mapper.readValue(response.getSourceAsBytes(),Map.class));
	}

	@Override
	public List<Map> findAll(String index, String type, int id)
			throws JsonParseException, JsonMappingException, IOException {
		MultiGetResponse multiGetItemResponses;
		MultiGetRequestBuilder builder = client.prepareMultiGet();
		List<Map> mapList = new ArrayList<Map>();
		if(id==0){
			for(int i =1; i <= Map.getCpt();i++){
				builder.add(index,type,Integer.toString(i));
			}
		}else{
			builder.add(index,"userid",Integer.toString(id));
			builder.add(index, "privacy", "public"); //on ajoute des map avec privacy public
		}
		multiGetItemResponses = builder.get();
		for (MultiGetItemResponse itemResponse : multiGetItemResponses) { 
		    GetResponse response = itemResponse.getResponse();
		    mapList.add(mapper.readValue(response.getSourceAsBytes(),Map.class));
		}
		return mapList;
	}

}
