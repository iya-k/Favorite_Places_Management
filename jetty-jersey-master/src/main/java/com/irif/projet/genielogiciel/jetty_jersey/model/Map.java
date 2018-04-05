package com.irif.projet.genielogiciel.jetty_jersey.model;

public class Map {
	private static int cpt = 1;
	private int id;
	private String mapName;
	private int userid;
	//private int numberOfPlaces;// A VOIR
	//private int numberOfEvents;// A VOIR
	private String privacy;
	
	
	public Map(){}


	public Map(int id, String mapName,int userid,String privacy){
		this.id = id;
		this.mapName = mapName;
		this.userid=userid;
		this.privacy = privacy;
		cpt++;
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

	

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}


	public String getPrivacy() {
		return privacy;
	}


	public void setPrivacy(String privacy) {
		this.privacy = privacy;
	}
	
	public static int getCpt() {
		return cpt;
	}
	
}
