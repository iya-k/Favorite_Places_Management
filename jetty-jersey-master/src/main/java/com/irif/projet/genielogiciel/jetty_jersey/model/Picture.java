package com.irif.projet.genielogiciel.jetty_jersey.model;

public class Picture{
	// cle url placeid unique
	private String picturename;
	private String url;
	private String dateDeCreation;
	private String placeid;

	public Picture() {}

	public Picture(String picturename, String url,String placeid) {
		this.picturename = picturename;
		this.url = url;
		this.placeid =placeid;
		this.dateDeCreation =  Map.getCurrentDateTime("yyyy/MM/dd/hh:mm:ss");
	}

	public String getPicturename(){
		return picturename;
	}

	public void setPicturename(String picturename){
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

	public String getDateDeCreation() {
		return dateDeCreation;
	}

	public void setDateDeCreation(String dateDeCreation) {
		this.dateDeCreation = dateDeCreation;
	}

	public String toString() {
		return("Picture_name : "+picturename+" url : "+url+" placeid : "+placeid);
	}

}