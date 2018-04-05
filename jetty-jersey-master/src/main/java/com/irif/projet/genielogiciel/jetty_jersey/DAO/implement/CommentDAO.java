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
import com.irif.projet.genielogiciel.jetty_jersey.model.Comment;
import com.irif.projet.genielogiciel.jetty_jersey.model.Picture;
import com.irif.projet.genielogiciel.jetty_jersey.model.User;

public class CommentDAO extends DAO<Comment>{

	public CommentDAO(TransportClient client) {
		super(client);
	}

	@Override
	public boolean create(String index, String type, Comment comment) throws JsonProcessingException {
		byte[] json = this.mapper.writeValueAsBytes(comment);
		IndexRequestBuilder indexRequest = client.prepareIndex(index,type)
                                                 .setId(Integer.toString(comment.getCommentid()))
                                                 .setSource(json,XContentType.JSON);
		return (indexRequest.execute().actionGet().getId().equals(Integer.toString(comment.getCommentid())));
	}

	@Override
	public boolean delete(String index, String type, Comment comment) {
		DeleteResponse response = client.prepareDelete(index,type,Integer.toString(comment.getCommentid())).get();
		return false;
	}

	@Override
	public boolean update(String index, String type, Comment comment) throws JsonProcessingException, InterruptedException, ExecutionException {
		UpdateRequest updateRequest = new UpdateRequest();
		updateRequest.index(index);
		updateRequest.type(type);
		updateRequest.id(Integer.toString(comment.getCommentid()));
		byte[] json = this.mapper.writeValueAsBytes(comment);
		updateRequest.doc(json,XContentType.JSON);
		client.update(updateRequest).get();
		return false;
	}

	@Override
	public Comment find(String index, String type, Comment comment)
			throws JsonParseException, JsonMappingException, IOException {
		GetResponse response = client.prepareGet(index,type,Integer.toString(comment.getCommentid())).get();
		return (mapper.readValue(response.getSourceAsBytes(),Comment.class));
	}

	@Override
	public List<Comment> findAll(String index, String type, int id)
			throws JsonParseException, JsonMappingException, IOException {
		MultiGetResponse multiGetItemResponses;
		MultiGetRequestBuilder builder = client.prepareMultiGet();
		List<Comment> commentList = new ArrayList<Comment>();
		if(id==0){
			for(int i =1; i <= Comment.getCpt();i++){
				builder.add(index,type,Integer.toString(i));
			}
		}else{
			builder.add(index,"placeid",Integer.toString(id));
		}
		multiGetItemResponses = builder.get();
		for (MultiGetItemResponse itemResponse : multiGetItemResponses) { 
		    GetResponse response = itemResponse.getResponse();
		    commentList.add(mapper.readValue(response.getSourceAsBytes(),Comment.class));
		}
		return commentList;
	}
}
