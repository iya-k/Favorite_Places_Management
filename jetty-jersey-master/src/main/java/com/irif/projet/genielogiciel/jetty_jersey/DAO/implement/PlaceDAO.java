package com.irif.projet.genielogiciel.jetty_jersey.DAO.implement;

import java.util.List;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.MultiMatchQueryBuilder.Type;

import com.irif.projet.genielogiciel.jetty_jersey.DAO.DAO;
import com.irif.projet.genielogiciel.jetty_jersey.model.Place;

public class PlaceDAO extends DAO<Place>{


	public PlaceDAO(TransportClient client) {
		super(client);
	}
	
	@Override
	public SearchResponse getSearchResponse(String index, String type, Place place){
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
	public boolean exist(String index, String type, Place p) {
		// TODO Auto-generated method stub
		Place place = p;

		SearchResponse response = client.prepareSearch(index)
				.setTypes(type)
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(QueryBuilders.matchQuery("name",place.getName()))
				.get();
		return (0 < response.getHits().getHits().length);
	}

	@Override
	public int delete(String index, Place place) {
		//DeleteResponse response = client.prepareDelete(index,type,Integer.toString(place.getId())).get();
		return 0;
	}

	@Override
	public String getId(String index, String type, Place obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Place find(String index, String type, Class<Place> t, String id, Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Place> findAllById(String index, String type, String id, String query, Class<Place> t) {
		// TODO Auto-generated method stub
		return null;
	}
}
