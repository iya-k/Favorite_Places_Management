package com.irif.projet.genielogiciel.jetty_jersey.model;

import java.util.Date;

public class Event extends Place{
	private Date begin;
	private Date end;

	public Event(){}
	
	public Event(String mapname,String placename,String longitude,String latitude,String description,Date beginDate, Date endDate){
		super(mapname, placename,longitude, latitude, description);
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
