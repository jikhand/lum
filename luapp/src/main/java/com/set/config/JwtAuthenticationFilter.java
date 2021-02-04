package com.set.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter{

	public JwtAuthenticationFilter() {
        super("/**");
    }
    @Override
    protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
        return true;
    }
@Override
public Authentication attemptAuthentication(HttpServletRequest arg0, HttpServletResponse arg1)
		throws AuthenticationException, IOException, ServletException {
	// TODO Auto-generated method stub
	return null;
}

}
