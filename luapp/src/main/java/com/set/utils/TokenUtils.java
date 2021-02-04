package com.set.utils;

import java.util.Date;
import java.util.logging.Logger;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenUtils {
	
	 private static Logger logger = Logger.getLogger("TokenUtils.class"); 
		
	public static String generateToken(String email, Date date) {
		String jwtToken = Jwts.builder().setSubject(email).setId("Setumbrella").setIssuedAt(new Date()).setExpiration(date)
					.signWith(SignatureAlgorithm.HS256, "secretkey").compact();
		return jwtToken;
	}
	
	public static Claims verifyToken(String token) {
		//https://www.mkyong.com/java/how-to-compare-dates-in-java/
		Claims claims = null;
		try {
			claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();	
		} catch (Exception e) {
			return null;
		}
		
		if (claims.isEmpty()) {
			return null;
		} 
		Date claimExpiryDate = claims.getExpiration();
		Date today = new Date();
		if (claimExpiryDate != null) {
			if (claimExpiryDate.compareTo(today) < 0) {
	            logger.info("claimExpiryDate is before today"); // invalid
	            return null;
	        }	
		}
		
		return claims;
	}

}
