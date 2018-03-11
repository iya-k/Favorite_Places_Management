package com.irif.projet.genielogiciel.jetty_jersey.DAO.implement;

import java.util.List;

import com.irif.projet.genielogiciel.jetty_jersey.DAO.DAO;
import com.irif.projet.genielogiciel.jetty_jersey.model.Comment;

public class CommentDAO extends DAO<Comment>{

	public CommentDAO(Object connect) {
		super(connect);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Comment obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Comment obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Comment obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Comment> find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
