package com.futbol.demo.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	  protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable().authorizeRequests()
	        .antMatchers("/").permitAll()
	        .antMatchers("/rest/usuarios/all").permitAll()
	        .antMatchers("/register").permitAll()
	        .and()
	    .authorizeRequests()
	        .antMatchers("/h2-console/**").permitAll().anyRequest().authenticated();
		
		
		http.headers().frameOptions().disable();
		
	        //.anyRequest().authenticated();
//	        .and()
//	        // We filter the api/login requests
//	        .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
//	                UsernamePasswordAuthenticationFilter.class)
//	        // And filter other requests to check the presence of JWT in header
//	        .addFilterBefore(new JWTAuthenticationFilter(),
//	                UsernamePasswordAuthenticationFilter.class);
	  }
}
