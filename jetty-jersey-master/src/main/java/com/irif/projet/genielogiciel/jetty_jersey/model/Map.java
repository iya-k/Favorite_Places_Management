package com.irif.projet.genielogiciel.jetty_jersey.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.irif.projet.genielogiciel.jetty_jersey.DAO.DAO;

public class Map{

	private String mapid;
	private String userid;
	private String mapname;
	private String mode;
	private String creationdate;
	private String mapimg;


	public Map(){}

	public Map(String userid,String mapname,String mode,String mapimg){
		this.mapid = "map"+Map.getCurrentDateTime("yyyyMMddhhmmss")+userid;
		this.userid =userid;
		this.mapname = mapname;
		this.mode = mode;
		this.mapimg = mapimg;
		this.creationdate = Map.getCurrentDateTime("yyyy/MM/dd/hh:mm:ss");
	}
	
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
	
	public String getMapid() {
		return mapid;
	}

	public void setMapid(String mapid) {
		this.mapid = mapid;
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
	
	public String toString() {
		return this.mapname+" "+this.userid+" "+this.mode;
	}
}
