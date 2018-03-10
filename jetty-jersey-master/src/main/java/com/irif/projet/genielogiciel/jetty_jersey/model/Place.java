package com.irif.projet.genielogiciel.jetty_jersey.model;

import java.util.List;

public abstract class Place {
	private int id;
	private String name;
	private String adress;
	private String description;
	private List<Picture> pictures;
	private List<Comment> comments;
	
	public Place(){}
	
	public Place(int id, String name, String adress, String description, List<Picture> pictures,
			List<Comment> comments) {
		
		this.id = id;
		this.name = name;
		this.adress = adress;
		this.description = description;
		this.pictures = pictures;
		this.comments = comments;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
	
	
	
	
}