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
import com.irif.projet.genielogiciel.jetty_jersey.model.Map;
import com.irif.projet.genielogiciel.jetty_jersey.model.Place;
import com.irif.projet.genielogiciel.jetty_jersey.model.User;

public class MapDAO extends DAO<Map>{

	public MapDAO(TransportClient client) {
		super(client);
	}
	@Override
	public boolean exist(String index, String type, Map m){
		/*Map map = (Map)m;
		SearchResponse response = client.prepareSearch(index)
                .setTypes(type)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.matchQuery("mapName",map.getMapName()))
                .get();
		return (0<response.getHits().getHits().length);*/
		return false;
	}
	

	@Override
	public int delete(String index, Map map) {
		//DeleteResponse response = client.prepareDelete(index,type,Integer.toString(map.getId())).get();
		return 0;
	}
	@Override
	public String getId(String index, String type, Map obj) {
		// TODO Auto-generated method stub
		return null;
	}
}
