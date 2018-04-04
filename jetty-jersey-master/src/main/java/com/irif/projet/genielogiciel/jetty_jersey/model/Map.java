package com.irif.projet.genielogiciel.jetty_jersey.model;

public class Map {
	private static int idMAp = 1;
	private int id;
	private String mapName;
	//private int numberOfPlaces;// A VOIR
	//private int numberOfEvents;// A VOIR
	private String privacy;
	
	
	public Map(){}


	public Map(int id, String mapName,/* int numberOfPlaces, int numberOfEvents,*/ String privacy){
		this.id = id;
		this.mapName = mapName;
		//this.numberOfPlaces = numberOfPlaces;
		//this.numberOfEvents = numberOfEvents;
		this.privacy = privacy;
		idMAp++;
	}


	public int getId() {
		return id;
	}


	public void setId(int mapId) {
		this.id = mapId;
	}


	public String getMapName() {
		return mapName;
	}


	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

/*
	public int getNumberOfPlaces() {
		return numberOfPlaces;
	}


	public void setNumberOfPlaces(int numberOfPlaces) {
		this.numberOfPlaces = numberOfPlaces;
	}


	public int getNumberOfEvents() {
		return numberOfEvents;
	}


	public void setNumberOfEvents(int numberOfEvents) {
		this.numberOfEvents = numberOfEvents;
	}
	*/

	public String getPrivacy() {
		return privacy;
	}


	public void setPrivacy(String privacy) {
		this.privacy = privacy;
	}
	
	public static int getCpt() {
		return idMAp;
	}
	
}
