package com.irif.projet.genielogiciel.jetty_jersey.DAO;

import java.util.List;

import org.elasticsearch.client.transport.TransportClient;

public abstract class DAO<T> {
	  protected TransportClient connect = null;
	  
	  protected DAO(){}

	  
	  public DAO(TransportClient connect){
	    this.connect = connect;
	  }
	   
	  /**
	  * creation method
	  * @param obj
	  * @return boolean if created true else false
	  */
	  public abstract boolean create(T obj);

	  /**
	  * delete method
	  * @param obj
	  * @return boolean if deleted true else false
	  */
	  public abstract boolean delete(T obj);

	  /**
	  * update method
	  * @param obj
	  * @return boolean if updated true else false
	  */
	  public abstract boolean update(T obj);

	  /**
	  * find method
	  * @param id
	  * @return T list corresponding to the id
	  */
	  public abstract List<T> find(int id);
	}