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
import com.irif.projet.genielogiciel.jetty_jersey.model.Picture;
import com.irif.projet.genielogiciel.jetty_jersey.model.Place;


public class PlaceDAO extends DAO<Place>{
	private DAO<Picture> picturedao;

	public PlaceDAO(TransportClient client, DAO<Picture> picturedao) {
		super(client);
		this.picturedao = picturedao;
	}



	@Override
	public SearchResponse getSearchResponse(String index, String type,String query){
		SearchResponse response = null;

		try{
			response = client.prepareSearch(index)
					.setTypes(type)
					.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
					.setQuery(QueryBuilders.multiMatchQuery(query,"mapname","placename","longitude","latitude")
							.type(Type.CROSS_FIELDS)
							.operator(Operator.AND)).get();
		}catch(IndexNotFoundException e){
			client.admin().indices().prepareCreate(index).get();
			//e.printStackTrace();
		}
		return response;
	}
	@Override
	public boolean exist(String index, String type, Place place){
		String query = place.getMapname()+" "+
				place.getPlacename()+" "+
				place.getLongitude()+" "+
				place.getLatitude();
		SearchResponse response = getSearchResponse(index,type,query);
		boolean res = response != null && 0 < response.getHits().getHits().length;
		return (res);
	}

	@Override
	public String getId(String index, String type, Place place) {
		String id = "";
		try {
			String query = place.getMapname()+" "+
					place.getPlacename()+" "+
					place.getLongitude()+" "+
					place.getLatitude();
			id = getSearchResponse(index,type,query).getHits().getHits()[0].getId();
		}catch(ArrayIndexOutOfBoundsException | NullPointerException e){
			e.printStackTrace();
		}
		return(id);

	}

	@Override
	public int delete(String index, Place place) {
		String query = place.getMapname()+" "+
				place.getPlacename()+" "+
				place.getLongitude()+" "+
				place.getLatitude();

		BulkByScrollResponse response = DeleteByQueryAction.INSTANCE.newRequestBuilder(client)
				.filter(QueryBuilders.multiMatchQuery(query,"mapname","placename","longitude","latitude")
						.type(Type.CROSS_FIELDS)
						.operator(Operator.AND))
				.source(index)
				.get();
		client.admin().indices().prepareRefresh(index).get();
		return((int)response.getDeleted());
	}
	@Override
	public Place find(String index, String type, String query){
		Place place = null;
		Class cl = Place.class;
		SearchResponse response =  getSearchResponse(index,type,query);
		SearchHit[] res = response.getHits().getHits();
		if(res.length == 1) {
			place =super.getObj(res[0],cl);
		}
		return place;
	}

	@Override
	public List<Place> findAllById(String index, String type, String query){
		 Class<Place> cl = Place.class;
		List<Place> placeList = new ArrayList<>();
		SearchResponse response =  client.prepareSearch(index).setTypes(type)
				                  .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
					              .setQuery(QueryBuilders.matchQuery("mapname",query)).get();
		SearchHit[] res = response.getHits().getHits();
		 for(int i = 0; i < res.length;i++) {
			 placeList.add(getObj(res[i],cl));
		 }
		return placeList;
	}

}
