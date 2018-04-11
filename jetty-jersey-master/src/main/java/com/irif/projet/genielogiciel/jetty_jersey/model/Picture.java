package com.irif.projet.genielogiciel.jetty_jersey.model;

public class Picture {
	private String pictureid;
	private String placeid;
	private String picturename;
	private String url;


	public Picture(String picturename, String url,String placeid) {
		this.picturename = picturename;
		this.url = url;
		this.placeid =placeid;
	}


	public String getPictureid() {
		return pictureid;
	}

	public void setPictureid(String pictureid) {
		this.pictureid = pictureid;
	}

	public String getPicturename() {
		return picturename;
	}

	public void setPicturename(String picturename) {
		this.picturename = picturename;
	}

	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getPlaceid() {
		return placeid;
	}

	public void setPlaceid(String placeid) {
		this.placeid = placeid;
	}
	
}
