package com.set.utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Random;
import java.util.logging.Logger;

import com.set.config.SchoolIdentification;

public class  CommonUtils {
	private static Logger logger = Logger.getLogger("CommonUtils.class");

	public static String generatePrimaryKeyId(String tblName) {
		String pkId = tblName.substring(6, 8).toUpperCase();
		Random rand = new Random();
			// Generate random integers in range 0 to 9999999
		int randInt = rand.nextInt(1000000);
		pkId += randInt;
		return pkId;
	}
	
	public static Date getCurrentDateTime(){
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		 Calendar calendar = Calendar.getInstance();
			java.util.Date currentDate = calendar.getTime();
			Date date = new Date(currentDate.getTime());
			return date;
	}
	
	public static String checkNull(Object obj) {
		String strEmpty = "";
		if(null == obj) {
			obj = strEmpty;
		}
		return obj.toString();
	}

	public static boolean isSchoolActive(String uri, String schoolName) {
		if(schoolName.equals("luschool")) {
			return true;
		}else {
			return false;
		}
	}
}
