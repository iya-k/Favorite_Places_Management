package com.irif.projet.genielogiciel.jetty_jersey.model;

public class Picture {
	private static int cpt = 1;
	private int pictureid;
	private String picturename;
	private String url;
	private int placeid;


	public Picture(String picturename, String url,int placeid) {
		this.pictureid = cpt;
		this.picturename = picturename;
		this.url = url;
		this.placeid =placeid;
		cpt++;
	}


	public int getPictureid() {
		return pictureid;
	}

	public void setPictureid(int pictureid) {
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
	
	public int getPlaceid() {
		return placeid;
	}

	public void setPlaceid(int placeid) {
		this.placeid = placeid;
	}
	
	public static int getCpt() {
		return cpt;
	}

	
	
}
