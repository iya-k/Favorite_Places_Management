package com.irif.projet.genielogiciel.jetty_jersey.model;

public class Comment {
	private int commentId;
	private String comment;
	//add likes for comments and pictures future extension
	
	
	public Comment(int commentId, String comment) {
		this.commentId = commentId;
		this.comment = comment;
	}


	public int getCommentId() {
		return commentId;
	}


	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	
	
	
	
	
	
}
