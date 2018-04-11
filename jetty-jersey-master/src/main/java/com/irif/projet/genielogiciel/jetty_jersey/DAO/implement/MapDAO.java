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
import org.elasticsearch.index.IndexNotFoundException;
import org.elasticsearch.index.query.MultiMatchQueryBuilder.Type;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.search.SearchHit;

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

	private SearchResponse getResponse(String index, String type,String query,String att1, String att2) {
		SearchResponse response = null;
		try {
			response = client.prepareSearch(index)
					.setTypes(type)
					.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
					.setQuery(QueryBuilders.multiMatchQuery(query,att1,att2)
							.type(Type.CROSS_FIELDS)
							.operator(Operator.AND)).get();
		}catch(IndexNotFoundException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	@Override
	public SearchResponse getSearchResponse(String index, String type, Map map) {
			String query = map.getUserid()+" "+map.getMapid()+" "+map.getMode(); 
			return getResponse(index, type, query, "userid", "mapid");
	}
	
	@Override
	public boolean exist(String index, String type, Map map){
		SearchResponse response = getSearchResponse(index,type,map);
		boolean b1 = response != null;
		boolean b2 = (response != null)?0 < response.getHits().getHits().length:false;

		return (b1 && b2);
	}


	@Override
	public int delete(String index, Map map) {
		BulkByScrollResponse response = DeleteByQueryAction.INSTANCE.newRequestBuilder(client)
				.filter(QueryBuilders.matchQuery("mapid",map.getMapid())) 
				.source(index)                                  
				.get();                                             
		client.admin().indices().prepareRefresh(index).get();

		return((int)response.getDeleted());
	}

	@Override
	public Map find(String index, String type, Class<Map> mapclass, String id, Object obj) {
		Map map = null;
		//User user = (User)obj;
		String query = id+" home";
		System.out.println("$$$$$$$$query"+query);
		SearchResponse response = getResponse(index, type, query, "userid", "mode");
		SearchHit[] res = response.getHits().getHits();
		
		if(res.length == 1) {
			map =(Map)super.getObj(res[0],mapclass);
		}else{
			query = "root public";
			response = getResponse(index, type, query, "userid", "mode");
			res = response.getHits().getHits();
			if(res.length == 1)
				map =(Map)super.getObj(res[0],mapclass);
		}
		return map;
	}

	@Override
	public String getId(String index, String type, Map obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map> findAllById(String index, String type, String id, String query, Class<Map> t) {//id tab
		List<Map> mapList = new ArrayList<>();
		if(query==null) {
			query = "userid";
		}
		SearchResponse response = getResponse(index, type, query, id, "");
		SearchHit[] res = response.getHits().getHits();
		 for(int i = 0; i < res.length;i++) {
			 mapList.add(getObj(res[i],t));
		 }
		return mapList;
	}
}
