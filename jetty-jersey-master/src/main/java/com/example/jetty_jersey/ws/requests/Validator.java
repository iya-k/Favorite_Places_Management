package com.example.jetty_jersey.ws.requests;

import com.example.jetty_jersey.constant.Constants;

public class Validator {
	
    public static boolean checkName(String name) {
    	if(name.length() < 4 || Constants.containANumber(name))
        		return false;
        
        return true;
    }

    public static boolean checkUsername(String username) {
        if(username.length() < 4)
        	return false;

        return true;
    }

    public static boolean checkEmail(String email) {
        if(email.indexOf("@") == -1 || email.indexOf(".") == -1)
        	return false;

        return true;
    }

    public static boolean checkDate(String date) {
        if(date.length() != 10)
        	return false;

        return true;
    }

    public static boolean checkStartEndDate(String startDate, String endDate) {
        if(startDate != endDate)
        	return false;

        return true;
    }

    public static boolean checkPassword(String password) {
        if(password.length() < 8 || !Constants.containANumber(password))
        	return false;

        return true;
    }

    public static boolean checkConfirmedPassword(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

}
