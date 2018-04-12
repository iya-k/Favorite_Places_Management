package com.irif.projet.genielogiciel.jetty_jersey.DAO.implement;

import java.util.List;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.MultiMatchQueryBuilder.Type;

import com.irif.projet.genielogiciel.jetty_jersey.DAO.DAO;
import com.irif.projet.genielogiciel.jetty_jersey.model.Picture;

public class PictureDAO extends DAO<Picture>{

	public PictureDAO(TransportClient client) {
		super(client);
	}
	@Override
	public SearchResponse getSearchResponse(String index, String type, Picture picture) {
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
	public boolean exist(String index, String type, Picture pi) {
		Picture picture = pi;
		
		SearchResponse response = client.prepareSearch(index)
                .setTypes(type)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.matchQuery("picturename",picture.getPicturename()))
                .get();
		return (0 < response.getHits().getHits().length);
	}
	
	@Override
	public int delete(String index, Picture picture) {
		//DeleteResponse response = client.prepareDelete(index,type,Integer.toString(picture.getPictureid())).get();
		return 0;
	}
	@Override
	public String getId(String index, String type, Picture obj) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Picture find(String index, String type, Class<Picture> t, String id, Object obj) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Picture> findAllById(String index, String type, String id, String query, Class<Picture> t) {
		// TODO Auto-generated method stub
		return null;
	}
}
