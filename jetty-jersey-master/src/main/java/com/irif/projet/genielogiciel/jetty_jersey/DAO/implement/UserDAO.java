package com.irif.projet.genielogiciel.jetty_jersey.DAO.implement;

import java.util.List;

import com.irif.projet.genielogiciel.jetty_jersey.DAO.DAO;
import com.irif.projet.genielogiciel.jetty_jersey.model.User;

public class UserDAO extends DAO<User>{

	public UserDAO(Object connect) {
		super(connect);
	}

	@Override
	public boolean create(User obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(User obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(User obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<User> find(int id) {
		// TODO Auto-generated method stub
		return null;
	}



	
}
