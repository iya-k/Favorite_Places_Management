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
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.MultiMatchQueryBuilder.Type;

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
		Picture picture = (Picture)pi;

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
	public Picture find(String index, String type, Class<Picture> t, String userid) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Picture> findAllById(String index, String type, String query, Class<Picture> t) {
		// TODO Auto-generated method stub
		return null;
	}
}
