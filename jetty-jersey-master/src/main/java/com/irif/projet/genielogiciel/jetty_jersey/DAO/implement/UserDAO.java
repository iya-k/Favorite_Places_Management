package com.irif.projet.genielogiciel.jetty_jersey.DAO.implement;


import com.irif.projet.genielogiciel.jetty_jersey.DAO.DAO;
import com.irif.projet.genielogiciel.jetty_jersey.model.Map;
import com.irif.projet.genielogiciel.jetty_jersey.model.User;

import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.MultiMatchQueryBuilder.Type;
import org.elasticsearch.index.IndexNotFoundException;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.search.SearchHit;

public class UserDAO extends DAO<User>{
	private DAO<Map> mapdao;

	public UserDAO(TransportClient client, DAO<Map> mapdao){
		super(client);
		this.mapdao = mapdao;
	}

	@Override
	public SearchResponse getSearchResponse(String index, String type,String query) {
		SearchResponse response = null;
		try {
			response = client.prepareSearch(index)
				.setTypes(type)
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(QueryBuilders.matchQuery("username",query))
				.get();
		}catch(IndexNotFoundException e){
			client.admin().indices().prepareCreate(index).get();
			//e.printStackTrace();
		}
		return response;
	}

	@Override
	public String getId(String index, String type, User user){
		String id = "";
		try {
			String query = user.getUsername();
			id = getSearchResponse(index,type,query).getHits().getHits()[0].getId();
		}catch(ArrayIndexOutOfBoundsException | NullPointerException e){
			e.printStackTrace();
		}
		return(id);
	}

	@Override
	public boolean exist(String index, String type, User user){
		String query = user.getUsername();
		SearchResponse response = getSearchResponse(index,type,query);
		boolean res = response != null && 0 < response.getHits().getHits().length;
		return (res);
	}


	/**
	 * connection method
	 * @param index type userName password
	 * @return  user
	 */
	public User connect(String index, String type,String userName, String password){
		User user=null;
		String encryptpassword =User.Encrypt(userName,password);
		System.out.println("Test : "+encryptpassword);
		String query = userName+" "+encryptpassword;
		SearchResponse response = client.prepareSearch(index)
				.setTypes(type)
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(QueryBuilders.multiMatchQuery(query,"username","password")
						.type(Type.CROSS_FIELDS)
						.operator(Operator.AND)).get();
		client.admin().indices().prepareRefresh(index).get();

		SearchHit[] res = response.getHits().getHits();
		if(res.length == 1)
			user = super.getObj(res[0],User.class);
		return (user);
	}


	@Override
	public int delete(String index,User user){
		String username = user.getUsername();
		BulkByScrollResponse response = DeleteByQueryAction.INSTANCE.newRequestBuilder(client)
				.filter(QueryBuilders.matchQuery("username",user.getUsername()))
				.source(index)
				.get();
		client.admin().indices().prepareRefresh(index).get();
		/*
		int estEffectue = (int)response.getDeleted();
		if (estEffectue == 1) {
			List<Map> listMap = mapdao.findAllById("mapdb", "map", username, Map.class);
			for (int i = 0; i < listMap.size(); i++) {
				mapdao.delete("mapdb", listMap.get(i));
			}
		}
		*/
		return((int)response.getDeleted());
	}
	@Override
	public User find(String index, String type, String query){
		User user = null;
		Class cl = User.class;
		SearchResponse response =  getSearchResponse(index,type,query);
		SearchHit[] res = response.getHits().getHits();
		if(res.length == 1){
			user =super.getObj(res[0],cl);
		}
		return user;
	}

	@Override
	public List<User> findAllById(String index, String type, String query) {
		// TODO Auto-generated method stub
		return null;
	}

}
