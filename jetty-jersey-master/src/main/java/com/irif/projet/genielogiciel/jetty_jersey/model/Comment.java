package com.irif.projet.genielogiciel.jetty_jersey.model;

public class Comment {
	private String commentid;
	private String userid; 
	private String placeid;
	private String eventid; // one of id(placeid | eventid) != null 
	private String comment;
	private String dateDeCreation;

	public Comment(String comment,String placeid,String evenid) {
		this.placeid= placeid;
		this.comment = comment;
		this.dateDeCreation =  Map.getCurrentDateTime("yyyy/MM/dd/hh:mm:ss");
	}


	public String getCommentid() {
		return commentid;
	}


	public void setCommentid(String commentid) {
		this.commentid = commentid;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getPlaceid() {
		return placeid;
	}

	public void setPlaceid(String placeid) {
		this.placeid = placeid;
	}

	public String getEventid() {
		return eventid;
	}

	public void setEventid(String eventid) {
		this.eventid = eventid;
	}

	public String getDateDeCreation() {
		return dateDeCreation;
	}

	public void setDateDeCreation(String dateDeCreation) {
		this.dateDeCreation = dateDeCreation;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

}

