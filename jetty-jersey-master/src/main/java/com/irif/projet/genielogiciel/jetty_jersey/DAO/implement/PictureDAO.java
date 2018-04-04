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
import com.irif.projet.genielogiciel.jetty_jersey.model.Picture;
import com.irif.projet.genielogiciel.jetty_jersey.model.Place;
import com.irif.projet.genielogiciel.jetty_jersey.model.User;

public class PictureDAO extends DAO<Picture>{

	public PictureDAO(TransportClient client) {
		super(client);
	}

	@Override
	public boolean create(String index, String type, Picture picture) throws JsonProcessingException {
		byte[] json = this.mapper.writeValueAsBytes(picture);
		IndexRequestBuilder indexRequest = client.prepareIndex(index,type)
                                                 .setId(Integer.toString(picture.getPictureid()))
                                                 .setSource(json,XContentType.JSON);
		return (indexRequest.execute().actionGet().getId().equals(Integer.toString(picture.getPictureid())));
	}

	@Override
	public boolean delete(String index, String type, Picture picture) {
		DeleteResponse response = client.prepareDelete(index,type,Integer.toString(picture.getPictureid())).get();
		return false;
	}

	@Override
	public boolean update(String index, String type, Picture picture) throws JsonProcessingException, InterruptedException, ExecutionException {
		UpdateRequest updateRequest = new UpdateRequest();
		updateRequest.index(index);
		updateRequest.type(type);
		updateRequest.id(Integer.toString(picture.getPictureid()));
		byte[] json = this.mapper.writeValueAsBytes(picture);
		updateRequest.doc(json,XContentType.JSON);
		client.update(updateRequest).get();
		return false;
	}
	@Override
	public Picture find(String index, String type, Picture picture)
			throws JsonParseException, JsonMappingException, IOException {
		GetResponse response = client.prepareGet(index,type,Integer.toString(picture.getPictureid())).get();
		return (mapper.readValue(response.getSourceAsBytes(),Picture.class));
	}
	@Override
	public List<Picture> findAll(String index, String type, int id)
			throws JsonParseException, JsonMappingException, IOException {
		MultiGetResponse multiGetItemResponses;
		MultiGetRequestBuilder builder = client.prepareMultiGet();
		List<Picture> picureList = new ArrayList<Picture>();
		if(id==0){
			for(int i =1; i <= Picture.getCpt();i++){
				builder.add(index,type,Integer.toString(i));
			}
		}else{
			builder.add(index,"placeid",Integer.toString(id));
		}
		multiGetItemResponses = builder.get();
		for (MultiGetItemResponse itemResponse : multiGetItemResponses) { 
		    GetResponse response = itemResponse.getResponse();
		    picureList.add(mapper.readValue(response.getSourceAsBytes(),Picture.class));
		}
		return picureList;
	}
}
