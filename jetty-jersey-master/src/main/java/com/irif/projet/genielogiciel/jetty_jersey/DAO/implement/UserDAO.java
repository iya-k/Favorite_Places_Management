package com.irif.projet.genielogiciel.jetty_jersey.DAO.implement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.irif.projet.genielogiciel.jetty_jersey.DAO.DAO;
import com.irif.projet.genielogiciel.jetty_jersey.model.User;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetRequestBuilder;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.node.Node;
import org.elasticsearch.search.SearchHit;

public class UserDAO extends DAO<User>{

	
	public UserDAO(TransportClient client) {
		super(client);
	}
	
	@Override
	public boolean create(String index,String type,User user) throws JsonProcessingException{
		byte[] json = this.mapper.writeValueAsBytes(user);
		IndexRequestBuilder indexRequest = client.prepareIndex(index,type)
                                                 .setId(Integer.toString(user.getId()))
                                                 .setSource(json,XContentType.JSON);
		return (indexRequest.execute().actionGet().getId().equals(Integer.toString(user.getId())));
	}

	@Override
	public boolean delete(String index,String type,User user){
		DeleteResponse response = client.prepareDelete(index,type,Integer.toString(user.getId())).get();
		return false;
	}
	@Override
	public boolean update(String index,String type,User user) throws JsonProcessingException, InterruptedException, ExecutionException{
		UpdateRequest updateRequest = new UpdateRequest();
		updateRequest.index(index);
		updateRequest.type(type);
		updateRequest.id(Integer.toString(user.getId()));
		byte[] json = this.mapper.writeValueAsBytes(user);
		updateRequest.doc(json,XContentType.JSON);
		client.update(updateRequest).get();
		return false;
	}
	@Override
	public User find(String index, String type,User user) throws JsonParseException, JsonMappingException, IOException{
		GetResponse response = client.prepareGet(index,type,Integer.toString(user.getId())).get();
		return (mapper.readValue(response.getSourceAsBytes(),User.class));
	}
	
	
	@Override
	public List<User> findAll(String index,String type) throws JsonParseException, JsonMappingException, IOException{
		MultiGetResponse multiGetItemResponses;
		MultiGetRequestBuilder builder = client.prepareMultiGet();
		List<User> usersList = new ArrayList<User>();
		
		for(int i =1; i <= User.getCpt();i++){
			builder.add(index,type,Integer.toString(i));
		}
		
		multiGetItemResponses = builder.get();
		for (MultiGetItemResponse itemResponse : multiGetItemResponses) { 
		    GetResponse response = itemResponse.getResponse();
		    usersList.add(mapper.readValue(response.getSourceAsBytes(),User.class));
		    /*if (response.isExists()) {                      
		        String json = response.getSourceAsString(); 
		    }*/
		}
		return usersList;
	}	
}
