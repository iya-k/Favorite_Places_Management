package com.irif.projet.genielogiciel.jetty_jersey.model;

import java.util.ArrayList;
import java.util.List;

public class Place{
	private String mapname;
	private String placename;
	private String longitude;
	private String latitude;
	private String description;
	private String dateDeCreation;
	private List<Picture> pictures;
	private List<Comment> comments;

	public Place(){}

	public Place(String mapname,String placename,String longitude,String latitude,String description){
		this.mapname = mapname;
		this.placename = placename;
		this.longitude = longitude;
		this.latitude = latitude;
		this.description = description;
		this.dateDeCreation = Map.getCurrentDateTime("yyyy/MM/dd/hh:mm:ss");
		this.pictures = new ArrayList<Picture>();
		this.comments =new ArrayList<Comment>();
	}

	public String getMapname(){
		return mapname;
	}

	public void setMapname(String mapname){
		this.mapname = mapname;
	}
	public String getPlacename() {
		return placename;
	}

	public void setPlacename(String placename) {
		this.placename = placename;
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

	public String getDateDeCreation(){
		return dateDeCreation;
	}

	public void setDateDeCreation(String dateDeCreation){
		this.dateDeCreation = dateDeCreation;
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

	public String toString() {
		return("Mapname : "+mapname+", placename : "+ placename);
	}

}