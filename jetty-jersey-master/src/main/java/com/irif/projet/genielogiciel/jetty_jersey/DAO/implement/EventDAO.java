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
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.irif.projet.genielogiciel.jetty_jersey.DAO.DAO;
import com.irif.projet.genielogiciel.jetty_jersey.model.Event;
import com.irif.projet.genielogiciel.jetty_jersey.model.Place;
import com.irif.projet.genielogiciel.jetty_jersey.model.User;

public class EventDAO extends DAO<Event>{

	public EventDAO(TransportClient client) {
		super(client);
	}
	
	@Override
	public boolean exist(String index, String type,Event e) {
		Event event = (Event)e;
		SearchResponse response = client.prepareSearch(index)
                .setTypes(type)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.matchQuery("name",event.getName()))
                .get();
		return (0<response.getHits().getHits().length);
	}
	
	
	@Override
	public int delete(String index, Event event) {
		//DeleteResponse response = client.prepareDelete(index,type,Integer.toString(event.getId())).get();
		return 0;
	}

	@Override
	public String getId(String index, String type, Event obj) {
		// TODO Auto-generated method stub
		return null;
	}
}
