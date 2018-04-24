package com.irif.projet.genielogiciel.jetty_jersey.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.irif.projet.genielogiciel.jetty_jersey.DAO.DAO;

public class Map{
	private String userid;
	private String mapname;
	private String mode;
	private String creationdate;
	private String mapimg;
	private List<Place> placeList;
	private List<Event> eventList;

	public Map(){}

	public static String getCurrentDateTime(String format){
		Date dNow = new Date();
		SimpleDateFormat ft;
		String date = "";
		if(format.equals("yyyyMMddhhmmss")){
			ft = new SimpleDateFormat(format);
		}else{
			ft = new SimpleDateFormat("yyyy/MM/dd/hh:mm:ss");
		}
		date = ft.format(dNow);
		return date;
	}


	public Map(String userid,String mapname,String mode,String mapimg){
		this.userid =userid;
		this.mapname = mapname;
		this.mode = mode;
		this.mapimg = mapimg;
		this.creationdate = Map.getCurrentDateTime("yyyy/MM/dd/hh:mm:ss");
		this.placeList = new ArrayList<>();
		this.eventList = new ArrayList<>();
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getMapname() {
		return mapname;
	}

	public void setMapname(String mapname) {
		this.mapname = mapname;
	}
	public String getMode(){
		return mode;
	}

	public void setMode(String mode){
		this.mode = mode;
	}

	public String getCreationdate(){
		return creationdate;
	}

	public String getMapimg() {
		return mapimg;
	}

	public void setMapimg(String mapimg) {
		this.mapimg = mapimg;
	}

	public void setCreationdate(String creationdate) {
		this.creationdate = creationdate;
	}

	
	public void setPlacelist(List<Place> placeList){
		this.placeList = placeList;
	}
	
	public void setEventlist(List<Event> eventList){
		this.eventList = eventList;
	}
	
	public List<Place> getPlaceList(){
		return placeList;
	}
	
	public List<Event> getEventList(){
		return eventList;
	}
	
	public String toString() {
		return this.mapname+" "+this.userid+" "+this.mode;
	}
	
}