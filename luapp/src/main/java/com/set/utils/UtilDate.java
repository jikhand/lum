package com.set.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UtilDate {
	
	  public static boolean isValidDate(String inDate) {
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		    dateFormat.setLenient(false);
		    try {
		      dateFormat.parse(inDate.trim());
		    } catch (ParseException pe) {
		      return false;
		    }
		    return true;
		  }
	  
	  
/*	public static boolean isValidDate(String input, String format) {
	    boolean valid = false;

	    try {
	        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
	        String output = dateFormat.parse(input).format(format);
	        valid = input.equals(output); 
	    } catch (Exception ignore) {}

	    return valid;
	}*/
}
