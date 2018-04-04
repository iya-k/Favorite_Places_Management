package com.irif.projet.genielogiciel.jetty_jersey.DAO;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.elasticsearch.client.transport.TransportClient;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class DAO<T>{
	  protected TransportClient client;
	  protected ObjectMapper mapper;
	  
	  
	  protected DAO(){}

	  
	  public DAO(TransportClient client){
	    this.client = client;
	    this.mapper = new ObjectMapper();
	  }
	  
	  /**
	  * creation method
	  * @param obj
	  * @return boolean if created true else false
	  */
	  public abstract boolean create(String index,String type,T obj) throws JsonProcessingException;

	  /**
	  * delete method
	  * @param obj
	  * @return boolean if deleted true else false
	  */
	  public abstract boolean delete(String index,String type,T obj);

	  /**
	  * update method
	  * @param obj
	  * @return boolean if updated true else false
	  */
	  public abstract boolean update(String index,String type,T obj) throws JsonProcessingException, InterruptedException, ExecutionException;

	  
	  public abstract T find(String index, String type,T obj) throws JsonParseException, JsonMappingException, IOException;
	  /**
	   * 
	   * @param index
	   * @param type
	   * @param id if id=0 no matching with id else matching with id
	   * @return T list corresponding to the id
	   * @throws JsonParseException
	   * @throws JsonMappingException
	   * @throws IOException
	   */
	  public abstract List<T> findAll(String index,String type,int id)throws JsonParseException, JsonMappingException, IOException;
	 
	  
	  
	}