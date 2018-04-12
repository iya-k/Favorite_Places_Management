package com.irif.projet.genielogiciel.jetty_jersey.DAO.implement;

import java.util.ArrayList;
import java.util.List;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.IndexNotFoundException;
import org.elasticsearch.index.query.MultiMatchQueryBuilder.Type;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.search.SearchHit;

import com.irif.projet.genielogiciel.jetty_jersey.DAO.DAO;
import com.irif.projet.genielogiciel.jetty_jersey.model.Map;

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
			//e.printStackTrace();
			client.admin().indices().prepareCreate(index).get();
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
		SearchResponse response =  getSearchResponse(index,type,map);
		boolean res = response != null && 0 <response.getHits().getHits().length;
		
		return (res);
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
			map =super.getObj(res[0],mapclass);
		}else{
			query = "root public";
			response = getResponse(index, type, query, "userid", "mode");
			res = response.getHits().getHits();
			if(res.length == 1)
				map =super.getObj(res[0],mapclass);
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
