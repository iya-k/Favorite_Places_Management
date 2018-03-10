package com.irif.projet.genielogiciel.jetty_jersey.model;

public class Map {
	private int mapId;
	private String mapName;
	private int numberOfPlaces;
	private int numberOfEvents;
	private String privacy;
	
	
	public Map(){}


	public Map(int mapId, String mapName, int numberOfPlaces, int numberOfEvents, String privacy){
		this.mapId = mapId;
		this.mapName = mapName;
		this.numberOfPlaces = numberOfPlaces;
		this.numberOfEvents = numberOfEvents;
		this.privacy = privacy;
	}


	public int getMapId() {
		return mapId;
	}


	public void setMapId(int mapId) {
		this.mapId = mapId;
	}


	public String getMapName() {
		return mapName;
	}


	public void setMapName(String mapName) {
		this.mapName = mapName;
	}


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


	public String getPrivacy() {
		return privacy;
	}


	public void setPrivacy(String privacy) {
		this.privacy = privacy;
	}

	
}
