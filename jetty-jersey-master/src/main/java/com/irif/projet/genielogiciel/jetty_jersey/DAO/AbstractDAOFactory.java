package com.irif.projet.genielogiciel.jetty_jersey.DAO;

public abstract class AbstractDAOFactory {
	/**
	 * different types of factories
	 */
	public static final int DAO_FACTORY = 0;


	/**
	 * 
	 * @return an object User interact with DB
	 */
	public abstract DAO getUserDAO();

	/**
	 * 
	 * @return an object Map interact with DB
	 */
	public abstract DAO getMapDAO();

	/**
	 * 
	 * @return an object Place interact with DB
	 */
	public abstract DAO getPlaceDAO();

	/**
	 * 
	 * @return an object Event interact with DB
	 */
	public abstract DAO getEventDAO();

	/**
	 * 
	 * @return an object Comment interact with DB
	 */
	public abstract DAO getCommentDAO();

	/**
	 * 
	 * @return an object Picture interact with DB
	 */
	public abstract DAO getPictureDAO();


	//Méthode permettant de récupérer les Factory
	/**
	 * 
	 * @param type : type of factory desired
	 * @return factory corresponding to the type
	 */
	public static AbstractDAOFactory getFactory(int type){
		switch(type){
		case DAO_FACTORY:
			DAOFactory adf= new DAOFactory();
			adf.initClient();
			return adf;
		default:
			return null;
		}
	}
}
