package com.irif.projet.genielogiciel.jetty_jersey.model;

public class Comment {
	private static int cpt = 1;
	private int commentid;
	private String comment;
	private int placeid;
	//add likes for comments and pictures future extension
	
	
	public Comment(String comment,int placeid) {
		this.commentid = cpt;
		this.comment = comment;
		this.placeid= placeid;
		cpt++;
	}


	public int getCommentid() {
		return commentid;
	}


	public void setCommentid(int commentid) {
		this.commentid = commentid;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
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
