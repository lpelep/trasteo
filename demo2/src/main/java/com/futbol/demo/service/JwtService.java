package com.futbol.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.futbol.demo.modelo.Usuarios;

@Service
public interface JwtService {

	String toToken(Usuarios user);
	Optional<String> getClaims(String token);
	
}
