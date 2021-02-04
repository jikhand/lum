package com.set.utils;

public class checkNullException {

@SuppressWarnings("unused")
public static Object firstNonNull(Object string) {
	String returnString;
	if (string != null)
	{
	   returnString = (String) string;
	}
	else
	{
	   returnString = "";
	}
	return returnString;
}

}
