package com.futbol.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import com.futbol.demo.modelo.Rol;
import com.futbol.demo.modelo.Usuarios;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class DefaultJwtService implements JwtService {

	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.sessionTime}")
    private int sessionTime;

    @Override
    public String toToken(Usuarios user) {
        return Jwts.builder()
            .setSubject(user.getIdusuario().toString())
            .setExpiration(expireTimeFromNow())
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact();
    }

    private Date expireTimeFromNow() {
        return new Date(System.currentTimeMillis() + sessionTime * 1000);
    }

	@Override
	public Optional<String> getClaims(String token) {
		try {
			Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return Optional.ofNullable(claimsJws.getBody().getSubject());
		 } catch (Exception e) {
			 return Optional.empty();
	     }
	}
	
	@Override
	public void guardarUsuarioSesion(Usuarios user, HttpServletRequest request) {
		
		//si no hay contexto guardamos al usuario o bien es anónimo guardamos en sesion
		if (SecurityContextHolder.getContext().getAuthentication() == null || !SecurityContextHolder.getContext().getAuthentication().getPrincipal().getClass().equals(Usuarios.class)) {
        	UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, mapToGrantedAuthorities(user.getRoles()));
    		
    		authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
	}

	private static List<GrantedAuthority> mapToGrantedAuthorities(List<Rol> authorities) {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getStrRol()))
                .collect(Collectors.toList());
    }
	
	
	@Override
	public boolean  logout(Usuarios user, HttpServletRequest request, HttpServletResponse response) {
		
		boolean cerrado = false;
		
		//si se cumple la condición es que tenemos una sesión abierta
		if (SecurityContextHolder.getContext().getAuthentication() != null && SecurityContextHolder.getContext().getAuthentication().getPrincipal().getClass().equals(Usuarios.class)) {
			
			//si se cumple la condición es que el usuario que había un login pervio y se ha cambiado de usuario
			if (((Usuarios)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getIdusuario().intValue() != user.getIdusuario().intValue()) {
				
				//borramos al usuario de la sesion
				new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
				
				cerrado = true;
			}
		}
		
		return cerrado;
	}
	
	@Override
    public Integer getIdUsuarioFromToken(String token) {
        Integer id;
        try {
        	final Claims claims = getClaimsFromToken(token);
        	id = Integer.parseInt(claims.getSubject());
        } catch (Exception e) {
            id = null;
        }
        return id;
    }

	
	@Override
	public boolean sePuedeRefrescar(String token) {
		final Date expiration;
        final Claims claims = getClaimsFromToken(token);
        expiration = claims.getExpiration();
           
        
        return  expiration.before(new Date());
    }
    
    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }
    
}
