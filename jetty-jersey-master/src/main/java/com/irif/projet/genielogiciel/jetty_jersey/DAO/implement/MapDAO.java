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
import com.irif.projet.genielogiciel.jetty_jersey.model.Place;


public class MapDAO extends DAO<Map>{
	private DAO<Place> placedao;

	public MapDAO(TransportClient client, DAO<Place> placedao) {
		super(client);
		this.placedao = placedao;
	}
	

	private SearchResponse getResponse(String index, String type,String query,String att1, String att2,Operator op) {
		SearchResponse response = null;
		
		try{
			response = client.prepareSearch(index)
					.setTypes(type)
					.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
					.setQuery(QueryBuilders.multiMatchQuery(query,att1,att2)
							.type(Type.CROSS_FIELDS)
							.operator(op)).get();
		}catch(IndexNotFoundException e) {
			client.admin().indices().prepareCreate(index).get();
			//e.printStackTrace();
		}
		return response;
	}
	
	
	
	@Override
	public SearchResponse getSearchResponse(String index, String type, Map map) {
			String query =map.getUserid()+" "+map.getMapname();
			return getResponse(index, type, query, "userid", "mapname",Operator.AND);
	}
	
	@Override
	public boolean exist(String index, String type, Map map){
		SearchResponse response = getSearchResponse(index,type,map);
		boolean res = response != null && 0 < response.getHits().getHits().length;
		return (res);
	}


	@Override
	public int delete(String index, Map map){
		String mapname = map.getMapname();
		BulkByScrollResponse response = DeleteByQueryAction.INSTANCE.newRequestBuilder(client)
				.filter(QueryBuilders.matchQuery("mapname",map.getMapname())) 
				.source(index)                                  
				.get();                                             
		client.admin().indices().prepareRefresh(index).get();
		int estEffectue = (int)response.getDeleted();
		if (estEffectue == 1) {
			List<Place> listPlace = placedao.findAllById("placedb", "place", mapname, Place.class);
			for (int i = 0; i < listPlace.size(); i++) {
				placedao.delete("placedb", listPlace.get(i));
			}
		}
		return estEffectue;
	}

	@Override
	public String getId(String index, String type, Map obj) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Map find(String index, String type, Class<Map> mapclass, String userid){
		Map map = null;
		SearchResponse response = getResponse(index, type,userid,"userid","mapname",Operator.OR);
		SearchHit[] res = response.getHits().getHits();
		
		if(res.length == 1) {
			map =(Map)super.getObj(res[0],Map.class);
		}else{
			map = new Map(userid,userid+"map","public","root.jpg");
			add("mapdb","map",map);
			add("homemapdb","homemap",map);
		}
		
		return map;
	}

	@Override
	public List<Map> findAllById(String index, String type, String query, Class<Map> t){//id tab
		List<Map> mapList = new ArrayList<>();
		SearchResponse response = getResponse(index, type,query,"userid", "mode",Operator.AND);
		SearchHit[] res = response.getHits().getHits();
		 for(int i = 0; i < res.length;i++) {
			 mapList.add(getObj(res[i],t));
		 }
		return mapList;
	}
}
