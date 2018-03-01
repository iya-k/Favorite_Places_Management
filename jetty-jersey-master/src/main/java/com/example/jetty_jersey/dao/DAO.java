package com.example.jetty_jersey.dao;


import com.example.jetty_jersey.interfaceDao.*;
import com.example.jetty_jersey.implement.*;


public class DAO {

	private static UserDao user;
	private static MapDao map;
	private static PlaceDao place;
	private static EventDao event;
	
	public static UserDao getUserDao(){
		if(user == null) {
			user = new UserImp();
		}
		return user;
	}

	public static MapDao getMapDao(){
		if(map == null) {
			map = new MapImp();
		}
		return map;
	}

	public static EventDao getEventDao(){
		if(event == null) {
			event = new EventImp();
		}
		return event;
	}

	public static PlaceDao getPlaceDao(){
		if(place == null) {
			place = new PlaceImp();
		}
		return place;
	}
}
