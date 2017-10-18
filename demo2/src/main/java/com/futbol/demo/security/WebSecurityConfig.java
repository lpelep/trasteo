package com.futbol.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
    public JwttokenFilter jwtTokenFilter() {
        return new JwttokenFilter();
    }
	
	@Override
	  protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable().authorizeRequests()
	        .antMatchers("/").permitAll()
	        .antMatchers("/rest/usuarios/login/").permitAll()
	        .antMatchers("/register").permitAll()
	        .and()
	    .authorizeRequests()
	        .antMatchers("/h2-console/**").permitAll().anyRequest().authenticated();
		
		
		http.headers().frameOptions().disable();
		
		http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		
		// disable page caching
		//http.headers().cacheControl();
	  }
}
