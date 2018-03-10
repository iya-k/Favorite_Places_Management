package com.irif.projet.genielogiciel.jetty_jersey.model;

import java.util.Date;
import java.util.List;

public class Event extends Place{
	private Date begin;
	private Date end;

	public Event(int id, String name, String adress, String description, List<Picture> pictures,
			List<Comment> comments,Date begin, Date end) {
		super(id, name, adress, description, pictures, comments);
		this.begin = begin;
		this.end = end;
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
