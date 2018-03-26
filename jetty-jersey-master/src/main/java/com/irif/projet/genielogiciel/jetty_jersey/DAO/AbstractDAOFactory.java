package com.irif.projet.genielogiciel.jetty_jersey.DAO;

import java.net.UnknownHostException;

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


	//M�thode permettant de r�cup�rer les Factory
	/**
	 * 
	 * @param type : type of factory desired
	 * @return factory corresponding to the type
	 * @throws UnknownHostException 
	 */
	public static AbstractDAOFactory getFactory(int type){
			try {
				switch(type){
					case DAO_FACTORY:
						DAOFactory adf= new DAOFactory();
						adf.initClient();
						return adf;
				}
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			return null;
	}
}
