package com.set.config;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import com.set.service.CustomUserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@ComponentScan("com.set")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	private Logger logger = Logger.getLogger("WebSecurityConfig.class");
	@Autowired
	CustomUserDetailsService customUserDetailsService;

	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("Authentication Web Security");
		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		logger.info("in WebSecurity"); 	
		web.ignoring().antMatchers("/Login"); 
		web.ignoring().antMatchers("/forgetPassword/**");
		web.ignoring().antMatchers("/header/change-password");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		System.out.println("in Password Encoder");
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.exceptionHandling()
        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		.authorizeRequests()
		.antMatchers("/Login").authenticated()
			.and()
				.formLogin()
		        //.successHandler(new MYSuccessHandler())
		        .and()
				.exceptionHandling().accessDeniedPage("/accessdenied")
		        .and()
		        .logout();
		 System.out.println("in HttpSecurity 22"+http);
	}
}
