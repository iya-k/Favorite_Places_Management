package com.irif.projet.genielogiciel.jetty_jersey.model;

public class Map{
	
	private String mapid;
	private String userid;
	private String mapname;
	private String rank;
	private String creationdate;
	
	
	public Map(){}


	public Map(String userid,String rank,String mapname,String creationdate){
		this.userid ="";
	}


	/*public String getId() {
		return id;
	}


	public void setId(String mapId) {
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
	}*/
}
