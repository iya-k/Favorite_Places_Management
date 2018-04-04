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
import com.irif.projet.genielogiciel.jetty_jersey.model.Place;
import com.irif.projet.genielogiciel.jetty_jersey.model.User;

public class PlaceDAO extends DAO<Place>{
	
	public PlaceDAO(TransportClient client) {
		super(client);
	}
	@Override
	public boolean create(String index, String type, Place place) throws JsonProcessingException {
		byte[] json = this.mapper.writeValueAsBytes(place);
		IndexRequestBuilder indexRequest = client.prepareIndex(index,type)
                                                 .setId(Integer.toString(place.getId()))// static dans place
                                                 .setSource(json,XContentType.JSON);
		return (indexRequest.execute().actionGet().getId().equals(Integer.toString(place.getId())));
	}

	@Override
	public boolean delete(String index, String type, Place place) {
		DeleteResponse response = client.prepareDelete(index,type,Integer.toString(place.getId())).get();
		// delete selon condition et id
		return false;
	}

	@Override
	public boolean update(String index, String type, Place place) throws JsonProcessingException, InterruptedException, ExecutionException{
		UpdateRequest updateRequest = new UpdateRequest();
		updateRequest.index(index);
		updateRequest.type(type);
		updateRequest.id(Integer.toString(place.getId()));
		byte[] json = this.mapper.writeValueAsBytes(place);
		updateRequest.doc(json,XContentType.JSON);
		client.update(updateRequest).get();
		return false;
	}
	@Override
	public Place find(String index, String type, Place place)
			throws JsonParseException, JsonMappingException, IOException {
		GetResponse response = client.prepareGet(index,type,Integer.toString(place.getId())).get();
		return (mapper.readValue(response.getSourceAsBytes(),Place.class));
	}
	@Override
	public List<Place> findAll(String index, String type,int id)throws JsonParseException, JsonMappingException, IOException{
		MultiGetResponse multiGetItemResponses;
		MultiGetRequestBuilder builder = client.prepareMultiGet();
		List<Place> placeList = new ArrayList<Place>();
		if(id==0){
			for(int i =1; i <= Place.getCpt();i++){
				builder.add(index,type,Integer.toString(i));
			}
		}else{
			builder.add(index,"mapId",Integer.toString(id));
		}
		
		// verifier si correspond a mapID donne en param
		multiGetItemResponses = builder.get();
		for (MultiGetItemResponse itemResponse : multiGetItemResponses) { 
		    GetResponse response = itemResponse.getResponse();
		    placeList.add(mapper.readValue(response.getSourceAsBytes(),Place.class));
		    /*if (response.isExists()) {                      
		        String json = response.getSourceAsString(); 
		    }*/
		}
		return placeList;
	}
}
