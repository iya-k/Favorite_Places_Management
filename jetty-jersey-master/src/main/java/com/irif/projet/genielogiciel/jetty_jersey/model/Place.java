package com.irif.projet.genielogiciel.jetty_jersey.model;

import java.util.List;

public class Place {
	private static int idPlace = 1;
	private String id;
	private String name;
	private String adress;
	private String description;
	private int mapid; //id de la map sur lequel est placer
	private List<Picture> pictures;
	private List<Comment> comments;
	
	public Place(){}
	
	public Place(String name, String adress, String description,int mapid, List<Picture> pictures,
			List<Comment> comments) {
		this.name = name;
		this.adress = adress;
		this.description = description;
		this.mapid = mapid;
		this.pictures = pictures;
		this.comments = comments;
		idPlace++;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	public int getMapid() {
		return mapid;
	}

	public void setMapId(int mapid) {
		this.mapid = mapid;
	}

	public static int getCpt() {
		return idPlace;
	}

	
	
}