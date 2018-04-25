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

import com.example.jetty_jersey.constant.Constants;
import com.irif.projet.genielogiciel.jetty_jersey.DAO.DAO;
import com.irif.projet.genielogiciel.jetty_jersey.model.Event;
import com.irif.projet.genielogiciel.jetty_jersey.model.Map;
import com.irif.projet.genielogiciel.jetty_jersey.model.Place;

public class MapDAO extends DAO<Map>{
	private DAO<Place> placedao;
	private DAO<Event> eventdao;

	public MapDAO(TransportClient client, DAO <Place> placedao,DAO<Event> eventdao ) {
		super(client);
		this.placedao = placedao;
		this.eventdao = eventdao;
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
	public String getId(String index, String type, Map map) {
		String id = "";
		try {
			String query = map.getMapname();
			id = getSearchResponse(index,type,query).getHits().getHits()[0].getId();
		}catch(ArrayIndexOutOfBoundsException | NullPointerException e){
			e.printStackTrace();
		}
		return(id);
	}

	@Override
	public SearchResponse getSearchResponse(String index, String type,String query){
		return getResponse(index, type, query, "userid", "mapname",Operator.AND);
	}

	@Override
	public boolean exist(String index, String type, Map map){
		String query =map.getUserid()+" "+map.getMapname();
		SearchResponse response = getSearchResponse(index,type,query);
		boolean res = response != null && 0 < response.getHits().getHits().length;
		return (res);
	}


	@Override
	public int delete(String index, Map map){
		BulkByScrollResponse response = DeleteByQueryAction.INSTANCE.newRequestBuilder(client)
				.filter(QueryBuilders.matchQuery("mapname",map.getMapname()))
				.source(index)
				.get();
		client.admin().indices().prepareRefresh(index).get();
		/*
		if (estEffectue == 1) {
			List<Place> listPlace = placedao.findAllById("placedb", "place", mapname, Place.class);
			for (int i = 0; i < listPlace.size(); i++) {
				placedao.delete("placedb", listPlace.get(i));
			}
		}
		*/
		return((int)response.getDeleted());
	}
	@Override
	public Map find(String index, String type, String query){
		Map map = null;
		Class cl = Map.class;
		SearchResponse response = getResponse(index, type,query,"userid","mapname",Operator.OR);
		SearchHit[] res = response.getHits().getHits();

		if(res.length == 1) {
			map =super.getObj(res[0],cl);
			map.setPlacelist(placedao.findAllById(Constants.pINDEX,Constants.pTYPE, map.getMapname()));
			map.setEventlist(eventdao.findAllById(Constants.eINDEX,Constants.eTYPE, map.getMapname()));
		}else{
			map = new Map(query,query+"map","public","root.jpg");
			if(! (add("mapdb","map",map)==1 && add("homemapdb","homemap",map)==1)){
				map = null;
			}
		}

		return map;
	}
	@Override
	public List<Map> findAllById(String index, String type, String query){//id tab
		Class<Map> cl = Map.class;
		List<Map> mapList = new ArrayList<>();
		SearchResponse response = getResponse(index, type,query,"userid", "mode",Operator.AND);
		SearchHit[] res = response.getHits().getHits();
		 for(int i = 0; i < res.length;i++) {
			 mapList.add(getObj(res[i],cl));
		 }
		return mapList;
	}
}
