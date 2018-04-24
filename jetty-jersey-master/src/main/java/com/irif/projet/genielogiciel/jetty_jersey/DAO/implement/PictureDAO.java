package com.irif.projet.genielogiciel.jetty_jersey.DAO.implement;

import java.util.ArrayList;
import java.util.List;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.MultiMatchQueryBuilder.Type;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.search.SearchHit;

import com.irif.projet.genielogiciel.jetty_jersey.DAO.DAO;
import com.irif.projet.genielogiciel.jetty_jersey.model.Picture;

public class PictureDAO extends DAO<Picture>{

	public PictureDAO(TransportClient client) {
		super(client);
	}
	@Override
	public SearchResponse getSearchResponse(String index, String type,String query){

		SearchResponse response = client.prepareSearch(index)
				.setTypes(type)
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(QueryBuilders.multiMatchQuery(query,"url","placeid")
						.type(Type.CROSS_FIELDS)
						.operator(Operator.AND)).get();
		return response;
	}

	@Override
	public String getId(String index, String type, Picture picture) {
		String id = "";
		try {
			String query = picture.getPicturename();
			id = getSearchResponse(index,type,query).getHits().getHits()[0].getId();
		}catch(ArrayIndexOutOfBoundsException | NullPointerException e){
			e.printStackTrace();
		}
		return(id);
	}

	@Override
	public boolean exist(String index, String type, Picture picture) {
		String query = picture.getUrl()+" "+picture.getPlaceid();
		SearchResponse response = getSearchResponse(index,type,query);
		boolean res = response != null && 0 < response.getHits().getHits().length;
		return (res);
	}

	@Override
	public int delete(String index, Picture picture) {
		String query = picture.getUrl()+" "+picture.getPlaceid();
		BulkByScrollResponse response = DeleteByQueryAction.INSTANCE.newRequestBuilder(client)
				.filter(QueryBuilders.multiMatchQuery(query,"url","placeid")
                		.type(Type.CROSS_FIELDS)
						.operator(Operator.AND))
				.source(index)
				.get();
		client.admin().indices().prepareRefresh(index).get();
		return((int)response.getDeleted());
	}
	@Override
	public Picture find(String index, String type, String query){
		Picture picture = null;
		Class cl = Picture.class;
		SearchResponse response =  getSearchResponse(index,type,query);
		SearchHit[] res = response.getHits().getHits();
		if(res.length == 1){
			picture =super.getObj(res[0],cl);
		}
		return picture;
	}
	@Override
	public List<Picture> findAllById(String index, String type, String query){
		Class<Picture> cl = Picture.class;
		List<Picture> picturesList = new ArrayList<>();
		SearchResponse response =  client.prepareSearch(index).setTypes(type)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
	              .setQuery(QueryBuilders.matchQuery("placeid",query)).get();
		SearchHit[] res = response.getHits().getHits();
		for(int i = 0; i < res.length;i++) {
			 picturesList.add(getObj(res[i],cl));
		 }
		return picturesList;
	}
}
