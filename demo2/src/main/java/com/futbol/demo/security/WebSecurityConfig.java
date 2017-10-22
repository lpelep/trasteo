package com.futbol.demo.security;

import org.springframework.boot.autoconfigure.security.Http401AuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
    public JwttokenFilter jwtTokenFilter() {
        return new JwttokenFilter();
    }
	
	@Override
	  protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.exceptionHandling().authenticationEntryPoint(new Http401AuthenticationEntryPoint("Unauthenticated"))
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
			.and()
			.authorizeRequests()
			.antMatchers(HttpMethod.OPTIONS).permitAll()
			.and()
			.authorizeRequests().antMatchers("/").permitAll()
	        .antMatchers(HttpMethod.POST, "/rest/usuarios/login/").permitAll()
	        .antMatchers("/register").permitAll()
	        .and()
	        .authorizeRequests()
	        .antMatchers("/h2-console/**").permitAll()
	        .anyRequest().authenticated();
		
		
		http.headers().frameOptions().disable();
		
		http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		
	  }
}
