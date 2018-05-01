package com.irif.projet.genielogiciel.jetty_jersey.DAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.search.SearchHit;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.irif.projet.genielogiciel.jetty_jersey.DAO.implement.UserDAO;
import com.irif.projet.genielogiciel.jetty_jersey.model.User;

public abstract class DAO<T>{
	  protected TransportClient client;
	  protected ObjectMapper mapper;


	  protected DAO(){}


	  public DAO(TransportClient client){
		  this.client = client;
		  this.mapper = new ObjectMapper();
	  }
	  
	  /**
	   * 
	   * @param hit : ElesticSearch hit
	   * @param t : The object's class
	   * @return  the hit transformed to the corresponding class object and return it
	   */
	  public T getObj(SearchHit hit,Class<T> t){
		  T obj = null;
		  try{
			  obj = mapper.readValue(hit.getSourceAsString().getBytes(),t);
		  }catch (IOException e){
			  //e.printStackTrace();
		  }
		  return obj;
	  }
	  
	  /**
	   * 
	   * @param index : index of the database
	   * @param type : type of the database
	   * @param query : the query to execute
	   * @return the object T corresponding to the query
	   */
	  public abstract T find(String index,String type,String query);

	  /**
	   * 
	   * @param index : index of the database
	   * @param type : type of the database
	   * @param query : the query to execute
	   * @return SearchResponse corresponding to the query
	   */
	  public abstract SearchResponse getSearchResponse(String index, String type, String query);

	  /**
	   * 
	   * @param index : index of the database
	   * @param type : type of the database
	   * @param obj : The object that allows to verify its presence on the database
	   * @return true if the object exist on database else false
	   */
	  public abstract boolean exist(String index,String type,T obj);

	  /**
	   * 
	   * @param index : index of the database
	   * @param type : type of the database
	   * @param obj : The object for which we want to get the id
	   * @return : id of the object on database
	   */
	  public abstract String getId(String index, String type, T obj);
	 
	  /**
	   * 
	   * @param index : index of the database
	   * @param obj : The object that we want to delete fromdatabase
	   * @return  1:the object has been delected  != 0 else
	   */
	  public abstract int delete(String index,T obj);


	  /**
	   * 
	   * @param index : index of the database
	   * @param type : type of the database
	   * @param query : the query to execute
	   * @return list of elements corresponding to the query
	   */
	  public abstract List<T> findAllById(String index,String type,String query);
	  
	  /**
	   * 
	   * @param index : index of the database
	   * @param type : type of the database
	   * @param obj :The object that we want to add on database
	   * @return 1: if the object was added -1: if an error has occured 0 else
	   */
	  public int add(String index,String type,T obj){
			int res = 0;
			try{
				if(!exist(index,type,obj)){
					byte[] json = this.mapper.writeValueAsBytes(obj);
					IndexRequestBuilder indexRequest = client.prepareIndex(index,type)
							                                 .setSource(json,XContentType.JSON);
					indexRequest.execute().actionGet();
					client.admin().indices().prepareRefresh(index).get();
					res = 1;
				}
			}catch(JsonProcessingException e){
				res = -1;
				//e.printStackTrace();
			}
			return res;
	  }
     
	  /**
	   * 
	   * @param index : index of the database
	   * @param type : type of the database
	   * @param obj :The object that we want to modify on database
	   * @return 1: if the object was added -1: if an error has occured 0: else
	   */
	  public int update(String index,String type,T obj){
		 int status = 0;
		 try {
		  	byte[] json = this.mapper.writeValueAsBytes(obj);
		  	UpdateRequest updateRequest = new UpdateRequest();
		  	updateRequest.index(index);
		  	updateRequest.type(type);
		  	updateRequest.id(getId(index,type,obj));
		  	updateRequest.doc(json,XContentType.JSON);
		  	client.update(updateRequest).get();
		  	client.admin().indices().prepareRefresh(index).get();
			status = 1;
		}catch (InterruptedException | ExecutionException | IOException e){
			e.printStackTrace();
			status = -1;
		}
			return status;
	  }

	  /**
	   * 
	   * @param index : index of the database that we want to delete
	   * @return true if the index was deleted false else
	   */
	  public boolean deleteIndex(String index){

			BulkByScrollResponse response = DeleteByQueryAction.INSTANCE.newRequestBuilder(client)
				                            .filter(QueryBuilders.matchAllQuery())
				                            .source(index)
				                            .get();
			client.admin().indices().prepareRefresh(index).get();
			return (0 < response.getDeleted());
	  }
	  /**
	   *

	   * @param id if id=0 no matching with id else matching with id
	   * @return T list corresponding to the id
	   * @throws JsonParseException
	   * @throws JsonMappingException
	   * @throws IOException
	   */
	  
	  /**
	   * 
	   * @param index : index of the database
	   * @param type : type of the database
	   * @param t : the class of the object that we want 
	   * @return list of elements corresponding to the index
	   */
	  public List<T> findAll(String index,String type,Class<T> t){
			 List<T> res = new ArrayList<T>();
			 SearchResponse response = client.prepareSearch(index)
		                                     .setTypes(type)
		                                     .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
		                                     .get();
			 SearchHit[] searchHit = response.getHits().getHits();
			 for(int i = 0; i < searchHit.length;i++) {
				 res.add(getObj(searchHit[i],t));
			 }
			return res;
	  }

	}
