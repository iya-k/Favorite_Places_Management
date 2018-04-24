package com.irif.projet.genielogiciel.jetty_jersey.DAO.implement;

import java.util.List;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.MultiMatchQueryBuilder.Type;

import com.irif.projet.genielogiciel.jetty_jersey.DAO.DAO;
import com.irif.projet.genielogiciel.jetty_jersey.model.Comment;

public class CommentDAO extends DAO<Comment>{

	public CommentDAO(TransportClient client) {
		super(client);
	}
	@Override
	public SearchResponse getSearchResponse(String index, String type, String query){
		SearchResponse response = client.prepareSearch(index)
				.setTypes(type)
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(QueryBuilders.multiMatchQuery(query,"placeid","mapid")
						.type(Type.CROSS_FIELDS)
						.operator(Operator.AND)).get();
		return response;
	}
	@Override
	public boolean exist(String index, String type, Comment com){
		Comment comment = com;
		SearchResponse response = client.prepareSearch(index)
				.setTypes(type)
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(QueryBuilders.matchQuery("comment",comment.getComment()))
				.get();
		return (0<response.getHits().getHits().length);
	}


	@Override
	public int delete(String index, Comment comment) {
		//DeleteResponse response = client.prepareDelete(index,type,Integer.toString(comment.getCommentid())).get();
		return 0;
	}

	@Override
	public String getId(String index, String type, Comment obj) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Comment find(String index, String type, String userid) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Comment> findAllById(String index, String type, String query) {
		// TODO Auto-generated method stub
		return null;
	}

}
