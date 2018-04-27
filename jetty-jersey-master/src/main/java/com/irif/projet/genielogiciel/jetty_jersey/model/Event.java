package com.irif.projet.genielogiciel.jetty_jersey.model;

import com.example.jetty_jersey.ws.requests.PlaceRequest;

public class Event extends Place{
	private String begin;
	private String end;

	public Event(){}
	
	public Event(String mapname,String placename,String longitude,String latitude,String description,String beginDate, String endDate){
		super(mapname, placename,longitude, latitude, description);
		this.begin = beginDate;
		this.end = endDate;
	}
	
	public Event(PlaceRequest placeRequest, String mapName){
		super(placeRequest, mapName);
		this.begin = placeRequest.getStartDate()+"";
		this.end = placeRequest.getEndDate()+"";
		
	}
	

	public String getBegin() {
		return begin;
	}

	public void setBegin(String begin) {
		this.begin = begin;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}




}
