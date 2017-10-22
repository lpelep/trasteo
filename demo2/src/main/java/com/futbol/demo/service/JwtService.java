package com.futbol.demo.service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.futbol.demo.modelo.Usuarios;

@Service
public interface JwtService {

	String toToken(Usuarios user);
	Optional<String> getClaims(String token);
	void guardarUsuarioSesion(Usuarios user, HttpServletRequest request);
	
	boolean logout(Usuarios user, HttpServletRequest request, HttpServletResponse response);
	
	Integer getIdUsuarioFromToken (String token);
	boolean sePuedeRefrescar(String token);
}
