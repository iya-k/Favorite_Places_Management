package com.irif.projet.genielogiciel.jetty_jersey.DAO.implement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.irif.projet.genielogiciel.jetty_jersey.DAO.DAO;
import com.irif.projet.genielogiciel.jetty_jersey.model.User;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.node.Node;

public class UserDAO extends DAO<User>{

	
	public UserDAO() {
		super();
	}

	@Override
	public boolean create(User obj) {
		
	      Map<String, String> jsonDocument = new HashMap<String, String>();
	        jsonDocument.put("username", "name");
	        jsonDocument.put("password", "whatever");
	        jsonDocument.put("email", "maybe");
		
		if (jsonDocument != null){
			CreateIndexRequestBuilder createIndexRequestBuilder = this.connect.admin().indices().prepareCreate("Whatever");
			
			createIndexRequestBuilder.execute().actionGet();
			
			//IndexRequestBuilder indexRequestBuilder = this.connect.prepareIndex(indexName, documentType, documentId);
			//indexRequestBuilder.setSource(jsonDocument);
			
		} else {
			return false;		
		}
	}

	@Override
	public boolean delete(User obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(User obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<User> find(int id) {
		// TODO Auto-generated method stub
		return null;
	}



	
}
