package com.irif.projet.genielogiciel.jetty_jersey.model;

public class Comment {
	private String commentid;
	private String placeid;
	private String comment;
	private String dateDeCreation;
	//add likes for comments and pictures future extension
	
	
	public Comment(String comment,String placeid) {
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
	
}
