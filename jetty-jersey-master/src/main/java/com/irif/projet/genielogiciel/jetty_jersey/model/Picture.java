package com.irif.projet.genielogiciel.jetty_jersey.model;

public class Picture {
	private int pictureId;
	private String pictureName;
	private String url;
	
	
	
	public Picture(int pictureId, String pictureName, String url) {
		this.pictureId = pictureId;
		this.pictureName = pictureName;
		this.url = url;
	}


	public int getPictureId() {
		return pictureId;
	}

	public void setPictureId(int pictureId) {
		this.pictureId = pictureId;
	}

	public String getPictureName() {
		return pictureName;
	}

	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}

	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	} 
	
}
