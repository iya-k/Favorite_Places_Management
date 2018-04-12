package com.irif.projet.genielogiciel.jetty_jersey.DAO.implement;

import java.util.List;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.MultiMatchQueryBuilder.Type;

import com.irif.projet.genielogiciel.jetty_jersey.DAO.DAO;
import com.irif.projet.genielogiciel.jetty_jersey.model.Event;

public class EventDAO extends DAO<Event>{

	public EventDAO(TransportClient client) {
		super(client);
	}
	@Override
	public SearchResponse getSearchResponse(String index, String type, Event obj) {
		String query = " "; 

		SearchResponse response = client.prepareSearch(index)
				.setTypes(type)
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(QueryBuilders.multiMatchQuery(query,"placeid","mapid")
						.type(Type.CROSS_FIELDS)
						.operator(Operator.AND)).get();
		return response;
	}
	
	@Override
	public boolean exist(String index, String type,Event e) {
		Event event = e;
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
	@Override
	public Event find(String index, String type, Class<Event> t, String id, Object obj) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Event> findAllById(String index, String type, String id, String query, Class<Event> t) {
		// TODO Auto-generated method stub
		return null;
	}
}
