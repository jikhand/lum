package com.set.config;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import com.set.utils.TokenUtils;

public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	 @Autowired
	    private TokenUtils jwtUtil;
	 private Logger logger = Logger.getLogger("JwtAuthenticationProvider.class");

	    @Override
	    public boolean supports(Class<?> authentication) {
	        return (TokenUtils.class.isAssignableFrom(authentication));
	    }

	    @Override
	    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
	    }

	    @Override
	    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
	    	logger.info("authentication"+authentication.toString());
	        return null;
	    }

   

}