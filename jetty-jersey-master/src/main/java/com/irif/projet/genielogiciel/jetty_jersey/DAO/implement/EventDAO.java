package com.irif.projet.genielogiciel.jetty_jersey.DAO.implement;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.IndexNotFoundException;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.MultiMatchQueryBuilder.Type;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.search.SearchHit;

import com.irif.projet.genielogiciel.jetty_jersey.DAO.DAO;
import com.irif.projet.genielogiciel.jetty_jersey.model.Event;
import com.irif.projet.genielogiciel.jetty_jersey.model.Picture;
import com.irif.projet.genielogiciel.jetty_jersey.model.Place;


public class EventDAO extends DAO<Event>{
	private DAO<Picture> pictures;
	
	public EventDAO(TransportClient client, DAO<Picture> pictures) {
		super(client);
		this.pictures = pictures;
	}
	@Override
	public SearchResponse getSearchResponse(String index, String type, String query) {

		SearchResponse response = client.prepareSearch(index)
				.setTypes(type)
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(QueryBuilders.multiMatchQuery(query,"mapname","eventname","longitude","latitude","begin","end")
						.type(Type.CROSS_FIELDS)
						.operator(Operator.AND)).get();
		return response;
	}

	@Override
	public boolean exist(String index, String type,Event e){
		Event event = e;
		SearchResponse response = client.prepareSearch(index)
                .setTypes(type)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.matchQuery("name",event.getPlacename()))
                .get();
		return (0<response.getHits().getHits().length);
	}


	@Override
	public int delete(String index, Event event) {
		
		String query = event.getMapname()+" "+
				event.getPlacename()+" "+
				event.getLongitude()+" "+
				event.getLatitude() +" "+
				event.getBegin() +" "+
				event.getEnd();

		BulkByScrollResponse response = DeleteByQueryAction.INSTANCE.newRequestBuilder(client)
				.filter(QueryBuilders.multiMatchQuery(query,"mapname","eventname","longitude","latitude","begin","end")
						.type(Type.CROSS_FIELDS)
						.operator(Operator.AND))
				.source(index)
				.get();
		client.admin().indices().prepareRefresh(index).get();
		return((int)response.getDeleted());
	}

	@Override
	public String getId(String index, String type, Event event) {
		String id = "";
		try {
			String query = event.getMapname()+" "+
					event.getPlacename()+" "+
					event.getLongitude()+" "+
					event.getLatitude()+" "+
					event.getBegin()+" "+
					event.getEnd();
			id = getSearchResponse(index,type,query).getHits().getHits()[0].getId();
		}catch(ArrayIndexOutOfBoundsException | NullPointerException e){
			e.printStackTrace();
		}
		return(id);
	}
	@Override
	public Event find(String index, String type, String query) {
		Event event = null;
		Class cl = Event.class;
		//TODO:CHECK THIS
		//SearchResponse response =  getSearchResponse(index,type,query,"beginDate","endDate", Operator.AND);
		SearchResponse response =  getSearchResponse(index,type,query);
		SearchHit[] res = response.getHits().getHits();
		if(res.length == 1) {
			event =(Event)super.getObj(res[0],cl);
		}
		return event;
	}
	@Override
	public List<Event> findAllById(String index, String type, String query) {
		Class<Event> cl = Event.class;
		List<Event> eventList = new ArrayList<>();
		try {

			SearchResponse response =  client.prepareSearch(index).setTypes(type)
					.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
					.setQuery(QueryBuilders.matchQuery("mapname",query)).get();
			SearchHit[] res = response.getHits().getHits();
			for(int i = 0; i < res.length;i++) {
				eventList.add(getObj(res[i],cl));
			}
		}catch(IndexNotFoundException e){
			client.admin().indices().prepareCreate(index).get();
		}

		return eventList;
	}
}
