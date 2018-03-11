package com.irif.projet.genielogiciel.jetty_jersey.DAO;

import java.util.List;

import com.irif.projet.genielogiciel.jetty_jersey.model.User;

public class ExampleOfUse {

	public static void main(String[] args) {
		//obtention du factory correspondant
		AbstractDAOFactory aDAOF = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		//obtention du DAO
		DAO<User> userDAO =aDAOF.getUserDAO();
		User usr = new User(1, "test", "test", "test", "test@test.com", "test");
		//creation
		userDAO.create(usr);
		//supression
		userDAO.delete(usr);
		//mise a jour
		userDAO.update(usr);
		//recherche
		List<User> usr2 = userDAO.find(1);
		
		// obtention des attributs
		String userName =usr2.get(0).getUsername();
		String firstname=usr2.get(0).getFirstname() ;
		String lastname=usr2.get(0).getLastname();
		String email=usr2.get(0).getEmail();
		String password=usr2.get(0).getPassword();
	}
}
