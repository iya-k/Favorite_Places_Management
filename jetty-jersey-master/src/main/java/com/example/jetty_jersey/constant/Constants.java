package com.example.jetty_jersey.constant;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Constants {

	public static final String USERS = "users";
	public static final String PLACES = "places";
	public static final String MAPS = "maps";
	public static final String EVENTS = "events";
	public static final String PICTURES = "pictures";
	
	public static final String mINDEX = "mapdb";
    public static final String mTYPE = "map";
    
    public static final String pINDEX = "placedb";
    public static final String pTYPE = "place";
    
    public static final String eINDEX = "eventdb";
    public static final String eTYPE = "event";
    
    public static final String uINDEX = "userdb";
    public static final String uTYPE = "user";
    
    public static final String piINDEX = "picturedb";
    public static final String piTYPE = "picture";
    

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
    
	public Constants() {
		
	}
}
