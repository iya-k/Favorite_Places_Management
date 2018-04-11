package com.irif.projet.genielogiciel.jetty_jersey.model;

import java.util.ArrayList;
import java.util.List;

public class Place {
	private String placeid;
	private String mapid;
	private String name;
	private String adress;
	private String longitude;
	private String latitude;
	private String description;
	private String dateDeCreation;
	private List<Picture> pictures;
	private List<Comment> comments;
	
	public Place(){}
	
	public Place(String placeid,String mapid,String name,String adress,String longitude,String latitude,String description){	
		this.placeid = placeid;
		this.mapid = mapid;
		this.name = name;
		this.adress = adress;
		this.description = description;
		this.mapid = mapid;
		this.dateDeCreation = Map.getCurrentDateTime("yyyy/MM/dd/hh:mm:ss");
		this.pictures = new ArrayList<Picture>();
		this.comments =new ArrayList<Comment>();
	}
	
	
	public String getPlaceid() {
		return placeid;
	}

	public void setPlaceid(String placeid) {
		this.placeid = placeid;
	}

	public String getMapid() {
		return mapid;
	}

	public void setMapid(String mapid) {
		this.mapid = mapid;
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

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
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

	
	
	
}