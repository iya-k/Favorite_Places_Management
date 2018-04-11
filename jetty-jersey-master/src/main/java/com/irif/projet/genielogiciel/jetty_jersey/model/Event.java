package com.irif.projet.genielogiciel.jetty_jersey.model;

import java.util.Date;

public class Event extends Place{
	private Date begin;
	private Date end;

	public Event(String eventid,String mapid,String name,String adress,String longitude,String latitude,String description,Date beginDate, Date endDate){
		super(eventid, mapid, name, adress, longitude, latitude, description);
		this.begin = beginDate;
		this.end = endDate;
	}

	public Date getBegin() {
		return begin;
	}

	public void setBegin(Date begin) {
		this.begin = begin;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}
	
	
	
	
}
